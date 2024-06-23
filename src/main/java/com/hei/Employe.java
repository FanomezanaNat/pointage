package com.hei;

import com.hei.calendrier.Calendar;
import com.hei.category.Category;
import com.hei.category.WorkingHour;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter

public class Employe {
    private  String lastName;
    private  String firstName;
    private  Instant dateOfBirth;
    private  Instant dateOfHire;
    private  Instant endOfContract;
    private  Double grossSalary;
    private  Double netSalary;
    private  Category category;
    private  List<LocalDate> absenceDays;
    private  Calendar calendar;
    private  List<DayOfWeek> workingDays;
    private  Map<LocalDate, Integer> overtimeHours;
    private  Map<LocalDate, WorkingHour> workingDayOff;


    public Employe(String lastName, String firstName, Instant dateOfBirth, Instant dateOfHire, Instant endOfContract, Double grossSalary, Category category, List<LocalDate> absenceDays, Calendar calendar, List<DayOfWeek> workingDays) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
        this.endOfContract = endOfContract;
        this.grossSalary = grossSalary;
        this.netSalary = this.grossSalary - (this.grossSalary * 0.2);
        this.category = category;
        this.absenceDays = absenceDays;
        this.calendar = calendar;
        this.workingDays = workingDays;
        this.overtimeHours = new HashMap<>();
        this.workingDayOff = new HashMap<>();
    }

    public List<LocalDate> nonStopWorkingDay() {
        LocalDate startDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return startDate.datesUntil(endDate.plusDays(1))
                .filter(date -> workingDays.contains(date.getDayOfWeek()) && !this.calendar.getPublicHoliday().contains(date))
                .toList();
    }

    public int calculateMonthlyHours() {
        return (nonStopWorkingDay().size()) * this.category.getWorkingHourPerDay();
    }

    public List<LocalDate> addAbsence(LocalDate date) {
        this.absenceDays.add(date);
        return this.absenceDays;
    }

    public Map<LocalDate, WorkingHour> addWorkingOff(LocalDate date, WorkingHour value){
        this.workingDayOff.put(date,value);
        return this.workingDayOff;
    }


    public Double amoutDue() {

        return 0.0;
    }


}


