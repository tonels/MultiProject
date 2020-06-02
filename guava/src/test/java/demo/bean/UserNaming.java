package demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNaming {
    String Name;
    String email_of_developer;
    boolean isDeveloper;
    int _ageOfDeveloper;
}