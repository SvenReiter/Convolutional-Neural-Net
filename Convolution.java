
public class Convolution {
    private float[][][] activation;             //a sequence of resulting channels

    public Convolution(float[][]input, float[][][]filters, int fSizeCol, int fSizeRow, float[]biases, Func activationFunc, int stride) {
        activation= new float[filters.length][input.length-fSizeRow+1][input[0].length-fSizeCol+1];                     //(+1):= weil das erste mapping(an Position[0]) ansonsten übersprungen würde

        float[][]matMulRes;
        float sumUpMatValRes;
        for(int filterId=0; filterId<filters.length; filterId++) {
            for(int rowInput=0; rowInput<input.length-fSizeCol+1; rowInput+=stride) {
                for(int colInput=0; colInput<input[rowInput].length-fSizeRow+1; colInput+=stride) {
                    matMulRes= matMul(input, filters[filterId], rowInput, colInput);
                    sumUpMatValRes= sumUpMatVal(matMulRes);
                    sumUpMatValRes+= biases[filterId];
                    activation[filterId][rowInput][colInput]+= activationFunc.calc(sumUpMatValRes);
                }
            }
        }
        /* TODO: 06.10.2024
            Das Pooling funktioniert und das Max-Pooling-Resultat stimmt auch mit dem des YT-Videos überein
            Der Algorithmus Convolution(...) muss nur geändert oder überladen werden:
                Convolution on Multiple Channels | Layer 2
                .
                zB. könnte man den Convolutions Konstruktor überladen:
                    Für Convolution on Multiple Channels | Layer 1
                    Convolution(float[][]input, float[][]filters, int fSizeCol, int fSizeRow, float[]biases, Func activationFunc, int stride)
                    Und für Convolution on Multiple Channels | Layer 2
                    Convolution(float[][]input, float[][][]filters, int fSizeCol, int fSizeRow, float[]biases, Func activationFunc, int stride)
                        Hierfür muss ich mir eine Lösung überlegen
         */
    }

    /**
     * Cellular multiplication of respectively elements rather scalar multiplication of.
     * @param input picture pixel wise.
     * @param filter the convolutions filter.
     * @param row the specific inputs row of area to be convoluted.
     * @param col the specific inputs column of area to be convoluted.
     * @return multiplication of respectively elements of filter[][], input[][]. */
    public float[][] matMul(float[][]input, float[][]filter, int row, int col) {
        float[][]result= new float[filter.length][filter[0].length];
        for(int r=0; r<filter.length; r++) {
            if((row+r)>(input.length-1)) continue;
            for(int c=0; c<filter[0].length; c++) {
                if((col+c)>(input[0].length-1)) continue;
                result[r][c] = filter[r][c] * input[row + r][col + c];
            }
        }
        return result;
    }

    /**
     * Sums up sequence's elements.
     * @param mat the sequence of elements to sum.
     * @return The sum of all elements from mat. */
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
