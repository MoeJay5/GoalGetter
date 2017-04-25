package com.goalgetter.goalgetterroot;

class EventEvent {
    private String eventName;
    private String eventGoal;
    private int userDays;
    private String eventNotificationFreq;

    EventEvent(String eventName, String eventGoal, int userDays, String eventNotificationFreq){
        this.eventName = eventName;
        this.eventGoal = eventGoal;
        this.userDays = userDays;
        this.eventNotificationFreq = eventNotificationFreq;
    }

    String getEventName(){
        return eventName;
    }

    String getEventGoal() { return eventGoal; }

    int getEventDays() { return userDays; }

    String getEventDaysString() { return String.valueOf(userDays); }

    // String getEventNotifFreq() { return eventNotificationFreq;}

    int getEventNotifPos() {
        if (eventNotificationFreq.equals("Daily"))
            return 1;
        else return 0;
    }
}
