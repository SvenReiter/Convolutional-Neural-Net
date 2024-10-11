
public class Layer {
    final Convolution CONV;
    final Pooling POOL;
    public Layer(Convolution conv, int poolSize, int stride, Func pooling) {
        CONV= conv;
        POOL= new Pooling(poolSize, stride, pooling);
    }
}
