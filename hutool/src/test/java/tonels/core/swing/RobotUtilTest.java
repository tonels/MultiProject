package tonels.core.swing;

import cn.hutool.core.swing.RobotUtil;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.io.FileUtil;

public class RobotUtilTest {

	@Test
	@Ignore
	public void captureScreenTest() {
		RobotUtil.captureScreen(FileUtil.file("e:/screen.jpg"));
	}
}
