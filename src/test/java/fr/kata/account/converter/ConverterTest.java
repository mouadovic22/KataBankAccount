package fr.kata.account.converter;

import fr.kata.account.domain.Transaction;
import fr.kata.account.domain.TransactionType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConverterTest {

    @Test
    public void testConvertTransactionToLine() {
        Transaction transaction = new Transaction(LocalDateTime.of(2023, 1, 1, 14, 0), BigDecimal.valueOf(10), TransactionType.DEPOSIT, BigDecimal.valueOf(10));
        assertNotNull(Converter.convertTransactionToLine(transaction));
        assertEquals("DEPOSIT | 2023-01-01 14:00 | 10 | 10", Converter.convertTransactionToLine(transaction));
    }

    @Test
    public void testFormatLocalDateTime() {
        assertEquals("2023-01-01 14:00", Converter.formatLocalDateTime(LocalDateTime.of(2023, 1, 1, 14, 0)));
    }
}
