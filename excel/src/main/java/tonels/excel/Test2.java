package tonels.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import tonels.model.State;

import java.util.List;

public class Test2 {

    public static void main(String[] args) {

        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx");

        List<State> read = reader.read(0, 1, State.class);
        System.out.println(read);

    }




}
