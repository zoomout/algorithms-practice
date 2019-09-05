import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IceCreamParlor {

    // O (n log n)
    static void whatFlavors(int[] cost, int money) {
        int[] sorted = Arrays.copyOf(cost, cost.length); // O(n)
        Arrays.sort(sorted); // O(n log n)

        // block below: O(n log n)
        int e1 = 0;
        int e2 = 0;
        for (int i = 0; i < cost.length; i++) { // O(n)
            int another = Arrays.binarySearch(sorted, money - sorted[i]); // O(log n)
            if (another >= 0 && another != i) {
                e1 = sorted[i]; // O(1)
                e2 = sorted[another]; // O(1)
                break;
            }
        }

        List<Integer> costList = Arrays.stream(cost).boxed().collect(Collectors.toList());

        int indexOfFirst = costList.indexOf(e1); // O(n)
        int indexOfSecond = e1 != e2 ? costList.indexOf(e2) : costList.lastIndexOf(e2); // O(n)
        indexOfFirst++;
        indexOfSecond++;
        System.out.println(indexOfFirst < indexOfSecond ? (indexOfFirst + " " + indexOfSecond)
                : (indexOfSecond + " " + indexOfFirst));
    }

    public static void main(String[] args) {
        whatFlavors(new int[] { 1, 2, 3, 5, 6 }, 5); // 2 3
        whatFlavors(new int[] { 1, 4, 5, 3, 2 }, 4); // 1 4
        whatFlavors(new int[] { 2, 2, 4, 3 }, 4); // 1 2
    }

}
