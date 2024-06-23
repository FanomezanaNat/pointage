package com.hei.calendrier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Getter
public class Calendar {
    private  int month;
    private  int year;
    private  List<LocalDate> publicHoliday;

}

