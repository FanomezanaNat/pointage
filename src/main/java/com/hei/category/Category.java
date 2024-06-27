package com.hei.category;

import com.hei.calendrier.Calendar;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public abstract sealed class Category permits Driver, Guard, Normal, SeniorExecutive {
    private String name;
    private int workingHourPerWeek;
    private int salaryPerWeek;
    private int compensation;
    private List<DayOfWeek> workingDays;
    private double hourlyRate;
    private int workingHourPerDay;
    private Calendar calendar;
    private double grossSalary;
    private double netSalary;


    public Category(String name, int workingHourPerWeek, int salaryPerWeek, int compensation, List<DayOfWeek> workingDays, Calendar calendar) {
        this.name = name;
        this.workingHourPerWeek = workingHourPerWeek;
        this.salaryPerWeek = salaryPerWeek;
        this.compensation = compensation;
        this.workingDays = workingDays;
        this.hourlyRate = ((double) salaryPerWeek / workingDays.size());
        this.workingHourPerDay = workingHourPerWeek / this.workingDays.size();
        this.calendar = calendar;
    }

    public List<LocalDate> nonStopWorkingDay(LocalDate begin, LocalDate ending) {
        LocalDate startDate = LocalDate.of(calendar.getYear(), begin.getMonth(), begin.getDayOfMonth());
        LocalDate endDate = LocalDate.of(calendar.getYear(), ending.getMonth(), ending.getDayOfMonth());

        return startDate.datesUntil(endDate.plusDays(1))
                .filter(date -> this.getWorkingDays().contains(date.getDayOfWeek()) && !this.calendar.getPublicHoliday().contains(date))
                .toList();
    }





}
