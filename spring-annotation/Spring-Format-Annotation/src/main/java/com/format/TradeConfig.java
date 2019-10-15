package com.format;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@Import(MyViewConfig.class)
public class TradeConfig {

    @Bean
    public TradeController tradeParamRequestController() {
        return new TradeController();
    }

    @Bean
    public TradeService tradeService(){
        return new TradeService();
    }

}