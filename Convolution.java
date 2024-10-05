
public class Convolution {
    private float[][][] activation;             //a sequence of resulting channels

    public Convolution(float[][]input, float[][][]filters, int fSizeCol, int fSizeRow, float[]biases, Func activationFunc, int stride) {
        activation= new float[filters.length][input.length-fSizeRow+1][input[0].length-fSizeCol+1];                     //(+1):= weil das erste mapping(an Position[0]) ansonsten übersprungen würde

        float[][]matMulRes;
        float sumUpMatValRes;
        for(int filterId=0; filterId<filters.length; filterId++) {
            for(int rowInput=0; rowInput<input.length-fSizeCol+1; rowInput+=stride) {
                for(int colInput=0; colInput<input[rowInput].length-fSizeRow+1; colInput+=stride) {
                    matMulRes= matMul(input, filters[filterId], rowInput, colInput, stride);
                    sumUpMatValRes= sumUpMatVal(matMulRes);
                    sumUpMatValRes+= biases[filterId];
                    activation[filterId][rowInput][colInput]= activationFunc.calc(sumUpMatValRes);
                }
            }
            // TODO: 05.10.2024 yt-video(Convolutional Neural Networks from Scratch | In Depth/ Conv Layer 2)
        }
    }

    /**
     * @pre
     * @param input
     * @param filter
     * @param row
     * @param col
     * @param stride
     * @return
     */
    public float[][] matMul(float[][]input, float[][]filter, int row, int col, int stride) {
        float[][]result= new float[filter.length][filter[0].length];
        for(int r=0; r<filter.length; r++) {
            for(int c=0; c<filter[0].length; c++) {
                if((row+r)>(input.length-1) || (col+c)>(input[0].length-1))
                    continue;
                result[r][c] = filter[r][c] * input[row + r][col + c];

            }
        }
        return result;
    }

    public float sumUpMatVal(float[][]mat) {
        float result=0;
        for(int r=0; r<mat.length; r++) {
            for(int c=0; c<mat[0].length; c++) {
                result+= mat[r][c];
            }
        }
        return result;
    }

    public float[][][] getActivation() {
        return activation;
    }
}
