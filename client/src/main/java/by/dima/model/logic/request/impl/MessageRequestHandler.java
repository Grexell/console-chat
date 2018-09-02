package by.dima.model.logic.request.impl;

import by.dima.model.entity.Message;
import by.dima.util.MessageFormatter;
import by.dima.util.UserHolder;
import by.dima.util.UserOutput;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MessageRequestHandler extends AbstractRestTemplateHandler {
    private UserOutput userOutput;
    private MessageFormatter formatter;

    public MessageRequestHandler(String url, String userPattern, UserOutput userOutput, MessageFormatter formatter) {
        super(url, userPattern);
        this.userOutput = userOutput;
        this.formatter = formatter;
    }

    public MessageRequestHandler(RestTemplate template, String url, String userPattern, UserOutput userOutput, MessageFormatter formatter) {
        super(template, url, userPattern);
        this.userOutput = userOutput;
        this.formatter = formatter;
    }

    @Override
    public void handle(String data) {
        List<Message> messages;
        synchronized (UserHolder.getInstance()) {
            if (UserHolder.getInstance().getCurrentUser() != null &&
                    UserHolder.getInstance().getCurrentUser().getId() != null &&
                    UserHolder.getInstance().getCurrentUser().getId().length() > 0) {
                messages = getTemplate().exchange(getUrl(),
                        HttpMethod.GET,
                        new HttpEntity<>(getHeaders()),
                        new ParameterizedTypeReference<List<Message>>() {
                        }).getBody();

                for (Message message : messages) {
                    userOutput.print(formatter.format(message));
                }
            }
        }
    }
}
