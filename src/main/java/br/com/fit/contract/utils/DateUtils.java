package br.com.fit.contract.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private DateUtils() {
    }

    public static String formatBR(Instant instant) {
        return BR_FORMATTER.format(instant);
    }

    private static final DateTimeFormatter BR_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    .withZone(ZoneId.of("America/Sao_Paulo"));
}