package samples.misc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Files1 {

    // -------------------------遍历单个目录----------------------------------
    @Test
    public void getDirs1() throws IOException {
        Path dir = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources");
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path e : stream) {
            System.out.println(e.getFileName());
        }
    }

    @Test
    public void getDirs2() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources"))) {
            Iterator<Path> ite = stream.iterator();
            while (ite.hasNext()) {
                Path pp = ite.next();
                System.out.println(pp.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------------遍历单个目录----------------------------------
// -------------------------遍历整个目录----------------------------------
    @Test
    public void t2() throws IOException {
        Path startingDir = Paths.get("D:\\GitRepository\\multiProject\\java8");
        List<Path> result = new LinkedList<Path>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        System.out.println("result.size()=" + result.size());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;

        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(".txt")) {
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    // -------------------------遍历整个目录----------------------------------
// -------------------------创建文件----------------------------------
    @Test
    public void create1() {
        try {
            Files.createDirectories(Paths.get("C://TEST"));
            if (!Files.exists(Paths.get("C://TEST")))
                Files.createFile(Paths.get("C://TEST/test.txt"));
//            Files.createDirectories(Paths.get("C://TEST/test2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------------创建文件----------------------------------
// -------------------------输出流----------------------------------
    @Test
    public void io1() {
        String dir = "D:\\GitRepository\\multiProject\\java8\\src\\main\\resources";
        try {
            Files.createDirectories(Paths.get(dir));
            if (!Files.exists(Paths.get(dir)))
                Files.createFile(Paths.get(dir + "/test.txt"));
            Files.copy(Paths.get(dir + "/txt/test.txt"), System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // -------------------------输出流----------------------------------


    // -------------------------- BufferedReader ------------------------------
    @Test
    public void testReaderLines() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("test"))
                    .count();
            System.out.println(countPrints);
        }
    }
    // -------------------------- BufferedReader ------------------------------

    // -------------------------- BufferedWriter 写文件，不追加 ------------------------------
    @Test
    public void testWriter() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("print('Hello World');");
        }
    }
    // -------------------------- BufferedWriter 写文件，不追加 ------------------------------

    // -------------------------- BufferedReader 读文件第一行 ------------------------------
    @Test
    public void testReader() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        }
    }
    // -------------------------- BufferedReader 读文件第一行 ------------------------------

    // -------------------------- BufferedReader Stream<String> ------------------------------

    /**
     * @throws IOException print('Hello World1'); print('Hello World2'); print('Hello World3');
     */
    @Test
    public void testReader2() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        String string;
//        StringBuilder string = null ;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            string = reader.lines().collect(Collectors.joining(""));
        }
        System.out.println(string);
    }
    // -------------------------- BufferedReader Stream<String> ------------------------------

    // -------------------------- Files.walk 基于某一目录下，向下递归查找匹配文件 ------------------------------
    @Test
    public void testWalk() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(path, maxDepth)) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(e -> e.endsWith(".txt"))
                    .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined);
        }
    }
    // -------------------------- Files.walk 基于某一目录下，向下递归查找匹配文件 ------------------------------

    // -------------------------- Files.walk 基于某一目录下，向下递归查找匹配文件 ------------------------------

    /**
     * todo 文件会重复读/漏读 问题，main/target
     * find(): src\main\resources\txt\test.txt; target\classes\txt\test.txt
     *
     * @throws IOException
     */
    @Test
    public void testFind() throws IOException {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".txt"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("find(): " + joined);
        }
    }
    // -------------------------- Files.walk 基于某一目录下，向下递归查找匹配文件 ------------------------------

    /**
     * 读指定文件夹下，遍历 一 层级的文件和文件夹，
     * list(): src\main; src\test
     *
     * @throws IOException
     */
    @Test
    public void testList() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("src"))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("list(): " + joined);
        }
    }

    /**
     * 读指定文件，按行处理打印
     * print('Hello World1');
     *
     * @throws IOException
     */
    @Test
    public void testLines() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        try (Stream<String> stream = Files.lines(path)) {
            stream
                    .filter(line -> line.contains("1"))
                    .map(String::trim)
                    .forEach(System.out::println);
        }
    }

    /**
     * 从指定文件，读写到指定文件
     *
     * @throws IOException
     */
    @Test
    public void testReadWriteLines() throws IOException {
        Path path = Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt/test.txt");
        List<String> lines = Files.readAllLines(path);
        lines.add("print('foobar');");
        Files.write(Paths.get("D:\\GitRepository\\multiProject\\java8\\src\\main\\resources/txt", "ls.js"), lines);
    }



    // todo 测试 Git 提交标签





}
