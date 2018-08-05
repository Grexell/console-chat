package by.dima.util;

import java.util.Scanner;

public class ConsoleUserInput implements UserInput {
    public String getLine() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
