package fr.kata.account.app;

import fr.kata.account.domain.BankAccount;
import fr.kata.account.print.PrintStatement;
import fr.kata.account.print.PrintStatementImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PrintStatement printStatement = new PrintStatementImpl();
        BankAccount bankAccount = new BankAccount(printStatement, BigDecimal.ZERO, "Bart Simpson", 123, new ArrayList<>());
        bankAccount.deposit(new BigDecimal(10), LocalDateTime.now().minusHours(1));
        bankAccount.deposit(new BigDecimal(40), LocalDateTime.now().minusHours(2));
        bankAccount.withdraw(new BigDecimal(10), LocalDateTime.now().minusHours(3));
        bankAccount.getStatement(LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(10));
    }
}
