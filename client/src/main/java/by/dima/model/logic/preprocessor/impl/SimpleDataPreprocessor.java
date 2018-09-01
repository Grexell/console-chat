package by.dima.model.logic.preprocessor.impl;

import by.dima.model.logic.preprocessor.DataPreprocessor;

public class SimpleDataPreprocessor implements DataPreprocessor {
    @Override
    public String parse(String data) {
        return data;
    }
}
