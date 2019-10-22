package tonels.txt;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestTxt {

    @Test
    public void t1(){
        String path = "D:\\test\\aa.txt";
        File file = FileUtil.file(path);
        List<String> list = FileUtil.readLines(file, "utf-8");

        System.out.println(list);

    }










}
