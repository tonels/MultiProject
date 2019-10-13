package com.async.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeName implements Serializable {


    private static final long serialVersionUID = -1773599508061743940L;

    public String firstName;
    public String lastName;

}
