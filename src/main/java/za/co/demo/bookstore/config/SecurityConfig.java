package za.co.demo.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            try {
                auth.requestMatchers("/","/swagger-ui*/**","/v3/api-docs/**")
                        .permitAll().anyRequest().authenticated();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).oauth2Login(Customizer.withDefaults()).csrf().disable();

        return http.build();

    }
}
