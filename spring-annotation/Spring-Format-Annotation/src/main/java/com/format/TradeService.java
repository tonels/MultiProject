package com.format;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TradeService {
    private HashMap<Long, Trade> trades = new HashMap<>();

    @PostConstruct
    private void postConstruct () {
        //just populating with some dummy data
        //in real application will get the data from a database
        List<Currency> ccy = new ArrayList(Currency.getAvailableCurrencies());
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        for (int i = 1; i <= 100; i++) {
            Trade trade = new Trade();
            trade.setTradeId(i);
            trade.setBuySell(Math.random() > 0.5 ? "Buy" : "Sell");
            trade.setBuyCurrency(ccy.get(rnd.nextInt(0, ccy.size())));
            if (i == 1) {
                trade.setBuySell("Buy");
                trade.setBuyCurrency(Currency.getInstance("USD"));
            }
            trade.setSellCurrency(ccy.get(rnd.nextInt(0, ccy.size())));
            trade.setAmount(new BigDecimal(rnd.nextDouble(1000, 100000)));
            trade.setTradeDate(Date.from(LocalDate.now()
                    .minusMonths(rnd.nextInt(10, 100))
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()));
            trades.put((long) i, trade);
        }
    }

    public Trade getTradeById (long id) {
        return trades.get(id);

    }

    public List<Trade> getAllTrades () {
        return new ArrayList<>(trades.values());
    }
}