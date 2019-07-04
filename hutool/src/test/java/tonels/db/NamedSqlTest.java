package tonels.db;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.hutool.core.map.MapUtil;
import cn.hutool.db.sql.NamedSql;

public class NamedSqlTest {

	@Test
	public void parseTest() {
		String sql = "select * from table where id=@id and name = @name1 and nickName = :subName";

		Map<String, Object> paramMap = MapUtil.builder("name1", (Object)"张三").put("age", 12).put("subName", "小豆豆").build();

		NamedSql namedSql = new NamedSql(sql, paramMap);
		String sql1 = namedSql.getSql();
		List<Object> paramList = namedSql.getParamList();

		System.out.println(sql1);
		System.out.println(paramList);
		System.out.println(namedSql);
	}
	
	@Test
	public void parseTest2() {
		String sql = "select * from table where id=@id and name = @name1 and nickName = :subName";
		
		Map<String, Object> paramMap = MapUtil.builder("name1", (Object)"张三").put("age", 12).put("subName", "小豆豆").put("id", null).build();
		
		NamedSql namedSql = new NamedSql(sql, paramMap);
		System.out.println(namedSql.getSql());

	}
}
