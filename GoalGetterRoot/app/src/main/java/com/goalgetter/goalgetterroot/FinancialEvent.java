package com.goalgetter.goalgetterroot;


class FinancialEvent {
    private String financialGoal;
    private String financialCurrentGoal;
    private String financialNotificationFreq;

    FinancialEvent(String financialGoal,String financialCurrentGoal, String financialNotificationFreq){
        this.financialGoal = financialGoal;
        this.financialCurrentGoal = financialCurrentGoal;
        this.financialNotificationFreq = financialNotificationFreq;
    }

    String getFinancialGoal(){
        return financialGoal;
    }

    String getFinancialCurrentGoal() {
        return financialCurrentGoal;
    }

    //String getFinancialNotifFreq(){ return financialNotificationFreq; }

    int getFinancialNotifPos() {
        if (financialNotificationFreq.equals("Daily"))
            return 1;
        else if (financialNotificationFreq.equals("Weekly"))
            return 2;
        else if (financialNotificationFreq.equals("Monthly"))
            return 3;
        else return 0;
    }
}
