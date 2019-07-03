package tonels.excel;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import tonels.model.State;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {

        List<State> states = JSONUtil.toList(new JSONArray("[" +
                "{\"capitalen\":\"A\",\"code\":\"C\",\"enName\":\"D\",\"remark\":\"F\",\"id\":1,\"capital\":\"B\",\"chName\":\"E\"}," +
                "{\"capitalen\":\"A\",\"code\":\"C\",\"enName\":\"D\",\"remark\":\"F\",\"id\":1,\"capital\":\"B\",\"chName\":\"E\"}," +
                "{\"capitalen\":\"A\",\"code\":\"C\",\"enName\":\"D\",\"remark\":\"F\",\"id\":1,\"capital\":\"B\",\"chName\":\"E\"}," +
                "{\"capitalen\":\"A\",\"code\":\"C\",\"enName\":\"D\",\"remark\":\"F\",\"id\":1,\"capital\":\"B\",\"chName\":\"E\"}," +
                "{\"capitalen\":\"A\",\"code\":\"C\",\"enName\":\"D\",\"remark\":\"F\",\"id\":1,\"capital\":\"B\",\"chName\":\"E\"}" +
                "]"), State.class);
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\ZhengYuan\\Desktop\\asd.xlsx","第一栏");
        writer.addHeaderAlias("capitalen","首都英文")
                .addHeaderAlias("id","编号")
                .addHeaderAlias("code","编码")
                .addHeaderAlias("enName","英文名")
                .addHeaderAlias("remark","简介")
                .addHeaderAlias("capital","首都中文")
                .addHeaderAlias("chName","中文名");
//        ExcelWriter write = writer.write(states,false);// 这里是不写文件头行（标题）
        ExcelWriter write = writer.write(states);
        write.getStyleSet().setFont(HSSFColor.GREEN.index, Font.COLOR_NORMAL,"阿三大苏打",true);// 这里是设置
        write.setSheet("第一栏");
        write.getHeadCellStyle().setFillBackgroundColor(HSSFColor.BLUE.index);
       write.close();


    }

}
