import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class SherlockAndValidString {

    // Complete the isValid function below.
    static String isValid(String s) {
        ArrayList<Long> counts = new ArrayList<>(s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values());
        Map<Long, Long> collect = counts.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return collect.size() == 1 || isOneRemovalEnoughForPerfection(collect) ? "YES" : "NO";
    }

    private static boolean isOneRemovalEnoughForPerfection(Map<Long, Long> collect) {
        ArrayList<Map.Entry<Long, Long>> entries = new ArrayList<>(collect.entrySet());
        Map.Entry<Long, Long> firstEntry = entries.get(0);
        Map.Entry<Long, Long> secondEntry = entries.get(1);
        return collect.size() == 2 &&
                (
                        (firstEntry.getValue() == 1 && firstEntry.getKey() == 1) ||
                                (firstEntry.getValue() == 1 && Math.abs(secondEntry.getKey() - firstEntry.getKey()) == 1) ||
                                (secondEntry.getValue() == 1 && secondEntry.getKey() == 1) ||
                                (secondEntry.getValue() == 1 && Math.abs(firstEntry.getKey() - secondEntry.getKey()) == 1)
                );
    }

    @Test
    public void test1() {
        assertEquals("YES", isValid("abc"));
    }

    @Test
    public void test2() {
        assertEquals("YES", isValid("abcc"));
    }

    @Test
    public void test3_1() {
        assertEquals("NO", isValid("aabbcd"));
    }

    @Test
    public void test3() {
        assertEquals("YES", isValid("aabbc"));
    }

    @Test
    public void test4() {
        assertEquals("YES", isValid("abcdefghhgfedecba"));
    }

    @Test
    public void test5() {
        String data = FileUtil.readFile("SherlockAndValidString.txt").get(0);
        assertEquals("YES", isValid(data));
    }
}
