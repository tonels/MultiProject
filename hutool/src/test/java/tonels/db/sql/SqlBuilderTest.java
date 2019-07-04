package tonels.db.sql;

import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.LogicalOperator;
import cn.hutool.db.sql.SqlBuilder;
import org.junit.Test;

public class SqlBuilderTest {
	
	@Test
	public void queryNullTest() {
		SqlBuilder builder = SqlBuilder.create().select().from("user").where(new Condition("name", "= null"));
		System.out.println(builder);

		SqlBuilder builder2 = SqlBuilder.create().select().from("user").where(new Condition("name", "is null"));
		System.out.println(builder2);

		SqlBuilder builder3 = SqlBuilder.create().select().from("user").where(LogicalOperator.OR, new Condition("name", "!= null"),new Condition("name","like%as"));
		System.out.println(builder3);

		SqlBuilder builder4 = SqlBuilder.create().select().from("user").where(LogicalOperator.AND, new Condition("name", "is not null"),new Condition("id", "in 12,13,14"));
		System.out.println(builder4);
	}
}
