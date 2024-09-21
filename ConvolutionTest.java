import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ConvolutionTest {
    final float DELTA= (float)Math.pow(10, -3);
    final static String ROOT= "C:\\Users\\sven-.SVEN\\Documents\\IntelliJ\\convolutionalNeuralNet\\src\\forwardpass2409101214\\";

    @Test
    void testsConvolutionsResultOfKernelMulInput() {
        float[][]input= new float[][]{
                                {  0,   0,   0,   0, 0},
                                {198, 198, 198, 198, 198},
                                {254, 254, 254, 250, 229},
                                { 67,  67,  67,  59, 21},
                                {  0,   0,   0,   0, 83},
                        };
        float[][][]filters= new float[][][]{
                                    {
                                            { 0.003f,-0.077f,-0.037f, 0.039f,-0.095f},
                                            { 0.103f,-0.345f,  0.22f,  0.01f, 0.137f},
                                            { 0.028f,-0.259f,-0.108f,-0.077f, 0.14f},
                                            {-0.078f,-0.062f, 0.021f,-0.015f,-0.018f},
                                            {-0.042f, 0.067f, 0.088f, 0.051f, 0.2f}
                                    },{
                                            { 0.028f,  0.06f, 0.046f, 0.069f, 0.214f},
                                            {-0.093f,-0.019f, 0.036f,  0.23f,-0.115f},
                                            {-0.265f, -0.43f,-0.266f,-0.278f,-0.328f},
                                            {-0.005f,-0.029f,-0.244f,-0.132f,-0.216f},
                                            { 0.111f,-0.035f, 0.197f, 0.183f, 0.136f}}
                            };
        float[]biases= new float[]{0.0155f, -0.567f};
        Func reLu= x-> (x[0]>0)? x[0]: 0;
        Convolution c= new Convolution(input, filters, biases, reLu);
        float[][]expectedChannelRes= new float[][]{
                                                    {0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
                                                    {20.394f, -68.31f, 43.56f, 1.98f, 27.126f},
                                                    {7.112f, -65.786f, -27.432f, -19.25f, 32.06f},
                                                    {-5.226f, -4.154f, 1.407f, -0.885f, -0.378f},
                                                    {0.0f, 0.0f, 0.0f, 0.0f, 16.6f}
                                     };
        Assert.assertArrayEquals(expectedChannelRes[0], c.getChannel(0)[0], DELTA);
        Assert.assertArrayEquals(expectedChannelRes[1], c.getChannel(0)[1], DELTA);
        Assert.assertArrayEquals(expectedChannelRes[2], c.getChannel(0)[2], DELTA);
        Assert.assertArrayEquals(expectedChannelRes[3], c.getChannel(0)[3], DELTA);
        Assert.assertArrayEquals(expectedChannelRes[4], c.getChannel(0)[4], DELTA);
    }

    @Test
    void testsActivationLayersDims() throws IOException {
        float[][]pixels= pixelsToFloatMap(ImageIO.read(new File(ROOT+"seven.png")));
        byte filterSize= 5;
        Func reLu= x-> (x[0]>0)? x[0]: 0;
        Convolution convolutionA = new Convolution(pixels, 2, filterSize, reLu);
        Assert.assertEquals(convolutionA.getActivationLayer()[0].length, pixels.length-filterSize+1);
        Assert.assertEquals(convolutionA.getActivationLayer()[0][0].length, pixels.length-filterSize+1);
    }

    float[][] pixelsToFloatMap(BufferedImage image) {
        final byte[] pixels= ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int inputHorRes= image.getWidth();
        final int inputVerRes= image.getHeight();
        float[][] result= new float[inputVerRes][inputHorRes];

        for(int pixel=1, row=0, col=0; pixel<pixels.length; pixel+=4) {
            result[row][col]= pixels[pixel];

            if (result[row][col]< 0) {
                result[row][col]+= 256;
            }

            col++;
            if (col== inputHorRes) {
                col= 0;
                if ((row+1)< inputVerRes) {
                    row++;
                }
            }
        }
        return result;
    }
}
