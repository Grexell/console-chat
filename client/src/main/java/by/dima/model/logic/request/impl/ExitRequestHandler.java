package by.dima.model.logic.request.impl;

import by.dima.util.UserHolder;
import org.springframework.web.client.RestTemplate;

public class ExitRequestHandler extends AbstractRestTemplateHandler {
    public static final String USER_ID_PATTERN = "";

    private String userPattern;

    public ExitRequestHandler(String url, String userPattern) {
        super(url, userPattern);
    }

    public ExitRequestHandler(RestTemplate template, String url, String userPattern) {
        super(template, url, userPattern);
    }

    public ExitRequestHandler(String url) {
        this(url, USER_ID_PATTERN);
    }

    public ExitRequestHandler(RestTemplate template, String url) {
        this(template, url, USER_ID_PATTERN);
    }

    @Override
    public void handle(String data) {
        getTemplate().postForObject(getUrl(), null, Object.class);
        UserHolder.getInstance().setCurrentUser(null);
    }
}
