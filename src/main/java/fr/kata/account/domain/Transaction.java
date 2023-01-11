package fr.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private LocalDateTime date;
    private BigDecimal amount;
    private TransactionType type;
    private BigDecimal newBalance;

}
