package com.hei;

import com.hei.category.Category;
import com.hei.category.Type;
import com.hei.category.WorkingHour;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter

public class Employe {
    private String lastName;
    private String firstName;
    private Instant dateOfBirth;
    private Instant dateOfHire;
    private Instant endOfContract;
    private Category category;
    private List<LocalDate> attendanceDay;
    private Map<LocalDate, Integer> overtimeHours;
    private Map<LocalDate, List<WorkingHour>> workingDayOff;


    public Employe(String lastName, String firstName, Instant dateOfBirth, Instant dateOfHire, Instant endOfContract, Category category) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
        this.endOfContract = endOfContract;
        this.category = category;
        this.attendanceDay = new ArrayList<>();
        this.overtimeHours = new HashMap<>();
        this.workingDayOff = new HashMap<>();
    }

    public double normalGrossSalary(LocalDate begin, LocalDate ending) {
        return (this.getCategory().getHourlyRate() * this.getCategory().nonStopWorkingDay(begin, ending).size());
    }

    public double normalNetSalary(LocalDate begin, LocalDate ending) {
        return (normalGrossSalary(begin, ending) - (normalGrossSalary(begin, ending) * 0.2));
    }


    public List<LocalDate> addAttendance(LocalDate date) {
        this.attendanceDay.add(date);
        return this.attendanceDay;
    }

    public void addWorkingOff(LocalDate date, List<WorkingHour> value) {

        if (workingDayOff.containsKey(date)) {
            workingDayOff.get(date).addAll(value);
        } else {
            workingDayOff.put(date, new ArrayList<>(value));
        }
    }

    public int calculateTotalOvertimeAndPremiumHours() {
        int totalHours = 0;
        for (List<WorkingHour> workingHours : this.workingDayOff.values()) {
            for (WorkingHour workingHour : workingHours) {
                totalHours += workingHour.getHour();
            }
        }
        for (int overtimeHour : this.overtimeHours.values()) {
            totalHours += overtimeHour;
        }

        return totalHours;
    }

    public void addOvertimeHour(LocalDate date, int value) {
        this.overtimeHours.put(date, value);
    }

    public double grossSalaryDue(LocalDate begin, LocalDate ending) {
        double hourlyRate = this.getCategory().getHourlyRate();
        double workingDaysOff = this.workingDayOff.size();
        double workingDays = this.getCategory().getCalendar().getWorkingDays().size();
        double hourlyRateNight = hourlyRate * 1.3;
        double hourlyHolidayRate = hourlyRate * 1.5;
        double nightRate = 1.3;
        double holidayRate = 1.5;

        double totalAmount = 0;
        double total = 0;

        for (Map.Entry<LocalDate, List<WorkingHour>> entry : workingDayOff.entrySet()) {
            List<WorkingHour> workingHours = entry.getValue();

            for (WorkingHour hour : workingHours) {
                for (Type type : hour.getType()) {
                    switch (type) {
                        case Nuit -> totalAmount = ((hourlyRate) * (workingDays + (workingDaysOff * nightRate)));
                        case JourFerie -> {
                            if (totalAmount > 0) {
                                totalAmount = (hourlyRateNight) * (workingDays + (workingDaysOff * holidayRate));
                            } else if (totalAmount == 0) {
                                totalAmount = (hourlyRate) * (workingDays + (workingDaysOff * holidayRate));

                            }

                        }


                    }
                }

            }
        }
        return totalAmount;
    }

    public double netSalaryDue(LocalDate begin, LocalDate ending) {
        return (grossSalaryDue(begin, ending) - grossSalaryDue(begin, ending) * 0.2);
    }


}



