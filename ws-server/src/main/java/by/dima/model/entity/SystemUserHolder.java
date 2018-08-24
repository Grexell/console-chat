package by.dima.model.entity;

public class SystemUserHolder {
    public static final String SYSTEM_USERNAME = "System";
    public static final User SYSTEM_USER = new User(SYSTEM_USERNAME, Role.AGENT);
}
