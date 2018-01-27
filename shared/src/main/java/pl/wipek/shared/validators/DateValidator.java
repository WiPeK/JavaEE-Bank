package pl.wipek.shared.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator {
    private static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean checkIsNotDateInPast(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String[] dateParts = date.split("-");
        LocalDate inputDate = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
        LocalDate today = LocalDate.now();

        return inputDate.isAfter(today) || inputDate.isEqual(today);
    }
}
