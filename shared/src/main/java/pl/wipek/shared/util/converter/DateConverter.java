package pl.wipek.shared.util.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
public class DateConverter {

    public static Date fromLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
