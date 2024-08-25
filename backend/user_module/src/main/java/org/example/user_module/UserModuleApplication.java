package org.example.user_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.user_module.repository")
public class UserModuleApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserModuleApplication.class, args);
    }

}
