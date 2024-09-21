
public class Convolution {
    private float[][]input;
    private float[][][]activationLayer;
    private float[][][] channels;
    private float[][][] filters;
    private float[] biases;

    public Convolution(float[][]input, int nrFilters, int filterSize, Func activationFunc) {
        this.input= input;
        filters = new float[nrFilters][filterSize][filterSize];
        biases= new float[nrFilters];
        for(int fNr=0; fNr<nrFilters; fNr++) {
            for(int l=0; l<filterSize; l++) {
                for(int c=0; c<filterSize; c++) {
                    filters[fNr][l][c]= (float)Math.random()*2-1;
                }
            }
            biases[fNr]= (float)Math.random();
        }
        activationLayer= new float[nrFilters][input.length-filterSize+1][input.length-filterSize+1];
        channels = new float[nrFilters][filterSize][filterSize];

        convolution(nrFilters, filterSize, activationFunc);
        System.out.println();
    }

    public Convolution(float[][]input, float[][][]filters, float[] biases, Func activationFunc) {
        this.input= input;
        this.filters= filters;
        this.biases= biases;
        activationLayer= new float[filters.length][input.length-filters[0].length+1][input.length-filters[0][0].length+1];
        channels = new float[filters.length][filters[0].length][filters[0][0].length];
        convolution(filters.length, filters[0].length, activationFunc);
    }

    private void convolution(int nrFilters, int filterSize, Func activationFunc) {
        for(int fNr=0; fNr<nrFilters; fNr++) {
            for(int line=0; line<activationLayer[fNr].length; line++) {
                for(int column=0; column<activationLayer[fNr][0].length; column++) {
                    for (int l = 0; l < filterSize; l++) {      //vertical <stride>:= 1
                        for (int c = 0; c < filterSize; c++) {  //horizontal <stride>:= 1
                            channels[fNr][l][c] += input[line + l][column + c] * filters[fNr][l][c];
                        }
                    }
                    activationLayer[fNr][line][column]= activationFunc.calc(sumUp(fNr, filters[0].length));
                    // TODO: 21.09.2024 check resulting <activationLayer[][][]> 
                }
            }
        }
    }

    private float sumUp(int fNr, int fs) {
        float result= 0f;
        for(int i=0; i<fs; i++) {
            for(int j=0; j<fs; j++) {
                result+= channels[fNr][i][j];
            }
        }
        result+= biases[fNr];
        return result;
    }

    public float[][]getChannel(int id) {
        return channels[id];
    }
    public float[][][] getActivationLayer() {
        return activationLayer;
    }

}