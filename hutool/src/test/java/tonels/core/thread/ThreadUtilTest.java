package tonels.core.thread;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.Assert;
import org.junit.Test;

public class ThreadUtilTest {
	
	@Test
	public void executeTest() {
		final boolean isValid = true;
		
		ThreadUtil.execute(new Runnable() {
			
			@Override
			public void run() {
				Assert.assertTrue(isValid);
			}
		});
		
	}
}
