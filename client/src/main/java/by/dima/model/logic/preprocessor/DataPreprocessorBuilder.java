package by.dima.model.logic.preprocessor;

import by.dima.model.logic.preprocessor.impl.RegisterDataPreprocessor;
import by.dima.model.logic.preprocessor.impl.SimpleDataPreprocessor;
import by.dima.model.logic.preprocessor.impl.MessageDataPreprocessor;
import by.dima.model.logic.request.RequestHandlerBuilder;
import by.dima.util.JasonObjectConverter;

public class DataPreprocessorBuilder {

    public static DataPreprocessor build(String command){
        DataPreprocessor result;
        switch (command){
            case RequestHandlerBuilder.REGISTER_COMMAND:
                result = new RegisterDataPreprocessor(new JasonObjectConverter());
                break;
            case RequestHandlerBuilder.SEND_MESSAGE_COMMAND:
                result = new MessageDataPreprocessor();
                break;
            default:
                result = new SimpleDataPreprocessor();
        }
        return result;
    }
}
