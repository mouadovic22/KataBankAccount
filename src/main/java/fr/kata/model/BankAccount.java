package fr.kata.model;

import fr.kata.model.print.PrintStatement;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BankAccount {

    private PrintStatement printStatement;

    private BigDecimal balance;
    private String accountHolder;
    private int accountNumber;
    private List<Transaction> transactions;

    public BankAccount(BigDecimal balance, String accountHolder, int accountNumber, List<Transaction> transactions) {
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
    }

    public void deposit(BigDecimal amount, LocalDateTime depositDateTime) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format("Amount %s to deposit must be positive", amount));
        } else {
            balance = balance.add(amount);
            Transaction transaction = new Transaction(depositDateTime, amount, TransactionType.DEPOSIT, balance);
            transactions.add(transaction);
        }
    }

    public void withdraw(BigDecimal amount, LocalDateTime withdrawDateTime) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException(String.format("Insufficient balance %s", balance));
        }
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(withdrawDateTime, amount, TransactionType.WITHDRAW, balance);
        transactions.add(transaction);

    }

    public void getStatement(LocalDateTime startDate, LocalDateTime endDate) {

        System.out.println("Account statement for " + accountHolder + " (account number " + accountNumber + ") from " + startDate + " to " + endDate + ":");
        for (Transaction t : transactions) {
            if (t.getDate().compareTo(startDate) >= 0 && t.getDate().compareTo(endDate) <= 0) {
                printStatement.print(convertTransactionToLine(t));
            }
        }
        System.out.println("End of statement");
    }
    public String convertTransactionToLine(Transaction transaction) {
        return transaction.getType() + " | " + transaction.getDate() + "  | " + transaction.getAmount() + " | " + transaction.getNewBalance();
    }

}
