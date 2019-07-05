package tonels.core.io;

import org.junit.Assert;
import org.junit.Test;

import cn.hutool.core.io.file.FileReader;

/**
 * 文件读取测试
 * @author Looly
 *
 */
public class FileReaderTest {
	
	@Test
	public void fileReaderTest(){
		FileReader fileReader = new FileReader("core/test.properties");
		String result = fileReader.readString();
		Assert.assertNotNull(result);
	}
}
