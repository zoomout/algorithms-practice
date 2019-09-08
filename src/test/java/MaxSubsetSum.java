import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MaxSubsetSum {

    static int maxSubsetSum(int[] arr) {
        int w = arr[0];
        int u = Integer.MIN_VALUE;
        int v = Integer.MIN_VALUE;

        for (int i = 2; i < arr.length; i++) {

            int t = u;
            u = Math.max(u, v);
            v = Math.max(t, w) + arr[i];
            w = Math.max(w, arr[i - 1]);
        }

        return Math.max(u, v);
    }

    @Test
    public void test1() {
        assertEquals(13, maxSubsetSum(new int[] { 3, 7, 4, 6, 5 }));
    }

    @Test
    public void test2() {
        assertEquals(11, maxSubsetSum(new int[] { 2, 1, 5, 8, 4 }));
    }

    @Test
    public void test3() {
        assertEquals(15, maxSubsetSum(new int[] { 3, 5, -7, 8, 10 }));
    }

    @Test
    public void test4() {
        assertEquals(151598486, maxSubsetSum(FileUtil.getIntValuesFromFile("MaxSubsetSum1.txt")));
    }

    @Test
    public void test5() {
        assertEquals(-3, maxSubsetSum(new int[] { -5, 2, -5, -5, -5 }));
    }
}