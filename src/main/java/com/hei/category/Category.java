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
    private int hourlyRate;
    private int workingHourPerDay;
    private Calendar calendar;
    private int grossSalary;
    private int netSalary;


    public Category(String name, int workingHourPerWeek, int salaryPerWeek, int compensation, List<DayOfWeek> workingDays, Calendar calendar) {
        this.name = name;
        this.workingHourPerWeek = workingHourPerWeek;
        this.salaryPerWeek = salaryPerWeek;
        this.compensation = compensation;
        this.workingDays = workingDays;
        this.hourlyRate = (salaryPerWeek * this.workingDays.size() / workingHourPerWeek);
        this.workingHourPerDay = workingHourPerWeek / this.workingDays.size();
        this.calendar = calendar;
        this.grossSalary = normalSalaryPerMonth();
        this.netSalary = (int) (this.grossSalary - (this.grossSalary * 0.2));
    }

    public List<LocalDate> nonStopWorkingDay() {
        LocalDate startDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return startDate.datesUntil(endDate.plusDays(1))
                .filter(date -> this.getWorkingDays().contains(date.getDayOfWeek()) && !this.calendar.getPublicHoliday().contains(date))
                .toList();
    }
    public int calculateMonthlyHours() {
        return (this.nonStopWorkingDay().size()) * this.getWorkingHourPerDay();
    }
    private int normalSalaryPerMonth(){
        return (int) (this.getHourlyRate() * calculateMonthlyHours());

    }


}
