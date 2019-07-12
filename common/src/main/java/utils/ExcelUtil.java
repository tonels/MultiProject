package utils;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Description 表格导入导出工具类
 * @Author wangpeijin
 * @Date 2019/6/3 11:10
 * @Version 1.0
 **/
@Slf4j
public class ExcelUtil {

    /**
     * @param response, fileName, list, aClsss
     * @Description 报表数据导出
     * @Date 2019/6/3 11:11
     * @auther wangpeijin
     * @return:void
     **/
    public static void export(HttpServletResponse response, String fileName, List<? extends BaseRowModel> list, Class<? extends BaseRowModel> aClsss) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("GB2312"), "ISO_8859_1"));
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet = new Sheet(1, 0, aClsss);
            writer.write(list, sheet);
            writer.finish();
        } catch (Exception e) {
            log.error("【报表导出】---报表:{}导出异常:", fileName, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("【报表导出】---报表:{}导出异常:", fileName, e);
            }
        }

    }

    /**
     * @param filePath, fileName, list, aClsss
     * @Description 报表数据导出到文件中
     * @Date 2019/6/3 11:11
     * @auther wangpeijin
     * @return:void
     **/
    public static void export2File(String filePath, List<? extends BaseRowModel> list, Class<? extends BaseRowModel> aClsss) {
        OutputStream out = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                FileUtil.mkParentDirs(file);
            }
            out = new FileOutputStream(filePath);
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet = new Sheet(1, 0, aClsss);
            writer.write(list, sheet);
            writer.finish();
        } catch (Exception e) {
            log.error("【报表导出】---报表路径:{}导出异常:", filePath, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("【报表导出】---报表路径:{}导出异常:", filePath, e);
            }
        }
    }


    /**
     * @param file, rowNum 从第几行读
     * @Description 报表数据导入
     * @Date 2019/6/3 13:49
     * @auther wangpeijin
     * @return:java.util.List<java.lang.Object>
     **/
    public static Object importFile(final MultipartFile file, int rowNum) {

        Object list = null;
        InputStream in = null;
        try {
            in = file.getInputStream();
            list = EasyExcelFactory.read(in, new Sheet(1, rowNum));
        } catch (IOException e) {
            log.error("【报表导入】---报表:{}导入异常:", file.getOriginalFilename(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("【报表导入】---报表:{}导入异常:", file.getOriginalFilename(), e);
                }
            }
        }
        return list;
    }

}
