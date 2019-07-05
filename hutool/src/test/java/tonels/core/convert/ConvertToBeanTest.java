package tonels.core.convert;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.convert.Convert;
import org.junit.Assert;
import org.junit.Test;
import tonels.core.bean.BeanUtilTest;


/**
 * 类型转换工具单元测试<br>
 * 转换为数组
 * 
 * @author Looly
 *
 */
public class ConvertToBeanTest {

	@Test
	public void beanToMapTest() {
		BeanUtilTest.SubPerson person = new BeanUtilTest.SubPerson();
		person.setAge(14);
		person.setOpenid("11213232");
		person.setName("测试A11");
		person.setSubName("sub名字");
		
		Map<?, ?> map = Convert.convert(Map.class, person);
		Assert.assertEquals(map.get("name"), "测试A11");
		Assert.assertEquals(map.get("age"), 14);
		Assert.assertEquals("11213232", map.get("openid"));
	}
	
	@Test
	public void mapToBeanTest() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", "88dc4b28-91b1-4a1a-bab5-444b795c7ecd");
		map.put("age", 14);
		map.put("openid", "11213232");
		map.put("name", "测试A11");
		map.put("subName", "sub名字");
		
		BeanUtilTest.SubPerson subPerson = Convert.convert(BeanUtilTest.SubPerson.class, map);
		Assert.assertEquals("88dc4b28-91b1-4a1a-bab5-444b795c7ecd", subPerson.getId().toString());
		Assert.assertEquals(14, subPerson.getAge());
		Assert.assertEquals("11213232", subPerson.getOpenid());
		Assert.assertEquals("测试A11", subPerson.getName());
		Assert.assertEquals("11213232", subPerson.getOpenid());
	}
}
