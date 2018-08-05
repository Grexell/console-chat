package by.dima.util;

public class ConsoleUserOutput implements UserOutput {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
