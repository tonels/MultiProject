package com.async.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class EmployeeName implements Serializable {


    private static final long serialVersionUID = -1773599508061743940L;

    public String firstName;
    public String lastName;

}
