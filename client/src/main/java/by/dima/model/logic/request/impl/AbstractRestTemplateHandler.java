package by.dima.model.logic.request.impl;

import by.dima.model.logic.request.RequestHandler;
import by.dima.util.UserHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

public abstract class AbstractRestTemplateHandler implements RequestHandler {
    private RestTemplate template;
    private HttpHeaders headers;
    private String url;
    private String pathPlaceholder;
    private Supplier<String> replaceData;


    public AbstractRestTemplateHandler(String url, String pathPlaceholder) {
        this(new RestTemplate(), url, pathPlaceholder);
    }

    public AbstractRestTemplateHandler(RestTemplate template, String url) {
        this(template, url, new String());
    }

    public AbstractRestTemplateHandler(RestTemplate template, String url, String pathPlaceholder) {
        this(template, url, pathPlaceholder, () ->  UserHolder.getInstance().getCurrentUser().getId());
    }

    public AbstractRestTemplateHandler(RestTemplate template, String url, String pathPlaceholder, Supplier<String> supplier) {
        this.template = template;
        this.url = url;
        this.pathPlaceholder = pathPlaceholder;
        this.replaceData = supplier;
        headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
    }


    public RestTemplate getTemplate() {
        return template;
    }

    public String getUrl() {
        if (url.contains(pathPlaceholder)) {
            return url.replace(pathPlaceholder, replaceData.get());
        }
        return url;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
