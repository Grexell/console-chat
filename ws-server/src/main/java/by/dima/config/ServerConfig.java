package by.dima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"by.dima.util", "by.dima.model"})
public class ServerConfig {
}
