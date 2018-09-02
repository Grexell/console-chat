package by.dima.model.logic.preprocessor.impl;

import by.dima.model.logic.preprocessor.DataPreprocessor;

public class MarkerDataPreprocessor implements DataPreprocessor {

    public static final String DEFAULT_MARKER = "/";
    public static final String DEFAULT_END_MARKER = " ";

    private String marker;
    private String endMarker;
    private boolean fullDefault;

    public MarkerDataPreprocessor() {
        this(DEFAULT_MARKER, DEFAULT_END_MARKER);
    }

    public MarkerDataPreprocessor(String marker) {
        this(marker, DEFAULT_END_MARKER);
    }

    public MarkerDataPreprocessor(String marker, String endMarker) {
        this.marker = marker;
        this.endMarker = endMarker;
    }

    public MarkerDataPreprocessor(String marker, String endMarker, boolean fullDefault) {
        this.marker = marker;
        this.endMarker = endMarker;
        this.fullDefault = fullDefault;
    }

    @Override
    public String parse(String data) {
        String result;
        if (data.contains(marker)) {
            if (endMarker != null && data.contains(endMarker)) {
                result = data.substring(data.indexOf(marker) + 1, data.indexOf(endMarker));
            } else {
                result = data.substring(data.indexOf(marker) + 1);
            }

        } else {
            result = fullDefault ? data : new String();
        }
        return result;
    }
}
