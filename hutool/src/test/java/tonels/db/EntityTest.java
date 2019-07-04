package tonels.db;

import cn.hutool.db.Entity;
import org.junit.Test;
import tonels.db.pojo.User;

/**
 * Entity测试
 * 
 * @author looly
 *
 */
public class EntityTest {
	
	@Test
	public void parseTest() {
		User user = new User();
		user.setId(1);
		user.setName("test");
		
		Entity entity = Entity.create("user").parseBean(user);
		System.out.println(entity);


	}
	
	@Test
	public void parseTest2() {
		User user = new User();
		user.setId(1);
		user.setName("test");
		
		Entity entity = Entity.create().parseBean(user);
		System.out.println(entity);
	}
	
	@Test
	public void entityToBeanIgnoreCaseTest() {
		Entity entity = Entity.create().set("ID", 2).set("NAME", "testName");
		User user = entity.toBeanIgnoreCase(User.class);
		System.out.println(user);
	}
	@Test
	public void entityToBeanIgnoreCasseTest() {
		Entity entity = Entity.create().addFieldNames("as").addFieldNames("da").set("as",User.class);
		User user = entity.toBeanIgnoreCase(User.class);
		System.out.println(user);
	}
}
