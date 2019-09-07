import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(FileUtil.class.getResource(fileName).toURI()),
                    Charset.defaultCharset());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Couldn't read file: " + fileName, e);
        }
    }

    public static long[] getLongValuesFromFile(String fileName) {
        return getLongArrayFromString(readFile(fileName).get(0));
    }

    private static long[] getLongArrayFromString(String s) {
        return Arrays.stream(s.split(" ")).map(Integer::valueOf).mapToLong(x -> x).toArray();
    }

    public static int[] getIntValuesFromFile(String fileName) {
        return getIntArrayFromString(readFile(fileName).get(0));
    }

    private static int[] getIntArrayFromString(String s) {
        return Arrays.stream(s.split(" ")).map(Integer::valueOf).mapToInt(x -> x).toArray();
    }

}
