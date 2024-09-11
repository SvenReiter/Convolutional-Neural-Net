package forwardpass2409111257;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Start {
    /*
        Defines a prototype of Pooling-function in PoolingLayer();
        Defines tests for testing Pooling-function of PoolingLayer();
        Adds a few more PoolingLayer()-tests;
        Extends Pooling-functions Paramlist by a divisor(usually 1), to make possible defining average-pooling;
     */
    final static String ROOT= "C:\\Users\\sven-.SVEN\\Documents\\IntelliJ\\convolutionalNeuralNet\\src\\forwardpass2409101214\\";
    public static void main(String[] args) throws IOException {
        float[][]pixels= pixelsToFloatMap(ImageIO.read(new File(ROOT+"seven.png")));
        Func reLu= x-> (x[0]>0)? x[0]: 0;
        ConvolutionLayer convolutionLayerA= new ConvolutionLayer(pixels, 2,5, reLu);
        Func maxPooling= x-> (x[0]>x[1])? x[0]: x[1];
        PoolingLayer poolingLayerA= new PoolingLayer(convolutionLayerA.getChannel(0), 2, maxPooling, 1);
    }

    static float[][] pixelsToFloatMap(BufferedImage image) {
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
