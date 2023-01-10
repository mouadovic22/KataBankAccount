package fr.kata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private LocalDate date;
    private double amount;
    private TransactionType type;
    private double newBalance;

}
