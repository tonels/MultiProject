package tonels.core.img;

import cn.hutool.core.img.Img;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.io.FileUtil;

public class ImgTest {
	
	@Test
	@Ignore
	public void cutTest1() {
		Img.from(FileUtil.file("D://captcha//circle.png")).cut(0, 0, 200).write(FileUtil.file("D://captcha//aa//a.png"));
	}
	
	@Test
	@Ignore
	public void compressTest() {
		Img.from(FileUtil.file("D://captcha//circle.png")).setQuality(0.4).write(FileUtil.file("D://captcha//aa//a1.png"));
	}
	
	@Test
	@Ignore
	public void roundTest() {
		Img.from(FileUtil.file("D://captcha//circle.png")).round(0.5).write(FileUtil.file("D://captcha//aa//a2.png"));
	}
}
