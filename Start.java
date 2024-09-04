package forwardpass2409041258;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class Start {
    final static String ROOT= "C:\\Users\\sven-.SVEN\\Documents\\IntelliJ\\convolutionalNeuralNet\\src\\forwardpass2409031354\\";
    public static void main(String[] args) throws IOException {
        BufferedImage inputImage= ImageIO.read(new File(ROOT+"seven.png"));
        final byte[] pixels= ((DataBufferByte) inputImage.getRaster().getDataBuffer()).getData();
        final int inputHorRes= inputImage.getWidth();
        final int inputVerRes= inputImage.getHeight();
        int[][] result= new int[inputVerRes][inputHorRes];

        for(int pixel=1, row=0, col=0; pixel<pixels.length; pixel+=4) {
            result[row][col]= pixels[pixel];

            if (result[row][col]< 0) { //if pixel value is negative, change to positive //still weird to me
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
        Func reLu= x-> (x[0]>0)? x[0]: 0;           //f(x)= max(0, x)
        Layer layerA= new Layer(inputHorRes, inputVerRes,2,5,5,1,
                                reLu,2,2);
    }
}
