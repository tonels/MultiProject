package com.tonels.modelTable.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t1")
public class T1 {

    @Id
    private Long tId;

    private String tName;

    private Integer age;

    private byte sex;

    @Transient
    private List<S1> students;

}
