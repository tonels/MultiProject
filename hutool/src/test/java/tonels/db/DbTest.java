package tonels.db;

import java.sql.SQLException;
import java.util.List;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cn.hutool.core.lang.func.VoidFunc1;
import cn.hutool.db.sql.Condition;

/**
 * Db对象单元测试
 * @author looly
 *
 */
public class DbTest {

	@Test
	public void findTest() throws SQLException {
		Db db = Db.use("mysql");

		List<Entity> find = db.find(Entity.create("user").set("age", 18));
		Assert.assertEquals("王五", find.get(0).get("name"));
	}
	
	@Test
	public void findByTest() throws SQLException {
		Db.use("mysql");
		
		List<Entity> lt = Db.use("mysql").findBy("user",
				Condition.parse("age", "> 18"), 
				Condition.parse("age", "< 100")
		);
		lt.forEach(System.out::print);
	}
	
	@Test
	@Ignore
	public void txTest() throws SQLException {
		Db.use("mysql").tx(new VoidFunc1<Db>() {
			
			@Override
			public void call(Db db) throws SQLException {
				db.insert(Entity.create("user").set("name", "unitTestUser2"));
				db.update(Entity.create().set("age", 79), Entity.create("user").set("name", "unitTestUser2"));
				db.del("user", "name", "unitTestUser2");
			}
		});
	}
}
