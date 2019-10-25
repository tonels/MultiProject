package tonels.core.io.file;

import cn.hutool.core.io.file.Tailer;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;

public class TailerTest {
	
	@Test
	@Ignore
	public void tailTest() {
		FileUtil.tail(FileUtil.file("D:/captcha/a.txt"), CharsetUtil.CHARSET_UTF_8);
	}
	
	@Test
	@Ignore
	public void tailWithLinesTest() {
		Tailer tailer = new Tailer(FileUtil.file("e:/tail.txt"), Tailer.CONSOLE_HANDLER, 2);
		tailer.start();
	}


//	@Test
//	public void s1(){
//		FileUtil.
//	}

}
