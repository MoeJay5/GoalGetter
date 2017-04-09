package com.goalgitter.goalgitterroot;

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

    public String getRegularNotificationFreq(){
        return regularNotificationFreq;
    }
    //incase no input if == empty make button invisible.

}
