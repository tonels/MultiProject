package querydsl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

// 查询参数
@Data
@AllArgsConstructor
public class OffEmpCustParamsVo {

    private String offState;

    private Integer emploNumber;

    private String custState;

}
