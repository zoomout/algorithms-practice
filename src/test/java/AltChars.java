import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AltChars {

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        int count = 0;
        char previousChar = 0;
        for (char c : s.toCharArray()) {
            if (c == previousChar) {
                count++;
            } else {
                previousChar = c;
            }
        }
        return count;
    }

    @Test
    public void test() {
        assertEquals(3, alternatingCharacters("AAAA"));
        assertEquals(4, alternatingCharacters("BBBBB"));
        assertEquals(4, alternatingCharacters("AAABBB"));
        assertEquals(0, alternatingCharacters("ABABABA"));
        assertEquals(1, alternatingCharacters("ABAABABA"));
    }
}
