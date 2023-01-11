package fr.kata.model;

import fr.kata.model.print.PrintStatement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

public class BankAccountTest {

    private BankAccount bankAccount;

    private PrintStatement printStatement;

    @Before
    public void setUp() {
        bankAccount = new BankAccount(BigDecimal.ZERO, "Bart Simpson", 123, new ArrayList<>());
        printStatement = mock(PrintStatement.class);
        bankAccount.setPrintStatement(printStatement);
    }

    @Test
    public void testDepositAmountPositive() {
        bankAccount.deposit(new BigDecimal(100), LocalDateTime.of(2022, 12, 30, 12, 15));
        assertEquals(new BigDecimal(100), bankAccount.getBalance());
    }

    @Test
    public void testDepositAmountNegative() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.deposit(BigDecimal.valueOf(-50.0), LocalDateTime.of(2022, 12, 30, 12, 15)));
        assertEquals("Amount -50.0 to deposit must be positive", illegalArgumentException.getMessage());
    }

    @Test
    public void testWithdrawSomeSavings() {
        bankAccount.deposit(BigDecimal.valueOf(300.0), LocalDateTime.of(2023, 1, 1, 10, 0));
        bankAccount.withdraw(BigDecimal.valueOf(50.0), LocalDateTime.of(2023, 1, 10, 14, 0));
        assertEquals(BigDecimal.valueOf(250.0), bankAccount.getBalance());
    }

    @Test
    public void testWithdrawAll() {
        bankAccount.deposit(BigDecimal.valueOf(300.0), LocalDateTime.of(2023, 1, 1, 10, 0));
        bankAccount.withdraw(BigDecimal.valueOf(300.0), LocalDateTime.of(2023, 1, 10, 14, 0));
        assertEquals(BigDecimal.valueOf(0.0), bankAccount.getBalance());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        bankAccount.deposit(BigDecimal.valueOf(200.0), LocalDateTime.of(2023, 1, 10, 14, 0));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(BigDecimal.valueOf(300.0), LocalDateTime.of(2023, 1, 11, 14, 0)));
        assertEquals("Insufficient balance 200.0", illegalArgumentException.getMessage());
    }

    @Test
    public void testGetStatementHistory() {
        bankAccount.deposit(BigDecimal.valueOf(250.0), LocalDateTime.of(2023, 1, 1, 14, 0));
        bankAccount.withdraw(BigDecimal.valueOf(50.0), LocalDateTime.of(2023, 1, 2, 14, 0));

        InOrder inOrder = Mockito.inOrder(printStatement);

        bankAccount.getStatement(LocalDateTime.of(2023, 1, 1, 10, 0), LocalDateTime.of(2023, 2, 1, 14, 0));

        inOrder.verify(printStatement).print("DEPOSIT | 2023-01-01T14:00  | 250.0 | 250.0");
        inOrder.verify(printStatement).print("WITHDRAW | 2023-01-02T14:00  | 50.0 | 200.0");
    }

}
