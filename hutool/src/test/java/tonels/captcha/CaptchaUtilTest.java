package tonels.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.junit.Test;

import java.io.File;

public class CaptchaUtilTest {
	
	@Test
	public void createTest() {
		for(int i = 0; i < 1; i++) {
			ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(320, 240);
			shearCaptcha.write("d:/ls/ls.png");

		}
	}

	public static void main(String[] a) {
		System.out.println("ss");
	}


}
