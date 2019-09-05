import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class MinimumDifference {

    static int minimumAbsoluteDifference(int[] arr) {
        Arrays.sort(arr);
        int min = 0;
        for (int i = 1; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - arr[i - 1]);
            if (diff == 0) {
                return 0;
            }
            if (min == 0) {
                min = diff;
            }
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }

    @Test
    public void test() {
        assertEquals(3, minimumAbsoluteDifference(new int[]{1, -3, 71, 68, 17}));
    }

    @Test
    public void test2() throws Exception {
        String data = FileUtil.readFile("MinimumDifference.txt");
        int[] ints = Arrays.stream(data.split(" ")).map(Integer::valueOf).mapToInt(x -> x).toArray();
        assertEquals(0, minimumAbsoluteDifference(ints));
    }
}
