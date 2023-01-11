package fr.kata.account.domain;

import fr.kata.account.converter.Converter;
import fr.kata.account.print.PrintStatement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BankAccount {

    private static final String DELIMITER = " | ";
    private final PrintStatement printStatement;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm";

    private BigDecimal balance;
    private final String accountHolder;
    private final int accountNumber;
    private List<Transaction> transactions;


    public void deposit(final BigDecimal amount, final LocalDateTime depositDateTime) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format("Amount %s to deposit must be positive", amount));
        } else {
            balance = balance.add(amount);
            Transaction transaction = new Transaction(depositDateTime, amount, TransactionType.DEPOSIT, balance);
            transactions.add(transaction);
        }
    }

    public void withdraw(final BigDecimal amount, final LocalDateTime withdrawDateTime) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException(String.format("Insufficient balance %s", balance));
        }
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(withdrawDateTime, amount, TransactionType.WITHDRAW, balance);
        transactions.add(transaction);

    }

    public void getStatement(final LocalDateTime startDate, final LocalDateTime endDate) {

        System.out.println("Account statement for " + accountHolder + " (account number " + accountNumber + ") from " + startDate + " to " + endDate + ":");
        for (Transaction t : transactions) {
            if (t.getDate().compareTo(startDate) >= 0 && t.getDate().compareTo(endDate) <= 0) {
                printStatement.print(Converter.convertTransactionToLine(t));
            }
        }
        System.out.println("End of statement");
    }


}
