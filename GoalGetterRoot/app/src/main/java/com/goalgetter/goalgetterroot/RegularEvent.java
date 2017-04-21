package com.goalgetter.goalgetterroot;

/**
 * Created by moeja on 4/8/2017.
 */

public class RegularEvent {
    String regularGoal;
    String regularNotificationFreq;

    public RegularEvent(String regularGoal, String regularNotificationFreq){
        this.regularGoal = regularGoal;
        this.regularNotificationFreq = regularNotificationFreq;
    }

    public String getRegularGoal(){
        return regularGoal;
    }

    public String getRegularNotifFreq(){
        return regularNotificationFreq;
    }

    public int getRegularNotifPos() {
        if (regularNotificationFreq == "Weekly")
            return 1;
        else if (regularNotificationFreq == "Monthly")
            return 2;
        else return 0;
    }


}
