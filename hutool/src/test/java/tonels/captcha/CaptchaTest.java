package tonels.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.lang.Console;

import java.awt.*;

/**
 * 直线干扰验证码单元测试
 * 
 * @author looly
 *
 */
public class CaptchaTest {

	@Test
	public void lineCaptchaTest1() {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
		lineCaptcha.setBackground(Color.CYAN);
		lineCaptcha.setGenerator(new MathGenerator());
		lineCaptcha.write("d://captcha/maths.png");

	}
	
	@Test
	@Ignore
	public void lineCaptchaWithMathTest() {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
		lineCaptcha.setGenerator(new MathGenerator());
		String imageBase64 = lineCaptcha.getImageBase64();
		System.out.println(imageBase64);
		lineCaptcha.write("d://captcha/math.png");
	}

	@Test
	@Ignore
	public void lineCaptchaTest2() {

		// 定义图形验证码的长和宽
//		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
		 LineCaptcha lineCaptcha = new LineCaptcha(200, 100, 4, 150);
		// 图形验证码写出，可以写出到文件，也可以写出到流
		lineCaptcha.write("d://captcha/math1.png");
		Console.log(lineCaptcha.getCode());
		// 验证图形验证码的有效性，返回boolean值
		lineCaptcha.verify("1234");

		lineCaptcha.createCode();
		lineCaptcha.write("d://captcha/math2.png");
		Console.log(lineCaptcha.getCode());
		// 验证图形验证码的有效性，返回boolean值
		lineCaptcha.verify("1234");
	}

	@Test
	@Ignore
	public void circleCaptchaTest() {

		// 定义图形验证码的长和宽
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
		// CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
		// 图形验证码写出，可以写出到文件，也可以写出到流
		captcha.write("d:/captcha//circle.png");
		// 验证图形验证码的有效性，返回boolean值
		captcha.verify("1234");
	}

	@Test
	@Ignore
	public void ShearCaptchaTest() {

		// 定义图形验证码的长和宽
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
		// ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
		// 图形验证码写出，可以写出到文件，也可以写出到流
		captcha.write("d:/captcha/sss.png");
		// 验证图形验证码的有效性，返回boolean值
		captcha.verify("1234");
	}
	
	@Test
	@Ignore
	public void ShearCaptchaTest2() {
		
		// 定义图形验证码的长和宽
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 20, 4, 4);
		captcha.setGenerator(new MathGenerator());
		// ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
		// 图形验证码写出，可以写出到文件，也可以写出到流
		captcha.write("d:/captcha/shear.png");
		// 验证图形验证码的有效性，返回boolean值
		captcha.verify("1234");
	}



}
