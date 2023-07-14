package com.JuliaSystem.JuliaSystem.calendar;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Month {
    private EventPerDay[] eventsPerDay;
    private CalendarType calendarType;
    private Integer year;
}
