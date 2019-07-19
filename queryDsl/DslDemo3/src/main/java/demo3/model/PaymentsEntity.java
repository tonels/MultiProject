package demo3.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "payments", schema = "classicmodels", catalog = "")
@IdClass(PaymentsEntityPK.class)
public class PaymentsEntity {

    @Id
    @Column(name = "customerNumber")
    private Integer customerNumber;

    @Id
    @Column(name = "checkNumber")
    private String checkNumber;

    @Basic
    @Column(name = "paymentDate")
    private Date paymentDate; // 这里不用localDateTime因为数据库中只存了年-月-日

    @Basic
    @Column(name = "amount")
    private BigDecimal amount;

}
