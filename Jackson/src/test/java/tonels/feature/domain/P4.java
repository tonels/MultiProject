package tonels.feature.domain;

import java.time.LocalDateTime;

public class P4 {

    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private LocalDateTime birth;

    public P4(Integer id, String name, Integer age, String sex,LocalDateTime birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birth = birth;
    }

    public P4(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public P4(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }
}
