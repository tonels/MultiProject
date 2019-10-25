package com.tonels.modelTable.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sBirthday;

    private String sex;

}
