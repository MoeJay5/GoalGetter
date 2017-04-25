package com.goalgetter.goalgetterroot;

import android.icu.util.Calendar;
import android.widget.CalendarView;

/**
 * Created by moeja on 4/8/2017.
 */

public class EventEvent {
    String eventName;
    String eventGoal;
    int userDays;
    String eventNotificationFreq;


    public EventEvent(String eventName, String eventGoal, int userDays, String eventNotificationFreq){
        this.eventName = eventName;
        this.eventGoal = eventGoal;
        this.userDays = userDays;
        this.eventNotificationFreq = eventNotificationFreq;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEventGoal() {return eventGoal;}

    public int getEventDays() {return userDays;}

    public String getEventDaysString() {return String.valueOf(userDays);}

    public String getEventNotifFreq(){
        return eventNotificationFreq;
    }

    public int getEventNotifPos() {
        if (eventNotificationFreq == "Daily")
            return 1;
        else return 0;
    }


}
