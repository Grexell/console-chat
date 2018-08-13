package by.dima.model.logic;

import by.dima.util.JasonObjectConverter;

public class DataPreprocessorBuilder {
    public static final String EXIT = "exit";
    public static final String LEAVE = "leave";
    public static final String REGISTER = "register";

    public static DataPreprocessor build(String command){
        DataPreprocessor result;
        switch (command){
            case REGISTER:
                result = new RegisterDataPreprocessor(new JasonObjectConverter());
                break;
            case LEAVE:
            case EXIT:
                result = new UnregisterDataPreprocessor();
                break;
            default:
                result = new SimpleDataPreprocessor();
        }
        return result;
    }
}
