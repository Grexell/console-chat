package by.dima.util;

import by.dima.model.entity.User;

public class UserHolder {
    private static UserHolder ourInstance = new UserHolder();

    public static UserHolder getInstance() {
        return ourInstance;
    }

    private User currentUser;

    private UserHolder() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
