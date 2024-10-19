
public class Convolution {
    private float[][][] activationSeq;             //a sequence of resulting channels
    private float[][] activation;             //the resulting sequence

    /**
     *
     * @param input
     * @param filters
     * @param fSizeCol
     * @param fSizeRow
     * @param biases
     * @param activationFunc
     * @param stride
     * @pre filters.length==input.length
     */
    public Convolution(float[][][]input, float[][][]filters, int fSizeCol, int fSizeRow, float bias, Func activationFunc, int stride) {
        activationSeq= new float[filters.length][input[0].length-fSizeRow+1][input[0][0].length-fSizeCol+1];
        activation= new float[input[0].length-fSizeRow+1][input[0][0].length-fSizeCol+1];
        float[][]matMulRes;
                                                                       //iterates the channels
        for(int rowInput=0; rowInput<activationSeq[0].length; rowInput+=stride) {                                       //iterates rows of a channel
            for (int colInput=0; colInput<activationSeq[0][rowInput].length; colInput+=stride) {                        //iterates columns of a channels row
                for(int filterId=0; filterId<filters.length; filterId++) {                                              //iterates the given filterstack
                    matMulRes = matMulElmWise(input[filterId], filters[filterId], rowInput, colInput);                  //writes result of (input[i]*filters[filterId] at[rowInput, colInput]) to matMulRes
                    // TODO: 18.10.2024 Der erste Unterschied ist, dass matMulElmWise(...) seine Werte auf 2 Nachkommastellen rundet (=> -23.4856 wird -23.49)

                                                               // SUMME(k,i)   in YT-Tutorial(8:38)
                    activationSeq[filterId][rowInput][colInput]+= sumUpMatVal(matMulRes);                               //sums up values of matMulRes to corresponding elm
                }
            }
        }

        for(int filterId=0; filterId<filters.length; filterId++) {
            for (int r=0; r<activationSeq[filterId].length; r++) {
                for (int c=0; c<activationSeq[filterId][r].length; c++) {

                                    // SUMME(c,j)   in YT-Tutorial(8:38)
                    activation[r][c]+= activationSeq[filterId][r][c];
                }
            }
        }

        for (int r=0; r<activationSeq[0].length; r++) {
            for (int c=0; c<activationSeq[0][r].length; c++) {

                // + b   in YT-Tutorial(8:38)
                activation[r][c]+= bias;
            }
        }
        System.out.println();
        for (int r=0; r<activationSeq[0].length; r++) {
            for (int c=0; c<activationSeq[0][r].length; c++) {

                // s(zi)   in YT-Tutorial(8:38)
                activation[r][c]= activationFunc.calc(activation[r][c]);
            }
        }
        System.out.println();
    }

    public Convolution(float[][]input, float[][][]filters, int fSizeCol, int fSizeRow, float[]biases, Func activationFunc, int stride) {
        activationSeq= new float[filters.length][input.length-fSizeRow+1][input[0].length-fSizeCol+1];                  //(+1):= weil das erste mapping(an Position[0]) ansonsten übersprungen würde

        float[][]matMulRes;
        for(int rowInput=0; rowInput<input.length-fSizeCol+1; rowInput+=stride) {
            for(int colInput=0; colInput<input[rowInput].length-fSizeRow+1; colInput+=stride) {
                for(int filterLayerId=0; filterLayerId<filters.length; filterLayerId++) {
                    matMulRes = matMulElmWise(input, filters[filterLayerId], rowInput, colInput);                       //Multiplies the kernel with the values from the input-layer
                    activationSeq[filterLayerId][rowInput][colInput] += sumUpMatVal(matMulRes);
                    /*Der Vorteil dieser Verschachtelung ist, dass nach activationSeq[][][] jeweils der berechnete
                      Wert{matMul(); sumUpMatVal()} aus Stackframe hinzugezählt werden kann.
                      Das reduziert den Speicherverbrauch erheblich.
                      */
                }
                for(int filterLayerId=0; filterLayerId<filters.length; filterLayerId++) {
                    activationSeq[filterLayerId][rowInput][colInput] += biases[filterLayerId];
                }
                //hier ist der Wert final summiert und kann an Aktivierungsfunktion übergeben werden
                for(int filterLayerId=0; filterLayerId<filters.length; filterLayerId++) {
                    activationSeq[filterLayerId][rowInput][colInput] = activationFunc.calc(activationSeq[filterLayerId][rowInput][colInput]);
                }
            }
        }
    }

    /**
     * Cellular multiplication of respectively elements rather scalar multiplication of.
     * @param input picture pixel wise.
     * @param filter the convolutions filter.
     * @param row the specific inputs row of area to be convoluted.
     * @param col the specific inputs column of area to be convoluted.
     * @return multiplication of respectively elements of filter[][], input[][]. */
    public float[][] matMulElmWise(float[][]input, float[][]filter, int row, int col) {
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

    public float[][][] getActivationSeq() {
        return activationSeq;
    }
}
