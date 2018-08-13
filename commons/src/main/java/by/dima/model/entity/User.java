package by.dima.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {
    @JsonProperty(value="id")
    private String id;
    private String username;
    private Role role;

    public User() {
    }

    public User(String username, Role role) {
        this("", username, role);
    }

    public User(String id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
