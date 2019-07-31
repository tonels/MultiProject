package springbootdemo.com.model;

public class Book {
    String name;
    String pirce;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPirce() {
        return pirce;
    }

    public void setPirce(String pirce) {
        this.pirce = pirce;
    }

    public Book(String name, String pirce) {
        this.name = name;
        this.pirce = pirce;
    }
}
