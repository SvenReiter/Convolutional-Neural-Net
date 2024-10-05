import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PoolingTest {
    final float DELTA= (float)Math.pow(10, -3);

    @Test
    void testsConvolutionsChannelPooling() {
        float[][] input = new float[][] {
/*00*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*01*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*02*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*03*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*04*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*05*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*06*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*07*/          {0, 0, 0, 0, 0, 0, 84, 185, 159, 151, 60, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*08*/          {0, 0, 0, 0, 0, 0, 222, 254, 254, 254, 254, 241, 198, 198, 198, 198, 198, 198, 198, 198,170,52,0,0,0,0,0,0},
/*09*/          {0, 0, 0, 0, 0, 0, 67, 114, 72, 114, 163, 227, 254, 225, 254, 254, 254, 250, 229, 254, 254,140,0,0,0,0,0,0},
/*10*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 66, 14, 67, 67, 67, 59, 21, 236, 254, 106, 0, 0, 0, 0, 0, 0},
/*11*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 83, 253, 209, 18, 0, 0, 0, 0, 0, 0},
/*12*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 233, 255, 83, 0, 0, 0, 0, 0, 0, 0},
/*13*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 129, 254, 238, 44, 0, 0, 0, 0, 0, 0, 0},
/*14*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 59, 249, 254, 62, 0, 0, 0, 0, 0, 0, 0, 0},
/*15*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 254, 187, 5, 0, 0, 0, 0, 0, 0, 0, 0},
/*16*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 205, 248, 58, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*17*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 126, 254, 182, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*18*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 75, 251, 240, 57, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*19*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 221, 254, 166, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*20*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 203, 254, 219, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*21*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 38, 254, 254, 77, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*22*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 224, 254, 115, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*23*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 254, 254, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*24*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 61, 242, 254, 254, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*25*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 121, 254, 254, 219, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*26*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 121, 254, 207, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
/*27*/          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        float[][][] filters = new float[][][]{
            {
                {0.003f, -0.077f, -0.037f, 0.039f, -0.095f},
                {0.103f, -0.345f, 0.22f, 0.01f, 0.137f},
                {0.028f, -0.259f, -0.108f, -0.077f, 0.14f},
                {-0.078f, -0.062f, 0.021f, -0.015f, -0.018f},
                {-0.042f, 0.067f, 0.088f, 0.051f, 0.2f}
            }, {
                {0.028f, 0.06f, 0.046f, 0.069f, 0.214f},
                {-0.093f, -0.019f, 0.036f, 0.23f, -0.115f},
                {-0.265f, -0.43f, -0.266f, -0.278f, -0.328f},
                {-0.005f, -0.029f, -0.244f, -0.132f, -0.216f},
                {0.111f, -0.035f, 0.197f, 0.183f, 0.136f}
            }
        };
        float[] biases = new float[]{0.0155f, -0.567f};
        Func reLu = x -> (x[0] > 0) ? x[0] : 0;
        int CONV_POOL_STRIDE = 1;
        Convolution c = new Convolution(input, filters, 5, 5,biases, reLu, CONV_POOL_STRIDE);

        Func averagePool= param -> {
            float result= 0f;
            for(float f: param) {
                result+= f;
            }
            return (result/ param.length);
        };
        Func minPool= param -> {
            float result= 0f;
            for(float f: param) {
                if(f< result) {
                    result= f;
                }
            }
            return result;
        };
        Func maxPool= param -> {
            float result= 0f;
            for(float f: param) {
                if(f> result) {
                    result= f;
                }
            }
            return result;
        };
        final int POOL_STRIDE=2;
        final int POOL_SIZE= 2;
        Pooling p= new Pooling(POOL_SIZE, POOL_STRIDE, maxPool);
        p.poolChannel(c.getActivation()[0]);
        Assert.assertArrayEquals(new float[]{42.904f,  57.547f, 21.180f,  37.763f}, p.accumulate(c.getActivation()[0], 4,  2),  DELTA);
        Assert.assertArrayEquals(new float[]{0.0155f,   0.000f, 0.0155f,  0.0155f}, p.accumulate(c.getActivation()[0], 10, 6),  DELTA);
        Assert.assertArrayEquals(new float[]{ 0.000f,   0.000f,  0.000f,  16.636f}, p.accumulate(c.getActivation()[0], 15, 16), DELTA);
        Assert.assertArrayEquals(new float[]{ 6.166f,   0.031f,  0.189f,  0.0155f}, p.accumulate(c.getActivation()[0], 15, 18), DELTA);
        Assert.assertArrayEquals(new float[]{67.670f, 113.974f, 99.312f,  89.251f}, p.accumulate(c.getActivation()[0], 16, 10), DELTA);
        Assert.assertArrayEquals(new float[]{0.0155f,   0.000f, 0.0155f,  0.0155f}, p.accumulate(c.getActivation()[0], 10, 6),  DELTA);
        Assert.assertArrayEquals(new float[]{0.0155f,   0.000f,  0.000f,   0.000f}, p.accumulate(c.getActivation()[0], 23, 23), DELTA);

        Assert.assertArrayEquals(new float[]{30.205f,   21.186f, 115.24f, 118.097f}, p.accumulate(c.getActivation()[1], 3,  8),  DELTA);
        Assert.assertArrayEquals(new float[]{86.102f,   88.461f, 82.126f, 102.705f}, p.accumulate(c.getActivation()[1], 8,  8),  DELTA);
        Assert.assertArrayEquals(new float[]{0.239f,     0.413f,  1.496f,   0.000f}, p.accumulate(c.getActivation()[1], 20, 15),  DELTA);
        Assert.assertArrayEquals(new float[]{39.236f,   71.407f, 13.771f,  28.452f}, p.accumulate(c.getActivation()[1], 8,  2),  DELTA);

        Assert.assertEquals(2.785f,   p.poolChannel(c.getActivation()[0])[6/POOL_STRIDE][6/POOL_STRIDE],  DELTA);
        Assert.assertEquals(100.686f, p.poolChannel(c.getActivation()[0])[12/POOL_STRIDE][13/POOL_STRIDE],DELTA);
        Assert.assertEquals(14.425f,  p.poolChannel(c.getActivation()[0])[18/POOL_STRIDE][16/POOL_STRIDE],DELTA);
        Assert.assertEquals(0.267f,   p.poolChannel(c.getActivation()[0])[7/POOL_STRIDE][21/POOL_STRIDE], DELTA);
        Assert.assertEquals(113.974f, p.poolChannel(c.getActivation()[0])[16/POOL_STRIDE][10/POOL_STRIDE],DELTA);
        Assert.assertEquals(113.974f, p.poolChannel(c.getActivation()[0])[17/POOL_STRIDE][11/POOL_STRIDE],DELTA);

        /* Der aktuelle Pooling-Prozess arbeitet mit einem POOL_SIZE von 2(=> 2x2) und POOL_STRIDE von 2,
           sodass 4 quadratisch-benachbarte Werte aus activationLayer[][](c.getActivation()[0])
           nach pooled-Channel zu einem(entspr. der Pooling-Aktivierungsfunktion) zusammengefasst werden */
        Assert.assertEquals(73.433f, p.poolChannel(c.getActivation()[0])[16/POOL_STRIDE][13/POOL_STRIDE],DELTA);  //Wert activationLayer[16][13] wird nach poolChannel[8][6] zusammengefasst
        Assert.assertEquals(73.433f, p.poolChannel(c.getActivation()[0])[16/POOL_STRIDE][12/POOL_STRIDE],DELTA);  //Wert activationLayer[16][12] wird nach poolChannel[8][6] zusammengefasst
        Assert.assertEquals(73.432f, p.poolChannel(c.getActivation()[0])[17/POOL_STRIDE][12/POOL_STRIDE],DELTA);  //Wert activationLayer[17][12] wird nach poolChannel[8][6] zusammengefasst
        Assert.assertEquals(73.432f, p.poolChannel(c.getActivation()[0])[17/POOL_STRIDE][13/POOL_STRIDE],DELTA);  //Wert activationLayer[17][13] wird nach poolChannel[8][6] zusammengefasst

        Assert.assertEquals(73.306f, p.poolChannel(c.getActivation()[0])[10/POOL_STRIDE][12/POOL_STRIDE],DELTA);
        Assert.assertEquals(73.306f, p.poolChannel(c.getActivation()[0])[10/POOL_STRIDE][13/POOL_STRIDE],DELTA);
        Assert.assertEquals(73.306f, p.poolChannel(c.getActivation()[0])[11/POOL_STRIDE][12/POOL_STRIDE],DELTA);
        Assert.assertEquals(73.306f, p.poolChannel(c.getActivation()[0])[11/POOL_STRIDE][13/POOL_STRIDE],DELTA);

        Assert.assertEquals(51.714f, p.poolChannel(c.getActivation()[0])[16/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(51.714f, p.poolChannel(c.getActivation()[0])[16/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(51.714f, p.poolChannel(c.getActivation()[0])[17/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(51.714f, p.poolChannel(c.getActivation()[0])[17/POOL_STRIDE][8/POOL_STRIDE],DELTA);

        Assert.assertEquals(126.863f, p.poolChannel(c.getActivation()[0])[18/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(126.863f, p.poolChannel(c.getActivation()[0])[18/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(126.863f, p.poolChannel(c.getActivation()[0])[19/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(126.863f, p.poolChannel(c.getActivation()[0])[19/POOL_STRIDE][8/POOL_STRIDE],DELTA);

        Assert.assertEquals(0.0155f, p.poolChannel(c.getActivation()[0])[22/POOL_STRIDE][22/POOL_STRIDE],DELTA);
        Assert.assertEquals(0.0155f, p.poolChannel(c.getActivation()[0])[22/POOL_STRIDE][23/POOL_STRIDE],DELTA);
        Assert.assertEquals(0.0155f, p.poolChannel(c.getActivation()[0])[23/POOL_STRIDE][22/POOL_STRIDE],DELTA);
        Assert.assertEquals(0.0155f, p.poolChannel(c.getActivation()[0])[23/POOL_STRIDE][23/POOL_STRIDE],DELTA);

        Assert.assertEquals(85.572f, p.poolChannel(c.getActivation()[0])[22/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(85.572f, p.poolChannel(c.getActivation()[0])[22/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(85.572f, p.poolChannel(c.getActivation()[0])[23/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(85.572f, p.poolChannel(c.getActivation()[0])[23/POOL_STRIDE][9/POOL_STRIDE],DELTA);

        Assert.assertEquals(102.705f, p.poolChannel(c.getActivation()[1])[8/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(102.705f, p.poolChannel(c.getActivation()[1])[8/POOL_STRIDE][9/POOL_STRIDE],DELTA);
        Assert.assertEquals(102.705f, p.poolChannel(c.getActivation()[1])[9/POOL_STRIDE][8/POOL_STRIDE],DELTA);
        Assert.assertEquals(102.705f, p.poolChannel(c.getActivation()[1])[9/POOL_STRIDE][9/POOL_STRIDE],DELTA);

        Assert.assertEquals(1.029f, p.poolChannel(c.getActivation()[1])[18/POOL_STRIDE][16/POOL_STRIDE],DELTA);
        Assert.assertEquals(1.029f, p.poolChannel(c.getActivation()[1])[18/POOL_STRIDE][17/POOL_STRIDE],DELTA);
        Assert.assertEquals(1.029f, p.poolChannel(c.getActivation()[1])[19/POOL_STRIDE][16/POOL_STRIDE],DELTA);
        Assert.assertEquals(1.029f, p.poolChannel(c.getActivation()[1])[19/POOL_STRIDE][17/POOL_STRIDE],DELTA);

        Assert.assertEquals(20.369f, p.poolChannel(c.getActivation()[1])[4/POOL_STRIDE][20/POOL_STRIDE],DELTA);
        Assert.assertEquals(20.369f, p.poolChannel(c.getActivation()[1])[4/POOL_STRIDE][21/POOL_STRIDE],DELTA);
        Assert.assertEquals(20.369f, p.poolChannel(c.getActivation()[1])[5/POOL_STRIDE][20/POOL_STRIDE],DELTA);
        Assert.assertEquals(20.369f, p.poolChannel(c.getActivation()[1])[5/POOL_STRIDE][21/POOL_STRIDE],DELTA);

        Assert.assertEquals(39.965f, p.poolChannel(c.getActivation()[1])[2/POOL_STRIDE][2/POOL_STRIDE],DELTA);
        Assert.assertEquals(39.965f, p.poolChannel(c.getActivation()[1])[2/POOL_STRIDE][3/POOL_STRIDE],DELTA);
        Assert.assertEquals(39.965f, p.poolChannel(c.getActivation()[1])[3/POOL_STRIDE][2/POOL_STRIDE],DELTA);
        Assert.assertEquals(39.965f, p.poolChannel(c.getActivation()[1])[3/POOL_STRIDE][3/POOL_STRIDE],DELTA);
    }
}
