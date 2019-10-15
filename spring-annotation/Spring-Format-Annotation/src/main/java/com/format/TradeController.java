package com.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;


    @InitBinder
    private void customizeBinding (@PathVariable("tradeId") long tradeId, WebDataBinder binder) {
        Trade trade = tradeService.getTradeById(tradeId);
        if (trade == null) {
            return;
        }

        CurrencyStyleFormatter currencyFormatter = new CurrencyStyleFormatter();
        currencyFormatter.setCurrency(
                "Buy".equals(trade.getBuySell()) ? trade.getBuyCurrency() : trade
                        .getSellCurrency());
        binder.addCustomFormatter(currencyFormatter, "amount");
    }


    @RequestMapping("/{tradeId:\\d+}")
    public String handleTradeRequest (@PathVariable("tradeId") long tradeId, Model model) {
        Trade trade = tradeService.getTradeById(tradeId);
        if (trade == null) {
            model.addAttribute("msg", "No trade found");
            return "no-trade-page";
        }
        model.addAttribute("trade", trade);
        return "trade-page";
    }
}
