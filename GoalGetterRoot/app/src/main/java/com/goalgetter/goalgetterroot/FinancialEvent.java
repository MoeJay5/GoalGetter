package com.goalgetter.goalgetterroot;

/**
 * Created by moeja on 4/8/2017.
 */

public class FinancialEvent {
    String financialGoal;
    String financialCurrentGoal;
    String financialNotificationFreq;

    public FinancialEvent(String financialGoal,String financialCurrentGoal, String financialNotificationFreq){
        this.financialGoal = financialGoal;
        this.financialCurrentGoal = financialCurrentGoal;
        this.financialNotificationFreq = financialNotificationFreq;
    }

    public String getFinancialGoal(){
        return financialGoal;
    }

    public String getFinancialCurrentGoal() {
        return financialCurrentGoal;
    }

    public String getFinancialNotifFreq(){
        return financialNotificationFreq;
    }

    public int getFinancialNotifPos() {
        if (financialNotificationFreq == "Weekly")
            return 1;
        else if (financialNotificationFreq == "Monthly")
            return 2;
        else return 0;
    }


}
