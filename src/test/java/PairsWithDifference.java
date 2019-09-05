import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PairsWithDifference {

    @Test
    public void test() {
        assertEquals(3, pairs(2, new int[] { 1, 5, 3, 4, 2 }));
    }

    static int pairs(int k, int[] arr) {
        Arrays.sort(arr); // O(n log n)
        int count = 0;
        // below block O(n log n)
        for (int i = 0; i < arr.length; i++) { // O(n)
            int expected = arr[i] + k;
            if (Arrays.binarySearch(arr, expected) >= 0) { // O(log n)
                count++;
            }
        }
        return count;
    }
}