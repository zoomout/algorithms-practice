import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}
