package fr.kata.model.main;

import fr.kata.model.BankAccount;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0d, "mouad", 123, LocalDate.now(), new ArrayList<>());
        bankAccount.deposit(10);
        bankAccount.deposit(40);
        bankAccount.withdraw(10);
        bankAccount.getStatement(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
    }
}
