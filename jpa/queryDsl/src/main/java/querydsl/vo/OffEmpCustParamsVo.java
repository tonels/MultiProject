package querydsl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 查询条件
 */

@Data
@AllArgsConstructor
public class OffEmpCustParamsVo {

    private String offState;

    private Integer emploNumber;

    private String custState;

}
