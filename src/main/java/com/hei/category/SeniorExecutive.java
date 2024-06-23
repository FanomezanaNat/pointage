package com.hei.category;


import java.util.ArrayList;
import java.util.List;

public final class SeniorExecutive extends Category {
    private final List<String> prime;


    public SeniorExecutive(String name, int workingHourPerDay, Double salaryPerWeek, Double compensation) {
        super(name, workingHourPerDay, salaryPerWeek, compensation);
        this.prime=new ArrayList<>();
    }
}
