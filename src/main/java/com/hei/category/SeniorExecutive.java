package com.hei.category;


import com.hei.calendrier.Calendar;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public final class SeniorExecutive extends Category {
    private List<String> prime;

    public SeniorExecutive(String name, int workingHourPerWeek, int salaryPerWeek, int compensation, List<DayOfWeek> workingDays, Calendar calendar) {
        super(name, workingHourPerWeek, salaryPerWeek, compensation, workingDays, calendar);
        this.prime = new ArrayList<>();
    }
}
