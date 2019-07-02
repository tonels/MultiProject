package tonels.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tonels.model.State;
import utils.ExcelUtil;
import utils.ResultBean;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping("/tomysql")
    public ResultBean tomysql(@RequestBody MultipartFile file){

        Object o = ExcelUtil.importFile(file, 1);
        List<State> states = (List<State>) o;
        return ResultBean.ok(states);
    }

    @GetMapping("/tomysql2")
    public ResultBean tomysql(){
        InputStream is = null;
        try {
        is = new BufferedInputStream(new FileInputStream("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        Map<String, Object> map = new HashMap<>();
        List<Object> read1 = EasyExcelFactory.read(is, new Sheet(1, 1));

        for (Object o : read1) {
            Object[] o1 = (Object[]) o;

            List<Object> collect = Arrays.stream(o1).collect(Collectors.toList());
            System.out.println(collect);
            System.out.println("=====================");

        }


        Object read = read1;
    List<State> states = (List<State>) read;
        System.out.println(states);

        return ResultBean.ok(states);
    }



}
