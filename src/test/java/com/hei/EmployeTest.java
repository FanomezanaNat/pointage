package com.hei;

import com.hei.calendrier.Calendar;
import com.hei.category.Category;
import com.hei.category.Guard;
import com.hei.category.Type;
import com.hei.category.WorkingHour;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.hei.category.Type.*;
import static org.junit.Assert.assertEquals;

public class EmployeTest {
    @Test
    public void test() {
        int year = 2024;
        int month = 6;
        List<LocalDate> dayOff = List.of(
                LocalDate.of(2024, 6, 17),
                LocalDate.of(2024, 6, 25),
                LocalDate.of(2024, 6, 26)
        );

        List<DayOfWeek> workingDays = List.of(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY

        );

        Calendar calendrier = new Calendar(month, year, dayOff);
        Double salaire = 100000.00;
        Category normalCategory = new Guard("x", 8, 100.00, 0.0);
        Category nightCategory = new Guard("x", 8, 100.00, 0.0);
        Instant dateNaissance = Instant.parse("1980-01-01T00:00:00Z");
        Instant dateDembauche = Instant.parse("2015-05-20T00:00:00Z");
        Instant finContrat = Instant.parse("2023-12-31T00:00:00Z");
        Employe Rakoto = new Employe("Rakoto", "x", dateNaissance, dateDembauche, finContrat, salaire, normalCategory, List.of(), calendrier, workingDays);
        Employe Rabe = new Employe("Rabe", "x", dateNaissance, dateDembauche, finContrat, salaire, nightCategory, List.of(), calendrier, workingDays);


        assertEquals(216, Rakoto.calculateMonthlyHours());
        assertEquals(216, Rabe.calculateMonthlyHours());

        var juin25 = LocalDate.of(2024, 6, 25);
        var juin26 = LocalDate.of(2024, 6, 26);
        var jour = new WorkingHour(8, Arrays.asList(Jour, JourFerie));
        var nuit = new WorkingHour(8, Arrays.asList(Nuit, JourFerie));


        Rakoto.addWorkingOff(juin25, jour);
        Rakoto.addWorkingOff(juin25, nuit);
        Rakoto.addWorkingOff(juin26, jour);
        Rakoto.addWorkingOff(juin26, nuit);


        System.out.println(Rakoto.getNetSalary());
        System.out.println(Rakoto.getWorkingDayOff());


    }


}