package demo.bean;

import com.google.gson.annotations.SerializedName;

public class SomeObject2 {

    @SerializedName("custom_naming")
    private final String someField;

    private final String someOtherField;

    public SomeObject2(String a, String b) {
        this.someField = a;
        this.someOtherField = b;
    }
}
