package fr.kata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class BankAccount {

    private double balance;
    private String accountHolder;
    private int accountNumber;
    private LocalDate dateOpened;
    private List<Transaction> transactions;

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(String.format("Amount %s to deposit must be positive", amount));
        } else {
            balance += amount;
            Transaction transaction = new Transaction(LocalDate.now(), amount, TransactionType.DEPOSIT, balance);
            transactions.add(transaction);
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException(String.format("Insufficient balance %s", balance));
        }
        balance -= amount;
        Transaction transaction = new Transaction(LocalDate.now(), amount, TransactionType.WITHDRAW, balance);
        transactions.add(transaction);

    }

    public void getStatement(LocalDate startDate, LocalDate endDate) {
        System.out.println("Account statement for " + accountHolder + " (account number " + accountNumber + ") from " + startDate + " to " + endDate + ":");
        for (Transaction t : transactions) {
            if (t.getDate().compareTo(startDate) >= 0 && t.getDate().compareTo(endDate) <= 0) {
                System.out.println(t.getDate() + ": " + t.getType() + " €" + t.getAmount() + " | " + t.getNewBalance());
            }
        }
        System.out.println("End of statement");
    }

}
