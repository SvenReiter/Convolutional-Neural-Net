package forwardpass2409111257;

public class ConvolutionLayer {
    private float[][]input;
    private float[][]activationLayer;
    private float[][][] channels;
    private float[][][] filters;
    private float bias;

    public ConvolutionLayer(float[][]input, int nrFilters, int filterSize, Func activationFunc) {
        this.input= input;
        this.filters = new float[nrFilters][filterSize][filterSize];
        for(int fNr=0; fNr<nrFilters; fNr++) {
            for(int l=0; l<filterSize; l++) {
                for(int c=0; c<filterSize; c++) {
                    filters[fNr][l][c]= (float)Math.random()*2-1;
                }
            }
        }
        bias= (float)Math.random();
        activationLayer= new float[input.length-filterSize+1][input.length-filterSize+1];
        channels = new float[nrFilters][input.length-filterSize+1][input.length-filterSize+1];

        convolution(nrFilters, filterSize, activationFunc);
    }

    private void convolution(int nrFilters, int filterSize, Func activationFunc) {
        for(int line=0; line<activationLayer.length; line++) {
            for(int column=0; column<activationLayer[0].length; column++) {
                for(int fNr=0; fNr<nrFilters; fNr++) {
                    for (int l = 0; l < filterSize; l++) {
                        for (int c = 0; c < filterSize; c++) {
                            channels[fNr][line][column] += input[line + l][column + c] * filters[fNr][l][c];
                        }
                    }
                    channels[fNr][line][column] += bias;
                    channels[fNr][line][column] = activationFunc.calc(channels[fNr][line][column]);
                }
            }
        }
    }

    public float[][]getChannel(int id) {
        return channels[id];
    }
}