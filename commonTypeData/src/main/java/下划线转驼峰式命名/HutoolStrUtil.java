package 下划线转驼峰式命名;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HutoolStrUtil {

    private static List<String> underscope;
    private static List<String> camelCase;

    static {
        underscope = Lists.newArrayList(
                "produce_id",
                "gk_id",
                "asset_id",
                "video_id",
                "video_name",
                "produce_status",
                "produce_reason",
                "produce_start_time",
                "produce_end_time",
                "publish_status",
                "publish_reason",
                "publish_start_time",
                "publish_end_time",
                "operator",
                "operator_name",
                "node_status",
                "collect_time"
        );
    }

       static {
            camelCase =  Lists.newArrayList(
               "produceId",
               "gkId",
               "assetId",
               "videoId",
               "videoName",
               "produceStatus",
               "produceReason",
               "produceStartTime",
               "produceEndTime",
               "publishStatus",
               "publishReason",
               "publishStartTime",
               "publishEndTime",
               "operator",
               "operatorName",
               "nodeStatus",
               "collectTime"

        );
    }

    @Test
    public void test1(){
        List<String> collect = underscope.stream().map(e -> StrUtil.toCamelCase(e)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<String> collect = camelCase.stream().map(e -> StrUtil.toUnderlineCase(e)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }



}
