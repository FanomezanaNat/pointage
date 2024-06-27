package com.hei.calendrier;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Calendar {
    private  int year;
    private List<LocalDate> workingDays;
    private  List<LocalDate> publicHoliday;

    public Calendar(int year) {
        this.year = year;
        this.workingDays=new ArrayList<>();
        this.publicHoliday = new ArrayList<>();
    }

    public List<LocalDate> addPublicHoliday(List<LocalDate> date){
        this.getPublicHoliday().addAll(date);
        return this.getPublicHoliday();
    }
    public List<LocalDate> addWorkingDays(List<LocalDate> dates){
        this.getWorkingDays().addAll(dates);
        return this.getWorkingDays();
    }

}

