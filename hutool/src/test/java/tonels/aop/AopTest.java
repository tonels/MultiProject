package tonels.aop;

import org.junit.Assert;
import org.junit.Test;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;

/**
 * AOP模块单元测试
 * 
 * @author Looly
 *
 */
public class AopTest {

	@Test
	public void aopTest() {
		Animal cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
		String result = cat.eat();
		System.out.println(result);
	}

	@Test
	public void aopByCglibTest() {
		Dog dog = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
		String result = dog.eat();
		System.out.println(result);
	}

	static interface Animal {
		String eat();
	}

	/**
	 * 有接口
	 * 
	 * @author looly
	 *
	 */
	static class Cat implements Animal {

		@Override
		public String eat() {
			return "猫吃鱼";
		}
	}

	/**
	 * 无接口
	 * 
	 * @author looly
	 *
	 */
	static class Dog {
		public String eat() {
			return "狗吃肉";
		}
	}
}
