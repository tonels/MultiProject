package tonels.model;

import lombok.Data;
import tonels.enums.Status;

import javax.persistence.*;

@Entity
@Data
public class Atest {

    public static enum AccountType {
        TEACHER,//老师
        STUDENT;//学生
    }


    @Id
   private Integer id;

   private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type ")
    private AccountType accountType = AccountType.TEACHER ;
}
