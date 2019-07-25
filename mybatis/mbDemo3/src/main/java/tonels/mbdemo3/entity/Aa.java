package tonels.mbdemo3.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
public class Aa {
    @Size(min = 1,max = 50,message = " name字符在 1-50 之间")
    private String name;

    @Length(min = 1,max = 50,message = "na字符在 1-50 之间")
    private String na;

    private Long age;
}
