package tonels.db;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import tonels.db.pojo.User;

/**
 * Entity测试
 * 
 * @author looly
 *
 */
public class FindBeanTest {

	Db db;

	@Before
	public void init() {
		db = Db.use("mysql");
	}

	@Test
	public void findAllBeanTest() throws SQLException {
		List<User> results = db.findAll(Entity.create("user"), User.class);
		results.forEach(System.out::print);
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void findAllListTest() throws SQLException {
		List<List> results = db.findAll(Entity.create("user"), List.class);
		results.forEach(System.out::print);
	}
	
	@Test
	public void findAllArrayTest() throws SQLException {
		List<Object[]> results = db.findAll(Entity.create("user"), Object[].class);
		results.forEach(System.out::print);
	}
	
	@Test
	public void findAllStringTest() throws SQLException {
		List<String> results = db.findAll(Entity.create("user"), String.class);
		System.out.println(results);
		results.forEach(System.out::print);
	}
	
	@Test
	public void findAllStringArrayTest() throws SQLException {
		List<String[]> results = db.findAll(Entity.create("user"), String[].class);
		System.out.println(results);
		results.forEach(System.out::print);
	}
}
