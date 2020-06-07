package samples.util;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import samples.domain.DataLineage;

import java.util.List;

public class ListTest {

    private List<DataLineage> list;

    @Before
    public void inti() {
        List<DataLineage> ll = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            DataLineage p1 = new DataLineage();
            p1.setId(Long.valueOf(i));
            p1.setSourceDataBaseName("BaseName" + 1);
            p1.setSourceTableName("TableName" + RandomUtil.randomInt(5));
            p1.setSqlQuery("SqlQuery" + RandomUtil.randomInt(5));
            ll.add(p1);
        }
        list = ll;
    }


    @Test
    public void t1() {
        List<DataLineage> distinctDataLineageList = ListUtils.distinctList(list,
                DataLineage::getSourceDataBaseName,
                DataLineage::getSourceTableName);
        distinctDataLineageList.forEach(System.out::println);
    }

    @Test
    public void t2() {
        List<DataLineage> distinctDataLineageList = ListUtils.distinctList(list,
                DataLineage::getSourceDataBaseName,
                DataLineage::getSourceTableName,
                DataLineage::getSqlQuery);
        distinctDataLineageList.forEach(System.out::println);
    }


}
