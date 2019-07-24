package tonels.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.CharsetUtil;

import tonels.json.test.bean.Exam;
import tonels.json.test.bean.JsonNode;
import tonels.json.test.bean.KeyBean;

/**
 * JSONArray单元测试
 * 
 * @author Looly
 *
 */
public class JSONArrayTest {

	@Test
	public void addTest() {
		// 方法1
		JSONArray array = JSONUtil.createArray();
		// 方法2
		// JSONArray array = new JSONArray();
		array.add("value1");
		array.add("value2");
		array.add("value3");

		Assert.assertEquals(array.get(0), "value1");
	}

	@Test
	public void parseTest() {
		String jsonStr = "[\"value1\", \"value2\", \"value3\"]";
		JSONArray array = JSONUtil.parseArray(jsonStr);
		Assert.assertEquals(array.get(0), "value1");
	}

	// 直接读取 Json文件，然后转成对象
	@Test
	public void parseFileTest() {
		JSONArray array = JSONUtil.readJSONArray(FileUtil.file("D:\\GitRepository\\multiProject\\hutool\\src\\main\\resources\\json/exam_test.json"), CharsetUtil.CHARSET_UTF_8);

		List<Exam> exams = JSONUtil.toList(array, Exam.class);
		System.out.println(exams);
	}

	@Test
	@Ignore
	public void parseBeanListTest() {
		KeyBean b1 = new KeyBean();
		b1.setAkey("aValue1");
		b1.setBkey("bValue1");
		KeyBean b2 = new KeyBean();
		b2.setAkey("aValue2");
		b2.setBkey("bValue2");

		ArrayList<KeyBean> list = CollUtil.newArrayList(b1, b2);
		System.out.println(list);

	}

	@Test
	public void toListTest() {
		String jsonStr = FileUtil.readString("D:\\GitRepository\\multiProject\\hutool\\src\\main\\resources\\json/exam_test.json", CharsetUtil.CHARSET_UTF_8);
		JSONArray array = JSONUtil.parseArray(jsonStr);

		List<Exam> exams = JSONUtil.toList(array, Exam.class);
		System.out.println(exams);
	}

	@Test
	public void toListTest2() {
		String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
		
		JSONArray array = JSONUtil.parseArray(jsonArr);
		List<User> userList = JSONUtil.toList(array, User.class);
		
		Assert.assertFalse(userList.isEmpty());
		Assert.assertEquals(User.class, userList.get(0).getClass());
		
		Assert.assertEquals(Integer.valueOf(111), userList.get(0).getId());
		Assert.assertEquals(Integer.valueOf(112), userList.get(1).getId());
		
		Assert.assertEquals("test1", userList.get(0).getName());
		Assert.assertEquals("test2", userList.get(1).getName());
	}
	
	@Test
	public void toDictListTest() {
		String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
		
		JSONArray array = JSONUtil.parseArray(jsonArr);
		
		List<Dict> list = JSONUtil.toList(array, Dict.class);
		System.out.println(list);

	}

	@Test
	public void toArrayTest() {
		String jsonStr = FileUtil.readString("exam_test.json", CharsetUtil.CHARSET_UTF_8);
		JSONArray array = JSONUtil.parseArray(jsonStr);

		Exam[] list = array.toArray(new Exam[0]);
		Assert.assertFalse(0 == list.length);
		Assert.assertEquals(Exam.class, list[0].getClass());
	}

	/**
	 * 单元测试用于测试在列表元素中有null时的情况下是否出错
	 */
	@Test
	public void toListWithNullTest() {
		String json = "[null,{'akey':'avalue','bkey':'bvalue'}]";
		JSONArray ja = JSONUtil.parseArray(json);

		List<KeyBean> list = ja.toList(KeyBean.class);
		Assert.assertTrue(null == list.get(0));
		Assert.assertEquals("avalue", list.get(1).getAkey());
		Assert.assertEquals("bvalue", list.get(1).getBkey());
	}

	@Test
	public void toBeanListTest() {
		List<Map<String, String>> mapList = new ArrayList<>();
		mapList.add(buildMap("0", "0", "0"));
		mapList.add(buildMap("1", "1", "1"));
		mapList.add(buildMap("+0", "+0", "+0"));
		mapList.add(buildMap("-0", "-0", "-0"));
		JSONArray jsonArray = JSONUtil.parseArray(mapList);
		List<JsonNode> nodeList = jsonArray.toList(JsonNode.class);

	}

	private static Map<String, String> buildMap(String id, String parentId, String name) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("parentId", parentId);
		map.put("name", name);
		return map;
	}
	
	class User {
		private Integer id;
		private String name;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
}
