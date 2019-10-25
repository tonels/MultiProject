package com.springjdbc.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Employee {

    private int id;

    private String firstName;

    private String lastName;

    private String address;
}
