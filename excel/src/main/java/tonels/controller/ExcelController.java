package tonels.controller;

import cn.hutool.poi.excel.ExcelReader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tonels.model.City;
import tonels.model.State;
import tonels.repo.CityRepo;
import tonels.repo.ExcelRepo;
import utils.ExcelUtil;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/excel")
public class ExcelController {


    @Resource
    private ExcelRepo excelRepo;
    @Resource
    private CityRepo cityRepo;

    @PostMapping("/tomysql")
    public ResultBean tomysql(@RequestBody MultipartFile file){

        Object o = ExcelUtil.importFile(file, 1);
        List<State> states = (List<State>) o;
        return ResultBean.ok(states);

    }

    @GetMapping("/tomysql2")
    public ResultBean tomysql2(){
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx");
        List<State> list = reader.read(0, 1, State.class);
        try {
            List<State> all = excelRepo.saveAll(list);
        } catch (Exception e) {
        }
        return ResultBean.ok();
    }

    @GetMapping("/tomysql3")
    public ResultBean tomysql3(){
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader("C:\\Users\\ZhengYuan\\Desktop\\model1.xlsx", 1);
        List<City> read = reader.read(0, 1, City.class);
        try {
            List<City> all = cityRepo.saveAll(read);
        } catch (Exception e) {
        }
        return ResultBean.ok();
    }




}
