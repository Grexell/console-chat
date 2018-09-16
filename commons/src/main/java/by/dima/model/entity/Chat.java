package by.dima.model.entity;

import java.util.Date;
import java.util.List;

public class Chat {
    private Date engagementStart;
    private User customer;
    private User agent;

    public Chat(User customer, User agent, List<Message> messages) {
        this(new Date(), customer, agent, messages);
    }

    public Chat(Date engagementStart, User customer, User agent, List<Message> messages) {
        this.engagementStart = engagementStart;
        this.customer = customer;
        this.agent = agent;
        this.messages = messages;
    }

    private List<Message> messages;

    public Date getEngagementStart() {
        return engagementStart;
    }

    public void setEngagementStart(Date engagementStart) {
        this.engagementStart = engagementStart;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getId() {
        return "" + engagementStart.hashCode() + customer.getId().hashCode() + agent.getId().hashCode();
    }
}
