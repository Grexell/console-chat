package by.dima.model.logic.service;

public interface SecurityService {
    String protect(String key);

    void unprotect(String token);

    String check(String token);
}
