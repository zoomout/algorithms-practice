import org.junit.Test;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;

import static org.junit.Assert.assertEquals;

public class MinimumTimeRequired {

    static long minTime(long[] machines, long goal) {

        long minDays = getMinDays(machines, goal);
        long maxDays = getMaxDays(machines, goal);

        while (true) {
            long days = (minDays + maxDays) / 2;
            long sumPrevious = getSumForAmountOfDays(days - 1, machines);
            long sumCurrent = getSumForAmountOfDays(days, machines);
            if (sumPrevious < goal) {
                if (sumCurrent >= goal) {
                    return days;
                }
                minDays = days + 1;
            } else {
                maxDays = days - 1;
            }
        }

    }

    static long getMinDays(long[] machines, long goal) {
        return getAmountOfDays(machines, goal, Math::min);
    }

    static long getMaxDays(long[] machines, long goal) {
        return getAmountOfDays(machines, goal, Math::max);
    }

    static long getAmountOfDays(long[] machines, long goal, LongBinaryOperator op) {
        return (Arrays.stream(machines).reduce(op).orElse(0L) * goal + machines.length - 1) / machines.length;
    }

    static long getSumForAmountOfDays(long days, long[] machines) {
        long sum = 0;
        for (long machine : machines) {
            sum += days / machine;
        }
        return sum;
    }

    @Test
    public void test1() {
        // dummy to load
        assertEquals(6, minTime(new long[] { 2, 3 }, 5));
    }

    @Test
    public void test2() {
        assertEquals(6, minTime(new long[] { 2, 3 }, 5));
    }

    @Test
    public void test3() {
        assertEquals(7, minTime(new long[] { 1, 3, 4 }, 10));
    }

    @Test
    public void test4() {
        assertEquals(20, minTime(new long[] { 4, 5, 6 }, 12));
    }

    @Test
    public void test5() {
        assertEquals(304844592, minTime(FileUtil.getLongValuesFromFile("MinimumTimeRequired.txt"), 844676607L));
    }
}
