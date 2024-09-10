package forwardpass2409101214;

public class ConvolutionLayer {
    private float[][]input;
    private float[][]activationLayer;
    private float[][][]channel;
    private float[][][]filter;
    private float bias;

    public ConvolutionLayer(float[][]input, int numberfilters, int filterSize, Func activationFunc) {
        this.input= input;
        this.filter= new float[numberfilters][filterSize][filterSize];
        for(int fNr=0; fNr<numberfilters; fNr++) {
            for(int l=0; l<filterSize; l++) {
                for(int c=0; c<filterSize; c++) {
                    filter[fNr][l][c]= (float)Math.random()*2-1;
                }
            }
        }
        bias= (float)Math.random();

        activationLayer= new float[input.length-filterSize+1][input.length-filterSize+1];
        channel= new float[numberfilters][input.length-filterSize+1][input.length-filterSize+1];

        for(int line=0; line<activationLayer.length; line++) {
            for(int column=0; column<activationLayer[0].length; column++) {

                for(int fNr=0; fNr<numberfilters; fNr++) {
                    for (int l = 0; l < filterSize; l++) {
                        for (int c = 0; c < filterSize; c++) {
                            channel[fNr][line][column] += input[line + l][column + c] * filter[fNr][l][c];
                        }
                    }
                    channel[fNr][line][column] += bias;
                    channel[fNr][line][column] = activationFunc.calc(channel[fNr][line][column]);
                }

            }
        }
        System.out.println();
    }
}