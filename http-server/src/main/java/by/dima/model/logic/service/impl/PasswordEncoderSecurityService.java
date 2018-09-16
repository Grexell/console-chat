package by.dima.model.logic.service.impl;

import by.dima.model.logic.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordEncoderSecurityService implements SecurityService {
    private Map<String, String> protectedKeys;
    private PasswordEncoder encoder;

    @Autowired
    public PasswordEncoderSecurityService(PasswordEncoder encoder) {
        this.encoder = encoder;
        protectedKeys = new ConcurrentHashMap<>();
    }

    @Override
    public String protect(String key) {
        String token = encoder.encode(key);
        protectedKeys.put(token, key);
        return token;
    }

    @Override
    public void unprotect(String token) {
        protectedKeys.remove(token);
    }

    @Override
    public String check(String token) {
        return protectedKeys.get(token);
    }
}
