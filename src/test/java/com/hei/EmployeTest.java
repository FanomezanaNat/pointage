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
    public void normalSalaryRakoto() {
        var year = 2024;

        List<DayOfWeek> workingDaysInWeek = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        Calendar calendar = new Calendar(year);

        Category category = new Guard("Security Guard", 70, 100_000, 0, workingDaysInWeek, calendar);
        Instant birthdayDate = Instant.parse("1980-01-01T00:00:00Z");
        Instant hiringDate = Instant.parse("2020-05-20T00:00:00Z");
        Instant endOfContract = Instant.parse("2028-12-31T00:00:00Z");

        Employe Rakoto = new Employe("Rakoto", "Fra", birthdayDate, hiringDate, endOfContract, category);

        var mai26 = LocalDate.of(2024, 5, 26);
        var juillet06 = LocalDate.of(2024, 7, 6);
        var workingDates = category.nonStopWorkingDay(mai26, juillet06);

        calendar.addWorkingDays(workingDates);

        assertEquals(42, calendar.getWorkingDays().size());
        assertEquals(10, Rakoto.getCategory().getWorkingHourPerDay());

        var expectedSalary = 600_000;

        assertEquals(expectedSalary, Math.round(Rakoto.normalGrossSalary(mai26, juillet06)));


    }

    @Test
    public void normalSalaryRabe() {
        var year = 2024;
        List<DayOfWeek> workingDaysInWeek = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        Calendar calendar = new Calendar(year);

        Category category = new Guard("Security Guard", 98, 130_000, 0, workingDaysInWeek, calendar);
        Instant birthdayDate = Instant.parse("1980-01-01T00:00:00Z");
        Instant hiringDate = Instant.parse("2020-05-20T00:00:00Z");
        Instant endOfContract = Instant.parse("2028-12-31T00:00:00Z");

        Employe Rabe = new Employe("Rabe", "Jean", birthdayDate, hiringDate, endOfContract, category);

        var mai26 = LocalDate.of(2024, 5, 26);
        var juillet06 = LocalDate.of(2024, 7, 6);
        var nuit = new WorkingHour(14, List.of(Nuit));
        var workingDates = category.nonStopWorkingDay(mai26, juillet06);
        for (LocalDate date : workingDates) {
            Rabe.addWorkingOff(date, List.of(nuit));
        }
        var expectedSalary = 780_000;

        assertEquals(expectedSalary, Math.round(Rabe.normalGrossSalary(mai26, juillet06)));

    }

    @Test
    public void salaryRakotoWithMajorationNightOnly() {
        var year = 2024;

        List<DayOfWeek> workingDaysInWeek = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        List<LocalDate> dayOff = List.of(LocalDate.of(2024, 6, 17)
                , LocalDate.of(2024, 6, 25),
                LocalDate.of(2024, 6, 26));

        Calendar calendar = new Calendar(year);

        calendar.addPublicHoliday(dayOff);

        Category category = new Guard("Security Guard", 70, 100_000, 0, workingDaysInWeek, calendar);
        Instant birthdayDate = Instant.parse("1980-01-01T00:00:00Z");
        Instant hiringDate = Instant.parse("2020-05-20T00:00:00Z");
        Instant endOfContract = Instant.parse("2028-12-31T00:00:00Z");

        Employe Rakoto = new Employe("Rakoto", "Fra", birthdayDate, hiringDate, endOfContract, category);

        var mai26 = LocalDate.of(2024, 5, 26);
        var juillet06 = LocalDate.of(2024, 7, 6);

        var workingDates = category.nonStopWorkingDay(mai26, juillet06);

        calendar.addWorkingDays(workingDates);

        var juin17 = LocalDate.of(2024, 6, 17);
        var juin25 = LocalDate.of(2024, 6, 25);
        var juin26 = LocalDate.of(2024, 6, 26);
        var nuit = new WorkingHour(14, List.of(Nuit));

        Rakoto.addWorkingOff(juin17, List.of(nuit));
        Rakoto.addWorkingOff(juin25, List.of(nuit));
        Rakoto.addWorkingOff(juin26, List.of(nuit));

        var salaryExpected = 612_857;

        assertEquals(salaryExpected, Math.round(Rakoto.grossSalaryDue(mai26, juillet06)));

    }

    @Test
    public void salaryRakotoWithMajoration() {
        var year = 2024;

        List<DayOfWeek> workingDaysInWeek = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        List<LocalDate> dayOff = List.of(LocalDate.of(2024, 6, 17)
                , LocalDate.of(2024, 6, 25),
                LocalDate.of(2024, 6, 26));

        Calendar calendar = new Calendar(year);

        calendar.addPublicHoliday(dayOff);

        Category category = new Guard("Security Guard", 70, 100_000, 0, workingDaysInWeek, calendar);
        Instant birthdayDate = Instant.parse("1980-01-01T00:00:00Z");
        Instant hiringDate = Instant.parse("2020-05-20T00:00:00Z");
        Instant endOfContract = Instant.parse("2028-12-31T00:00:00Z");

        Employe Rakoto = new Employe("Rakoto", "Fra", birthdayDate, hiringDate, endOfContract, category);

        var mai26 = LocalDate.of(2024, 5, 26);
        var juillet06 = LocalDate.of(2024, 7, 6);

        var workingDates = category.nonStopWorkingDay(mai26, juillet06);

        calendar.addWorkingDays(workingDates);

        var juin17 = LocalDate.of(2024, 6, 17);
        var juin25 = LocalDate.of(2024, 6, 25);
        var juin26 = LocalDate.of(2024, 6, 26);

        var jour = new WorkingHour(10, Arrays.asList(Jour, JourFerie));
        var nuit = new WorkingHour(14, Arrays.asList(Nuit, JourFerie));

        Rakoto.addWorkingOff(juin17, Arrays.asList(jour, nuit));
        Rakoto.addWorkingOff(juin25, Arrays.asList(jour, nuit));
        Rakoto.addWorkingOff(juin26, Arrays.asList(jour, nuit));

        var salaryExpected = 807_857;

        assertEquals(salaryExpected,Math.round(Rakoto.grossSalaryDue(mai26,juillet06)));
    }


}


