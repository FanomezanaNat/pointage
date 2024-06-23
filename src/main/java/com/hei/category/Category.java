package com.hei.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract sealed class Category permits  Driver, Guard, Normal, SeniorExecutive {
    private String name;
    private int workingHourPerDay;
    private Double salaryPerWeek;
    private Double compensation;

}
