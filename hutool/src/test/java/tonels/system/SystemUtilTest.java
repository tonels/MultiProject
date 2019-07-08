package tonels.system;

import cn.hutool.system.JavaInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SystemUtilTest {
	
	@Test
	@Ignore
	public void dumpTest() {
		SystemUtil.dumpSystemInfo();
	}
	
	@Test
	public void getCurrentPidTest() {
		long pid = SystemUtil.getCurrentPID();
		System.out.println(pid);
	}
	
	@Test
	public void getJavaInfoTest() {
		JavaInfo javaInfo = SystemUtil.getJavaInfo();
		System.out.println(javaInfo);
	}
	
	@Test
	public void getOsInfoTest() {
		OsInfo osInfo = SystemUtil.getOsInfo();
		System.out.println(osInfo);
	}
	
}
