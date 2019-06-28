package tette;

import tonels.sqlResultMap.SubscriptionSummary;

public class T1 {
    public static void main(String[] args) {
        SubscriptionSummary subscriptionSummary = new SubscriptionSummary("",2l);
        String product = subscriptionSummary.getProduct();
    }
}
