package tonels.db.sql;

import cn.hutool.db.sql.Condition;
import org.junit.Test;

public class ConditionTest {
	
	@Test
	public void toStringTest() {
		Condition c1 = new Condition("name", null);
		System.out.println(c1);
		Condition c2 = new Condition("birthday", "!= null");
		System.out.println(c2);
		Condition c3 = new Condition("name", "= zhangsan");
		System.out.println(c3);
		Condition c4 = new Condition("name", "like %aaa");
		System.out.println(c4);
		Condition c5 = new Condition("id", "in 1,2,3");
		System.out.println(c5);
		Condition c6 = new Condition("age", "between 12 and 13");
		System.out.println(c6);
	}
	
	@Test
	public void toStringNoPlaceHolderTest() {
		Condition conditionNull = new Condition("user", null);
		conditionNull.setPlaceHolder(false);
		System.out.println(conditionNull);

		Condition conditionNotNull = new Condition("user", "!= null");
		conditionNotNull.setPlaceHolder(false);
		System.out.println(conditionNotNull);

		Condition conditionEquals = new Condition("user", "= zhangsan");
		conditionEquals.setPlaceHolder(false);
		System.out.println(conditionEquals);

		Condition conditionLike = new Condition("user", "like %aaa");
		conditionLike.setPlaceHolder(false);
		System.out.println(conditionLike);

		Condition conditionIn = new Condition("user", "in 1,2,3");
		conditionIn.setPlaceHolder(false);
		System.out.println(conditionIn);

		Condition conditionBetween = new Condition("user", "between 12 and 13");
		conditionBetween.setPlaceHolder(false);
		System.out.println(conditionBetween);
	}
}
