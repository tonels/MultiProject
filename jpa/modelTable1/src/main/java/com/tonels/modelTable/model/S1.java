package com.tonels.modelTable.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "s1")
public class S1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sId;

    @Version
    private Integer version;

    private String sName;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate sBirthday;

    private String sex;

}
