import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class TripletsSatisfyingCriteria {

    // total complexity:
    // O(lena log lena) + O(lenb log lenb) + O(lenc log lenc) +
    // O(lenb log lena) + O(lenb log lenc)
    static long triplets(int[] a, int[] b, int[] c) {
        Set<Integer> distinctA = new TreeSet<>();
        // O(lena log lena)
        for (int el : a) {
            distinctA.add(el);
        }
        // O(lenb log lenb)
        Set<Integer> distinctB = new TreeSet<>();
        for (int el : b) {
            distinctB.add(el);
        }
        // O(lenc log lenc)
        Set<Integer> distinctC = new TreeSet<>();
        for (int el : c) {
            distinctC.add(el);
        }

        long count = 0;
        int[] distinctArrayA = distinctA.stream().mapToInt(Number::intValue).toArray();
        int[] distinctArrayC = distinctC.stream().mapToInt(Number::intValue).toArray();
        for (int bElement : distinctB) { // O(lenb)
            int aCountSmallerOrEqualB = Arrays.binarySearch(distinctArrayA, bElement); // O(log lena)
            int cCountSmallerOrEqualB = Arrays.binarySearch(distinctArrayC, bElement); // O(log lenc)
            aCountSmallerOrEqualB = interpretSearchResult(distinctArrayA, aCountSmallerOrEqualB);
            cCountSmallerOrEqualB = interpretSearchResult(distinctArrayC, cCountSmallerOrEqualB);
            count += aCountSmallerOrEqualB * (long) cCountSmallerOrEqualB;
        }
        return count;
    }

    private static int interpretSearchResult(int[] a, int searchResult) {
        int result = 0;
        if (searchResult < 0) {
            int insertionIndex = Math.abs(searchResult);
            if (insertionIndex == 1 && insertionIndex >= a.length) {
                result = 0; // means item is not within the array
            } else {
                result = insertionIndex - 1;
            }
        } else {
            result = searchResult + 1;
        }
        return result;
    }

    @Test
    public void test() {
        assertEquals(8, triplets(new int[] { 1, 3, 5 }, new int[] { 2, 3 }, new int[] { 1, 2, 3 }));
        assertEquals(5, triplets(new int[] { 1, 4, 5 }, new int[] { 2, 3, 3 }, new int[] { 1, 2, 3 }));
        assertEquals(12, triplets(new int[] { 1, 3, 5, 7 }, new int[] { 5, 7, 9 }, new int[] { 7, 9, 11, 13 }));
    }

    @Test
    public void testMy() {
        assertEquals(16, triplets(new int[] { 1, 2, 3, 4 }, new int[] { 4, 5 }, new int[] { 1, 2 }));
        assertEquals(1, triplets(new int[] { 2, 3, 4 }, new int[] { 2 }, new int[] { 2, 3 }));
        assertEquals(0, triplets(new int[] { 1, 2, 3, 4 }, new int[] { 2, 3, 4 }, new int[] { 5, 6 }));
    }

    @Test
    public void test2() {
        List<String> arrays = FileUtil.readFile("TripletsSatisfyingCretiria.txt");
        int[] a = getIntArrayFromString(arrays.get(0));
        int[] b = getIntArrayFromString(arrays.get(1));
        int[] c = getIntArrayFromString(arrays.get(2));
        assertEquals(17747701952583L, triplets(a, b, c));
    }
    
    @Test
    public void test3() {
        List<String> arrays = FileUtil.readFile("TripletsSatisfyingCretiria2.txt");
        int[] a = getIntArrayFromString(arrays.get(0));
        int[] b = getIntArrayFromString(arrays.get(1));
        int[] c = getIntArrayFromString(arrays.get(2));
        assertEquals(145333908482693L, triplets(a, b, c));
    }

    private int[] getIntArrayFromString(String s) {
        return Arrays.stream(s.split(" ")).map(Integer::valueOf).mapToInt(x -> x).toArray();
    }
}
