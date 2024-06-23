package com.hei.calendrier;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class CalendarTest {
    @Test
            public void calendarTest(){
        int year = 2024;
        int month = 6;
        List<LocalDate> dayOff = List.of(
                LocalDate.of(2024, 6, 17),
                LocalDate.of(2024, 6, 25),
                LocalDate.of(2024, 6, 26)
        );

        Calendar calendar = new Calendar(month, year, dayOff);

        assertEquals(month, calendar.getMonth());
        assertEquals(year, calendar.getYear());
        assertEquals(dayOff, calendar.getPublicHoliday());

    }





}