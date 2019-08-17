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

                "asset_id",
                "gk_id",
                "group_id",
                "task_id",
                "video_type",
                "form_type",
                "category",
                "asset_name",
                "sc_media_md5",
                "upload_user_type",
                "serial_asset_id",
                "serial_asset_name",
                "serial_count",
                "serial_sequence",
                "upload_user_id",
                "upload_user_name",
                "created_time",
                "display_name",
                "short_name",
                "country",
                "assist",
                "content_form",
                "keywords",
                "occurred",
                "belong_name",
                "online_time",
                "mpid",
                "mpid_name",
                "captionLang",
                "lang",
                "platform",
                "source",
                "is_urgency",
                "logo",
                "duration",
                "base_code_url",
                "recommendation",
                "content_id",
                "description",
                "copyright_information",
                "asset_status",
                "business_type"
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
