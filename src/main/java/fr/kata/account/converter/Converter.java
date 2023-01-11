package fr.kata.account.converter;

import fr.kata.account.domain.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm";
    private static final String DELIMITER = " | ";

    //private constructor to hide implicity
    private Converter() {
    }


    public static String convertTransactionToLine(Transaction transaction) {
        return transaction.getType() + DELIMITER + formatLocalDateTime(transaction.getDate()) + DELIMITER + transaction.getAmount() + DELIMITER + transaction.getNewBalance();
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return dateTime.format(formatter);
    }
}
