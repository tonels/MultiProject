package com.async.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Trade {

    private long tradeId;

    private String buySell;

    @NumberFormat(pattern = "#,###,###,###.##")
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tradeDate;

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buySell='" + buySell + '\'' +
                ", amount=" + amount +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
