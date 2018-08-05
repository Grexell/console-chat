package by.dima.model.logic;

import by.dima.util.JasonObjectConverter;

public class DataPreprocessorBuilder {
    public static DataPreprocessor build(String command){
        DataPreprocessor result;
        switch (command){
            case "register":
                result = new RegisterDataPreprocessor(new JasonObjectConverter());
                break;
            default:
                result = new SimpleDataPreprocessor();
        }
        return result;
    }
}
