package forwardpass2409041258;

public class Layer {
    private float[][][]resultset;

    /**
     *
     * @param   inputHorRes     input image's horizontal resolution
     * @param   inputVerRes     input image's vertical resolution
     * @param   filters         number of filters in layer
     * @param   filterHorRes    filter's horizontal resolution
     * @param   filterVerRes    filter's vertical resolution
     * @param   stride          step size filter makes
     * @param   activationFunc  the activation function to use
     * @param   poolSize        size of the field to pool
     * @param   poolStride      step size pool makes
     * @apiNote "filters" are often referred to as "kernels"
     */
    public Layer(int inputHorRes, int inputVerRes, int filters,
                 int filterHorRes, int filterVerRes, int stride, Func activationFunc,
                 int poolSize, int poolStride) {
        this.resultset = new float[inputHorRes- filterHorRes+ stride][inputVerRes- filterVerRes+ stride][filters];
        // TODO: 04.09.2024 Auf dem Zettel(CNN-Forwardpass) LayerI Punkt Max Pooling 1 weiter machen 
    }
}
