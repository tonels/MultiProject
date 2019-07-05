package tonels.core.annotation;

import cn.hutool.core.annotation.AnnotationUtil;
import org.junit.Assert;
import org.junit.Test;

public class AnnotationUtilTest {
	
	@Test
	public void getAnnotationValueTest() {
		Object value = AnnotationUtil.getAnnotationValue(ClassWithAnnotation.class, AnnotationForTest.class);
		System.out.println(value);
	}
	
	@AnnotationForTest("测试")
	class ClassWithAnnotation{
		void hello(){
			System.out.println("Hello");
		}
		
	}
}
