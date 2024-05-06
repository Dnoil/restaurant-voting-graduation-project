package com.github.dnoil.restaurant.voting.security;

import com.github.dnoil.restaurant.voting.model.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class SecurityConfig {
    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**").authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/restaurants/**")
                            .hasAuthority(Role.USER.getAuthority())
                        .requestMatchers("/api/admin/**")
                            .hasAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.POST, "/api/user").anonymous()
                        .requestMatchers("/", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .requestMatchers("/**").authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
