package by.dima.model.logic.preprocessor.impl;

import by.dima.model.logic.preprocessor.DataPreprocessor;
import by.dima.model.logic.request.RequestHandlerBuilder;

public class CommandDataPreprocessor implements DataPreprocessor {

    public static final String DEFAULT_COMMAND_MARKER = "/";
    public static final String DEFAULT_COMMAND = RequestHandlerBuilder.SEND_MESSAGE_COMMAND;
    public static final String COMMAND_END = " ";

    private String commandMarker;
    private String defaultCommand;

    private MarkerDataPreprocessor markerPreprocessor;

    public CommandDataPreprocessor() {
        this(DEFAULT_COMMAND_MARKER, DEFAULT_COMMAND);
    }

    public CommandDataPreprocessor(String commandMarker, String defaultCommand) {
        this.commandMarker = commandMarker;
        this.defaultCommand = defaultCommand;
        markerPreprocessor = new MarkerDataPreprocessor(commandMarker, COMMAND_END, true);
    }

    @Override
    public String parse(String data) {
        if (data.startsWith(commandMarker)) {
            return markerPreprocessor.parse(data);
        }
        return defaultCommand;
    }
}
