package fr.kata.model.app;

import fr.kata.model.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(BigDecimal.ZERO, "Bart Simpson", 123, new ArrayList<>());
        bankAccount.deposit(new BigDecimal(10), LocalDateTime.now());
        bankAccount.deposit(new BigDecimal(40), LocalDateTime.now());
        bankAccount.withdraw(new BigDecimal(10), LocalDateTime.now());
        bankAccount.getStatement(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
    }
}
