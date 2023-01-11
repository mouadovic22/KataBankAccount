package fr.kata.model.print;

public class PrintStatementImpl implements PrintStatement {

    @Override
    public void print(String line) {
        System.out.println(line);
    }
}
