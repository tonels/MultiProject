package tonels.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class OffEmpCustVo {

    private String officeCode;

    private String offCountry;

    private String offState;

    private Integer emploNumber;

    private String emploEmail;

    private Integer custNumber;

    private String custState;


    public OffEmpCustVo(String officeCode, String offCountry, String offState, Integer emploNumber, String emploEmail, Integer custNumber, String custState) {
        this.officeCode = officeCode;
        this.offCountry = offCountry;
        this.offState = offState;
        this.emploNumber = emploNumber;
        this.emploEmail = emploEmail;
        this.custNumber = custNumber;
        this.custState = custState;
    }
}
