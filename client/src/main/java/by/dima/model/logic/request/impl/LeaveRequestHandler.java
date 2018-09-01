package by.dima.model.logic.request.impl;

import org.springframework.web.client.RestTemplate;

public class LeaveRequestHandler extends AbstractRestTemplateHandler {
    public LeaveRequestHandler(String url, String userPattern) {
        super(url, userPattern);
    }

    public LeaveRequestHandler(RestTemplate template, String url, String userPattern) {
        super(template, url, userPattern);
    }

    @Override
    public void handle(String data) {
        getTemplate().postForObject(getUrl(), null, Object.class);

    }
}
