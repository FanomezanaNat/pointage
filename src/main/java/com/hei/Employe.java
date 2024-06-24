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
    private HashMap<LocalDate, List<WorkingHour>> workingDayOff;


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

    public int grossSalaryDue() {
        int grossSalaryPerMonth = this.getCategory().getGrossSalary();
        int netSalaryPerMonth = this.getCategory().getNetSalary();
        int hourlyRate = this.getCategory().getHourlyRate();

        int salaryovertimeHours = 0;
        int salaryNightHours = 0;
        int salaryHolidaysHours = 0;
        int salarySundaysHours = 0;
        int totalAmount = 0;

        for (Map.Entry<LocalDate, List<WorkingHour>> entry : workingDayOff.entrySet()) {
            LocalDate date = entry.getKey();
            List<WorkingHour> workingHours = entry.getValue();

            for (WorkingHour hour : workingHours) {
                for (Type type : hour.getType()) {
                    switch (type) {
                        case Nuit -> salaryNightHours += (int) (hourlyRate * hour.getHour() * 1.3);
                        case JourFerie -> salaryHolidaysHours += (int) (hour.getHour() * hourlyRate * 1.5);
                        case Dimanche -> salarySundaysHours += (int) (hour.getHour() * hourlyRate * 1.4);

                    }
                }

            }
        }
        totalAmount += grossSalaryPerMonth + salaryNightHours + salaryHolidaysHours;
        return totalAmount;
    }

    public int netSalaryDue() {
        return (int) (grossSalaryDue() - grossSalaryDue() * 0.2);
    }


}



