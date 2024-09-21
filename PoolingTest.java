import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PoolingTest {
    Pooling pl;

    @Test
    void testMaxPoolingB() {
        Func maxPooling= x-> (x[0]>x[1])? x[0]: x[1];
        float[][]testChannel= new float[][]{{1,2,3,4,5,6,7,8},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,1,3,5,7},
                                            {7,5,3,1,8,6,4,2},
                                            {2,4,6,8,1,3,5,7},
                                            {1,1,1,1,1,1,1,1},
                                            {2,2,2,2,2,2,3,3},
                                            {2,2,2,2,2,2,2,5}};
        pl= new Pooling(testChannel, 2, 2, maxPooling, 1);
        assertTrue(pl.getResult().length== 4 && pl.getResult()[0].length== 4);
    }

    @Test
    void testMaxPoolingA() {
        Func maxPooling= x-> (x[0]>x[1])? x[0]: x[1];
        float[][]testChannel= new float[][]{{1,2,3,4,5,6,7,8},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,1,3,5,7},
                                            {7,5,3,1,8,6,4,2},
                                            {2,4,6,8,1,3,5,7},
                                            {1,1,1,1,1,1,1,1},
                                            {2,2,2,2,2,2,3,3},
                                            {2,2,2,2,2,2,2,5}};
        pl= new Pooling(testChannel, 2, 2, maxPooling, 1);
        Assert.assertEquals(pl.getResult(), new float[][]{{8f,6f,6f,8f}, {7f,8f,8f,7f}, {4f,8f,3f,7f}, {2f,2f,2f,5f}});
    }

    @Test
    void testMaxPoolingC() {
        Func maxPooling= x-> (x[0]>x[1])? x[0]: x[1];
        float[][]testChannel= new float[][]{{1,2,3,4,5,6,1,2},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,1,3,5,3},
                                            {7,5,3,1,1,6,4,2},
                                            {2,4,6,8,1,3,5,7},
                                            {1,1,1,1,1,1,1,1},
                                            {2,2,2,2,2,2,3,3},
                                            {2,2,2,2,2,2,2,5}};
        pl= new Pooling(testChannel, 4, 4, maxPooling, 1);
        Assert.assertEquals(pl.getResult(), new float[][]{{8f,6f}, {8f,7f}});
    }

    @Test
    void testMinPoolingA() {
        Func minPooling= x-> (x[0]!=0)?((x[0]<x[1])? x[0]: x[1]):x[1];
        float[][]testChannel= new float[][]{{5,2,3,4,5,6,1,2},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,4,3,5,3},
                                            {7,5,3,3,9,6,4,2},
                                            {2,4,6,8,3,3,5,7},
                                            {5,5,5,5,5,5,5,5},
                                            {4,8,3,9,2,2,3,3},
                                            {2,2,5,9,2,2,2,5}};
        pl= new Pooling(testChannel, 4, 4, minPooling, 1);
        Assert.assertEquals(pl.getResult(), new float[][]{{2f,1f}, {2f,2f}});
    }

    @Test
    void testMinPoolingB() {
        Func minPooling= x-> (x[0]!=0)?((x[0]<x[1])? x[0]: x[1]):x[1];
        float[][]testChannel= new float[][]{{5,2,3,4,5,6,1,2},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,4,3,5,3},
                                            {7,5,3,3,9,6,4,2},
                                            {2,4,6,8,3,3,5,7},
                                            {5,5,5,5,5,5,5,5},
                                            {4,8,3,9,2,2,3,3},
                                            {2,2,5,9,2,2,2,5}};
        pl= new Pooling(testChannel, 2, 2, minPooling, 1);
        Assert.assertEquals(pl.getResult(), new float[][]{{2f,3f,3f,1f}, {2f,3f,3f,2f}, {2f,5f,3f,5f}, {2f,3f,2f,2f}});
    }

    @Test
    void testAveragePooling() {
        Func averagePooling= x-> x[0]+x[1];
        float[][]testChannel= new float[][]{{5,2,3,4,5,6,1,2},
                                            {8,7,6,5,4,3,2,1},
                                            {2,4,6,8,4,3,5,3},
                                            {7,5,3,3,9,6,4,2},
                                            {2,4,6,8,3,3,5,7},
                                            {5,5,5,5,5,5,5,5},
                                            {4,8,3,9,2,2,3,3},
                                            {2,2,5,9,2,2,2,5}};
        pl= new Pooling(testChannel, 2, 2, averagePooling, 2*2);
        Assert.assertEquals(pl.getResult(), new float[][]{{5.5f,4.5f,4.5f,1.5f}, {4.5f,5f,5.5f,3.5f}, {4f,6f,4f,5.5f},
                                                          {4f,6.5f,2f,3.25f}});
    }
}
