package tonels.feature.domain;

import java.util.Date;

public class Person1 {
    private Integer id;
    private String name;
    private Date javaDate;

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", birthDate=" + javaDate + "]";
    }

    public Person1(Integer id, String name, Date javaDate) {
        this.id = id;
        this.name = name;
        this.javaDate = javaDate;

    }

    public Person1() {
        // TODO Auto-generated constructor stub
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

    public Date getJavaDate() {
        return javaDate;
    }

    public void setJavaDate(Date javaDate) {
        this.javaDate = javaDate;
    }
}
