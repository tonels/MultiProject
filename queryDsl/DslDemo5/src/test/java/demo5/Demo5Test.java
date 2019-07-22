package demo5;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import example.users.Application;
import example.users.User;
import example.users.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.capitalize;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo5Test {

    @javax.annotation.Resource
    private UserRepository userRepository;

    private List<User> list;

    @Before
    public void init() {
        list = JSONUtil.toList(new JSONArray("[{\"firstname\":\"Walter\",\"password\":\"pilot\",\"address\":{\"zip\":\"76274\",\"city\":\"Nashville\",\"street\":\"7857 Washington Ave\"},\"nationality\":\"US\",\"email\":\"walter.williams36@example.com\",\"picture\":{\"small\":\"http://api.randomuser.me/portraits/thumb/men/25.jpg\",\"large\":\"http://api.randomuser.me/portraits/men/25.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/25.jpg\"},\"username\":\"silverdog66\",\"lastname\":\"Williams\"},{\"firstname\":\"Bernard\",\"password\":\"qwaszx\",\"address\":{\"zip\":\"86420\",\"city\":\"St. Louis\",\"street\":\"1357 E Center St\"},\"nationality\":\"US\",\"email\":\"bernard.mitchell74@example.com\",\"picture\":{\"small\":\"http://api.randomuser.me/portraits/thumb/men/53.jpg\",\"large\":\"http://api.randomuser.me/portraits/men/53.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/53.jpg\"},\"username\":\"redbird658\",\"lastname\":\"Mitchell\"},{\"firstname\":\"Arthur\",\"password\":\"devils\",\"address\":{\"zip\":\"71741\",\"city\":\"Lakeland\",\"street\":\"9203 James St\"},\"nationality\":\"US\",\"email\":\"arthur.simpson31@example.com\",\"picture\":{\"small\":\"http://api.randomuser.me/portraits/thumb/men/98.jpg\",\"large\":\"http://api.randomuser.me/portraits/men/98.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/98.jpg\"},\"username\":\"ticklishfrog641\",\"lastname\":\"Simpson\"}]"), User.class);
    }

    @Test
    public void save(){
        User user = new User().setUsername("aa").setEmail("1255.@com").setFirstname("as").setLastname("tr").setPassword("1324");
         userRepository.saveAll(list);


    }




}
