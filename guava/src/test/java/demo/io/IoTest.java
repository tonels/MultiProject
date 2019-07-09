package demo.io;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Files;
import com.google.common.io.Flushables;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;

@Test
public class IoTest {

    public void messAroundWithFile() {
        File file = new File("d:/woop.txt");
        try {
            Files.touch(file);

            Files.write("Hey sailor!\n hello li", file, Charsets.UTF_8);
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }
}
