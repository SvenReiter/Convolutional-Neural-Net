
public class Pooling {
    private float[][]result;

    public Pooling(float[][]channel, int poolSize, int stride, Func poolingFunc, int div) {
        result= new float[channel.length/poolSize][channel[0].length/poolSize];

        float resultingValue= 0;
        for(int row=0; row<channel.length; row+=stride) {
            for(int col=0; col<channel[row].length; col+=stride) {

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
