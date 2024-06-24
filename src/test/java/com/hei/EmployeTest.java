package com.hei;

import com.hei.calendrier.Calendar;
import com.hei.category.Category;
import com.hei.category.Guard;
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
        List<LocalDate> dayOff = List.of(LocalDate.of(2024, 6, 17), LocalDate.of(2024, 6, 25), LocalDate.of(2024, 6, 26));

        List<DayOfWeek> workingDays = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY

        );

        Calendar calendrier = new Calendar(month, year, dayOff);
        Category category = new Guard("x", 56, 110_000,0, workingDays,calendrier);
        Instant dateNaissance = Instant.parse("1980-01-01T00:00:00Z");
        Instant dateDembauche = Instant.parse("2015-05-20T00:00:00Z");
        Instant finContrat = Instant.parse("2023-12-31T00:00:00Z");
        Employe Rakoto = new Employe("Rakoto", "x", dateNaissance, dateDembauche, finContrat, category);
        Employe Rabe = new Employe("Rabe", "x", dateNaissance, dateDembauche, finContrat, category);



        assertEquals(216,Rakoto.getCategory().calculateMonthlyHours());
        assertEquals(216,Rabe.getCategory().calculateMonthlyHours());


        var juin25 = LocalDate.of(2024, 6, 25);
        var juin26 = LocalDate.of(2024, 6, 26);
        var jour = new WorkingHour(8, Arrays.asList(Jour, JourFerie));
        var nuit = new WorkingHour(8, Arrays.asList(Nuit, JourFerie));


        Rakoto.addWorkingOff(juin25, Arrays.asList(jour, nuit));
        Rakoto.addWorkingOff(juin26, Arrays.asList(jour, nuit));

        var salaryBasic = 2_970_000;
        var netSalaryBasic=2_376_000;
        var grossSalaryWithMajo=3_916_000;
        var netSalaryWithMajo=3_132_800;


        assertEquals(salaryBasic,Rakoto.getCategory().getGrossSalary());
        assertEquals(netSalaryBasic,Rakoto.getCategory().getNetSalary());
        assertEquals(grossSalaryWithMajo,Rakoto.grossSalaryDue());
        assertEquals(netSalaryWithMajo,Rakoto.netSalaryDue());




    }


}