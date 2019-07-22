package dataFormat.pdfTest;

import cn.hutool.core.io.FileUtil;
import org.apache.tika.exception.TikaException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.Matrix;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PdfTest {
//    public static void main(String[] args) {
//
//        FileUtil.
//
//
//    }


    public static void main(final String[] args) throws IOException, TikaException {

        parseToText("D:\\个人网达文件管理\\李全\\a.pdf","D:\\个人网达文件管理\\李全\\ssad.text");

    }

    public static void parseToText(String fromPath,String toPath) throws IOException{

        FileInputStream inputstream = new FileInputStream(new File("D:\\个人网达文件管理\\李全\\a.pdf"));

        PDDocument load = PDDocument.load(inputstream);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(load);
        FileUtil.appendUtf8String(text,toPath);
        inputstream.close();
        load.close();
    }

}
