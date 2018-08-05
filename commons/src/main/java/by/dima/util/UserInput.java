package by.dima.util;

public interface UserInput {
    static UserInput console(){
        return new ConsoleUserInput();
    }

    String getLine();
}
