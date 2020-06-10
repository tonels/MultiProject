package tonels.domian;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 介质流水VO
 */

public class Vo21 {

    private String createUser;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
//
//    @Override
//    public String toString() {
//        return "Vo21{" +
//                "createUser='" + createUser + '\'' +
//                ", createDate=" + createDate +
//                '}';
//    }
}
