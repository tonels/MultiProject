package demo5;

import cn.hutool.json.JSONUtil;
import example.users.User;
import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.capitalize;

public class BasicTest {

    @Test
    public void t1() throws Exception {
        Resource resource = new ClassPathResource("randomuser.me.csv");

        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
        reader.setResource(resource);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>();
        lineMapper.setFieldSetMapper(new FieldSetMapper<User>() {
            @Override
            public User mapFieldSet(FieldSet fields) throws BindException {

                User user = new User();

                user.setEmail(fields.readString("email"));
                user.setFirstname(capitalize(fields.readString("first")));
                user.setLastname(capitalize(fields.readString("last")));
                user.setNationality(fields.readString("nationality"));

                String city = Arrays.stream(fields.readString("city").split(" "))//
                        .map(StringUtils::capitalize)//
                        .collect(Collectors.joining(" "));
                String street = Arrays.stream(fields.readString("street").split(" "))//
                        .map(StringUtils::capitalize)//
                        .collect(Collectors.joining(" "));

                try {
                    user.setAddress(new User.Address(city, street, fields.readString("zip")));
                } catch (IllegalArgumentException e) {
                    user.setAddress(new User.Address(city, street, fields.readString("postcode")));
                }

                user.setPicture(
                        new User.Picture(fields.readString("large"), fields.readString("medium"), fields.readString("thumbnail")));
                user.setUsername(fields.readString("username"));
                user.setPassword(fields.readString("password"));

                return user;
            }
        });

        lineMapper.setLineTokenizer(tokenizer);

        reader.setLineMapper(lineMapper);
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        reader.setLinesToSkip(1);
        reader.open(new ExecutionContext());

        List<User> users = new ArrayList<>();
        User user = null;

        do {

            user = reader.read();

            if (user != null) {
                users.add(user);
            }

        } while (user != null);

        List<User> aa = users.subList(0, 3);

        System.out.println(JSONUtil.toJsonStr(aa));
    }


    @Test
    public void t2(){
        System.out.println("".toString());
    }

}
