package by.dima.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    private String command;
    @JsonProperty(value="data")
    private String data;

    public Request() {
    }

    public Request(String command, String data) {
        this.command = command;
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
