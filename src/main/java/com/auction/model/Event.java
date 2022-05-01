package com.auction.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String schedule;
    private String sqlprocedure;

    private String eventname;


    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        LocalDateTime localDateTime = LocalDateTime.parse(schedule);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String formattedSchedule = localDateTime.format(format);
        this.schedule = formattedSchedule;
    }

    public String getSqlprocedure() {
        return sqlprocedure;
    }

    public void setSqlprocedure(String sqlprocedure) {
        this.sqlprocedure = sqlprocedure;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
}
