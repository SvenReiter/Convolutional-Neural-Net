
public class Pooling {
    final int POOL_SIZE;
    final int STRIDE;
    final Func POOLING_FUNC;

    /**
     * @param poolSize size of the template for pooling
     * @param stride template's stride's size for pooling
     * @param pooling the pooling-activationfunction*/
    public Pooling(int poolSize, int stride, Func pooling) {
        POOL_SIZE= poolSize;
        STRIDE= stride;
        POOLING_FUNC= pooling;
    }

    /**
     * Pools the handed channel based on initialized instance variable.
     * @param channel
     * @return reduced sequence based on initialized instance variable.*/
    public float[][] poolChannel(float[][]channel) {
        final int resRow= ((channel.length%2)==0)? (channel.length/POOL_SIZE): (channel.length/POOL_SIZE)+1;
        final int resCol= ((channel[0].length%2)==0)? (channel[0].length/POOL_SIZE): (channel[0].length/POOL_SIZE)+1;
        float[][] result= new float[resRow][resCol];
        for(int row=0; row<channel.length; row+=STRIDE) {
            for(int col=0; col<channel[0].length; col+=STRIDE) {
                result[row/POOL_SIZE][col/POOL_SIZE]=POOLING_FUNC.calc(accumulate(channel, row, col));
            }
        }
        return result;
    }

    /**
     * Accumulates pool-values based on current pooling-step and initialized instance variable.
     * @param channel   holds channels values
     * @param row   current pooling-step's row
     * @param col   current pooling-step's column
     * @return sequence of accumulated pool-values. */
    public float[] accumulate(float[][]channel, int row, int col) {
        float[]result= new float[POOL_SIZE*POOL_SIZE];
        for(int r=row; r<row+POOL_SIZE && r<channel.length; r++) {
            for(int c=col; c<col+POOL_SIZE && c<channel[r].length; c++) {
                result[(r-row)*POOL_SIZE+(c-col)]= channel[r][c];
            }
        }
        return result;
    }
}
