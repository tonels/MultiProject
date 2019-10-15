package com.format;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Data
public class Trade {

    private long tradeId;

    private String buySell;

    private Currency buyCurrency;

    private Currency sellCurrency;

    @NumberFormat(pattern = "#,###,###,###.##")
    private BigDecimal amount;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date tradeDate;

    @Override
    public String toString () {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buySell='" + buySell + '\'' +
                ", buyCurrency='" + buyCurrency + '\'' +
                ", sellCurrency='" + sellCurrency + '\'' +
                '}';
    }


}
