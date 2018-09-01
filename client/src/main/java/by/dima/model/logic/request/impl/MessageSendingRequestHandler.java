package by.dima.model.logic.request.impl;

import by.dima.util.UserHolder;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class MessageSendingRequestHandler extends AbstractRestTemplateHandler {

    public MessageSendingRequestHandler(String url, String userPattern) {
        super(url, userPattern);
    }

    public MessageSendingRequestHandler(RestTemplate template, String url, String userPattern) {
        super(template, url, userPattern);
    }

    @Override
    public void handle(String data) {
        getTemplate().postForObject(getUrl(), new HttpEntity<>(data, getHeaders()), Object.class);
    }
}
