package com.linhpete.java6.configuration;

import com.linhpete.java6.persistence.entity.User;
import com.linhpete.java6.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInitConfig {

    @NonFinal
    @Value("${security.password.default}")
    String defaultPassword;

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.existsByRole("admin")){
                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode(defaultPassword))
                        .role("admin")
                        .build();
                userRepository.save(user);
                log.warn("admin user created with default password : admin, please change it");
            }
        };
    }
}
