import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Fibonacci {

    @Test
    public void test() {
        assertEquals(0, fib(0));
        assertEquals(1, fib(1));
        assertEquals(1, fib(2));
        assertEquals(2, fib(3));
        assertEquals(3, fib(4));
        assertEquals(5, fib(5));
        assertEquals(8, fib(6));
        assertEquals(13, fib(7));
        assertEquals(21, fib(8));
        assertEquals(34, fib(9));
        assertEquals(55, fib(10));
    }

    static long fib(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be > 0, was " + n);
        }
        int prev = 0;
        int next = 1;
        if (n == 0) {
            return prev;
        }
        if (n == 1) {
            return next;
        }
        int count = n;
        while (--count > 0) {
            int tmp = next;
            next = prev + next;
            prev = tmp;
        }
        return next;
    }
}
