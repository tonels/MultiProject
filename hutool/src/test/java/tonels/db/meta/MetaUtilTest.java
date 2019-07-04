package tonels.db.meta;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

/**
 * 元数据信息单元测试
 * @author Looly
 */
public class MetaUtilTest {
	// TODO: 2019/7/4  // 指定数据库的方言组，我是在Mysql上测试的，选择 Mysql
	DataSource ds = DSFactory.get("mysql");

	@Test
	public void getTablesTest() {
		List<String> tables = MetaUtil.getTables(ds);
		// TODO: 2019/7/4  tables = [Atest, author, book, city, state, user]
	}

	@Test
	public void getTableMetaTest() {
		// TODO: 2019/7/4 这里我想看看这个 table 的基础数据
		Table table = MetaUtil.getTableMeta(ds, "user");

		System.out.println(JSONUtil.parse(table));

/*
{
"pkNames": [],
"columns": [
	{
		"size": 10,
		"isNullable": true,
		"name": "id",
		"typeName": "INT",
		"comment": "",
		"type": 4,
		"tableName": "user"
	},
	{
		"size": 20,
		"isNullable": true,
		"name": "name",
		"typeName": "VARCHAR",
		"comment": "",
		"type": 12,
		"tableName": "user"
	},
	{
		"size": 10,
		"isNullable": true,
		"name": "birthday",
		"typeName": "DATE",
		"comment": "",
		"type": 91,
		"tableName": "user"
	},
	{
		"size": 2,
		"isNullable": true,
		"name": "gender",
		"typeName": "VARCHAR",
		"comment": "",
		"type": 12,
		"tableName": "user"
	}
		],
"comment": "",
"tableName": "user"
}

*/

	}
}
