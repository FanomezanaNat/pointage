package com.hei.category;


import com.hei.calendrier.Calendar;

import java.time.DayOfWeek;
import java.util.List;

public final class Normal extends Category {


    public Normal(String name, int workingHourPerWeek, int salaryPerWeek, int compensation, List<DayOfWeek> workingDays, Calendar calendar) {
        super(name, workingHourPerWeek, salaryPerWeek, compensation, workingDays, calendar);
    }
}
