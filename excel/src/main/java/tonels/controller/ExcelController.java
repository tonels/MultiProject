package tonels.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tonels.model.City;
import tonels.model.State;
import tonels.repo.CityRepo;
import tonels.repo.ExcelRepo;
import utils.ExcelUtil;
import utils.ResultBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/toexcel1")
        public ResultBean to1(){
            List<City> all = cityRepo.findAll();
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter();
//        writer.
        return ResultBean.ok();
    }


    @GetMapping("/capt")
    public ResultBean capt(HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.write(response.getOutputStream());
        String imageBase64 = lineCaptcha.getImageBase64();
        System.out.println(imageBase64);
        return ResultBean.ok();
    }



}
