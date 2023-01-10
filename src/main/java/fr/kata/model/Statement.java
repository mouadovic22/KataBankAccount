package fr.kata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statement {
    private Transaction transaction;
    private double balance;
}
