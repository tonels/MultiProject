package tonels.core.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.hutool.core.img.ImgUtil;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.io.FileUtil;

public class ImgUtilTest {

	@Test
	@Ignore
	public void scaleTest() {
		ImgUtil.scale(FileUtil.file("D://captcha//circle.png"), FileUtil.file("D://captcha//aa//a.png"), 0.8f);
	}
	
	@Test
	@Ignore
	public void scalePngTest() {
		ImgUtil.scale(FileUtil.file("D://captcha//circle.png"), FileUtil.file("D://captcha//aa//a.png"), 0.5f);
	}

	@Test
	@Ignore
	public void scaleByWidthAndHeightTest() {
		ImgUtil.scale(FileUtil.file("D://captcha//circle.png"), FileUtil.file("D://captcha//aa//a.png"), 100, 800, Color.BLUE);
	}

	@Test
	@Ignore
	public void cutTest() {
		ImgUtil.cut(FileUtil.file("D://captcha//circle.png"), FileUtil.file("D://captcha//aa//a.png"), new Rectangle(200, 200, 100, 100));
	}
	
	@Test
	@Ignore
	public void rotateTest() throws IOException {
		Image image = ImgUtil.rotate(ImageIO.read(FileUtil.file("D://captcha//circle.png")), 180);
		ImgUtil.write(image, FileUtil.file("D://captcha//aa//a.png"));
	}

	@Test
	@Ignore
	public void flipTest() throws IOException {
		ImgUtil.flip(FileUtil.file("D://captcha//circle.png"), FileUtil.file("d:/result.png"));
	}

	@Test
	@Ignore
	public void pressImgTest() {
		ImgUtil.pressImage(FileUtil.file("D://captcha//circle.png"), FileUtil.file("d:/picTest/dest.jpg"), ImgUtil.read(FileUtil.file("d:/picTest/1432613.jpg")), 0, 0, 0.1f);
	}

	@Test
	@Ignore
	public void pressTextTest() {
		ImgUtil.pressText(//
				FileUtil.file("e:/pic/face.jpg"), //
				FileUtil.file("e:/pic/test2_result.png"), //
				"版权所有", Color.WHITE, //
				new Font("黑体", Font.BOLD, 100), //
				0, //
				0, //
				0.8f);
	}

	@Test
	@Ignore
	public void sliceByRowsAndColsTest() {
		ImgUtil.sliceByRowsAndCols(FileUtil.file("D://captcha//circle.png"), FileUtil.file("e:/pic/dest"), 10, 10);
	}
	
	@Test
	@Ignore
	public void convertTest() {
		ImgUtil.convert(FileUtil.file("D://captcha//circle.png"), FileUtil.file("e:/test2Convert.jpg"));
	}
	
	@Test
	@Ignore
	public void writeTest() {
		ImgUtil.write(ImgUtil.read("D://captcha//circle.png"), FileUtil.file("e:/test2Write.jpg"));
	}
	
	@Test
	@Ignore
	public void compressTest() {
		ImgUtil.compress(FileUtil.file("D://captcha//circle.png"), FileUtil.file("e:/pic/1111_target.jpg"), 0.8f);
	}
}
