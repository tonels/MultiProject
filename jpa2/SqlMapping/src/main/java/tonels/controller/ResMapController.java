package tonels.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tonels.sqlResultMap.SubscriptionProjection;
import tonels.sqlResultMap.SubscriptionRepository;
import tonels.sqlResultMap.SubscriptionSummary;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/res")
public class ResMapController {

    @Resource
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/r1")
    public ResultBean r1(){
        List<SubscriptionSummary> allSubscriptionSummaries = subscriptionRepository.findAllSubscriptionSummaries();
        return ResultBean.ok(allSubscriptionSummaries);
    }


    @GetMapping("/r2")
    public ResultBean r2(){
        List<SubscriptionProjection> allSubscriptionProjections = subscriptionRepository.findAllSubscriptionProjections();
        return ResultBean.ok(allSubscriptionProjections);
    }


}
