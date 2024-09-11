package forwardpass2409111257;

public class PoolingLayer {
    private float[][]result;

    /**
     *
     * @param channel
     * @param poolSize          ==="stride"
     * @param poolingFunc
     */
    public PoolingLayer(float[][]channel, int poolSize, Func poolingFunc, int div) {
        result= new float[channel.length/poolSize][channel[0].length/poolSize];

        float resultingValue= 0;
        for(int row=0; row<channel.length; row+=poolSize) {
            for(int col=0; col<channel[row].length; col+=poolSize) {

                for(int r=0; r<poolSize; r++) {
                    for(int c=0; c<poolSize; c++) {
                        resultingValue= poolingFunc.calc(resultingValue, channel[row+r][col+c]);
                    }
                }
                result[row/poolSize][col/poolSize]= (resultingValue/div);
                resultingValue= 0;

            }
        }
    }

    public float[][] getResult() {
        return result;
    }
}
