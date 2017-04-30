package com.goalgetter.goalgetterroot;

class RegularEvent { //Regular Goal Objects
    private String regularGoal;
    private String regularNotificationFreq;

    RegularEvent(String regularGoal, String regularNotificationFreq){
        this.regularGoal = regularGoal;
        this.regularNotificationFreq = regularNotificationFreq;
    }

    String getRegularGoal(){
        return regularGoal;
    }

    //public String getRegularNotifFreq(){ return regularNotificationFreq;}

    int getRegularNotifPos() {
        if (regularNotificationFreq.equals("Daily"))
            return 1;
        else if (regularNotificationFreq.equals("Weekly"))
            return 2;
        else if (regularNotificationFreq.equals("Monthly"))
            return 3;
        else return 0;
    }
}
