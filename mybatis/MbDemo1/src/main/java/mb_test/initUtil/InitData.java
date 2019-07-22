package mb_test.initUtil;

import com.google.common.collect.Lists;
import mb_test.model.User;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<User> create(){
        User u1 = new User().setName("测试一").setAge(12).setMoney(215.55);
        User u2 = new User().setName("测试二").setAge(45).setMoney(5685);
        User u3 = new User().setName("测试三").setAge(73).setMoney(3441);
        User u4 = new User().setName("测试四").setAge(38).setMoney(347455);
        User u5 = new User().setName("测试五").setAge(29).setMoney(24741);
        User u6 = new User().setName("测试六").setAge(82).setMoney(96851);

        ArrayList<User> users = Lists.newArrayList(u1, u2, u3, u4, u5, u6);
        return users;
    }




}
