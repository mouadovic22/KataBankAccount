package fr.kata.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BankAccountTest {

    private BankAccount bankAccount;

    @Before
    public void setUp() {
        bankAccount = new BankAccount(BigDecimal.ZERO, "Bart Simpson", 123, new ArrayList<>());
    }

    @Test
    public void testDepositAmountPositive() {
        bankAccount.deposit(new BigDecimal("100.0"), LocalDateTime.of(2022, 12, 30, 12, 15));
        assertEquals(new BigDecimal("100.0"), bankAccount.getBalance());
    }

    @Test
    public void testDepositAmountNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> bankAccount.deposit(BigDecimal.valueOf(-50.0), LocalDateTime.of(2022, 12, 30, 12, 15)));
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
        bankAccount.deposit(new BigDecimal("200.0"), LocalDateTime.of(2023, 1, 10, 14, 0));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> bankAccount.withdraw(new BigDecimal("300.0"), LocalDateTime.of(2023, 1, 11, 14, 0)));
        assertEquals("Insufficient balance 200.0", illegalArgumentException.getMessage());
    }

}
