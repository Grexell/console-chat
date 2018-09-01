package by.dima.model.logic.request.impl;

import by.dima.util.UserHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

public class RegisterRequestHandler extends AbstractRestTemplateHandler {

    public RegisterRequestHandler(RestTemplate template, String url) {
        super(template, url);
    }

    @Override
    public void handle(String data) {
        String id = getTemplate().exchange(getUrl(), HttpMethod.POST, new HttpEntity<>(data, getHeaders()), String.class).getBody();
        UserHolder.getInstance().getCurrentUser().setId(id);
    }
}
