package com.async.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class EmployeeAddress implements Serializable {

    private static final long serialVersionUID = 9089911519863129623L;

    public String streetNo;
    public String houseNo;
    public String zipCode;


}
