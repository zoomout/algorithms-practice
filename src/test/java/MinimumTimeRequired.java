import org.junit.Test;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;

import static org.junit.Assert.assertEquals;

public class MinimumTimeRequired {

    // total complexity: O(machines.length log (maxDays-minDays))
    static long minTime(long[] machines, long goal) {

        long minDays = getMinDays(machines, goal); // O(machines.length)
        long maxDays = getMaxDays(machines, goal); // O(machines.length)

        while (true) { // O(log (maxDays-minDays))
            long days = (minDays + maxDays) / 2;
            long sumForPreviousDay = getSumForAmountOfDays(days - 1, machines); // O(machines.length)
            long sumForCurrentDay = getSumForAmountOfDays(days, machines); // O(machines.length)
            if (goal <= sumForPreviousDay) {
                maxDays = days - 1;
            } else if (goal > sumForCurrentDay) {
                minDays = days + 1;
            } else {
                return days;
            }
        }

    }

    /**
     * Returns amount of days it takes if all machines work as the fastest one
     */
    static long getMinDays(long[] machines, long goal) {
        return getAmountOfDays(machines, goal, Math::min);
    }

     /**
     * Returns amount of days it takes if all machines work as the slowest one
     */
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
