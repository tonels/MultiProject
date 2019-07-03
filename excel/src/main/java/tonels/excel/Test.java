package tonels.excel;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import tonels.model.State;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {


        List<Object> objects = excel1();
        System.out.println(objects);

    }

    public static List<Map<String,Object>>excelMap(int sheetNum,int rowNum) throws FileNotFoundException {
        List<Object> read = EasyExcelFactory.read(new BufferedInputStream(new FileInputStream("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx")), new Sheet(sheetNum, rowNum));
        List<List> collect = read.stream().map(o -> (List) o).collect(Collectors.toList());
//        HashMap<String, Object> ohm = new HashMap<>();
        return null;
}

    public static List<Object> excel1() throws FileNotFoundException {
        InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx"));
        List<Object> read1 = EasyExcelFactory.read(is, new Sheet(1, 1));
//        List<State> states = (List<State>) read1;
        return read1;
    }

    public static List<State> excel2() {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream("C:\\Users\\ZhengYuan\\Desktop\\model.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Map<String, Object> map = new HashMap<>();
        List<State> list = Lists.newArrayList();
        List<Object> read1 = EasyExcelFactory.read(is, new Sheet(1, 1));


        for (Object o : read1) {
            System.out.println(o);
            List o_1 = (List) o;
            State state = new State();

            String o1 = o_1.get(0).toString();
            String o2 = o_1.get(1).toString();
            String o3 = o_1.get(2).toString();
            String o4 = o_1.get(3).toString();
            String o5 = o_1.get(4).toString();
            String o6 = o_1.get(5).toString();

            state.setId(Integer.parseInt(o1)).setChName(o2).setEnName(o3).setCode(o4).setCapital(o5).setCapitalen(o6);
            list.add(state);
        }

        return list;
    }


    public static void excel3() {
        State state = new State();
        State state1 = state.setId(1).setCapitalen("A").setCapital("B").setCode("C").setEnName("D").setChName("E").setRemark("F");
        System.out.println("state对象数据格式为:"+ state1);
        System.out.println("state对象JsonUtil数据格式为:"+ JSONUtil.parse(state1));


        System.out.println("================");

        List<State> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(state1);
        }
        System.out.println("List" + list);
        System.out.println(JSONUtil.toJsonStr(list));
        System.out.println("================");


        int[] a = {1,3,2,4,4};
        String[] b = {"s","a","f","g","h",};
        System.out.println("a" + JSONUtil.toJsonStr(a));
        System.out.println("b" + JSONUtil.parse(b));

        Object o = new Object();
        Object state11 = state1;
        System.out.println(state11);
        System.out.println(JSONUtil.parse(state11));
        System.out.println("================");

        HashMap<String, Object> hp = Maps.newHashMap();
        hp.put("a",list.get(0));
        hp.put("b",list.get(1));
        hp.put("c",list.get(2));

        System.out.println(hp.toString());
        String x = JSONUtil.toJsonStr(hp);
        System.out.println(x);
        JSONObject jsonObject = JSONUtil.parseFromMap(hp);
        System.out.println(jsonObject);


    }









}



