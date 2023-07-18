package com.nicode.gestionenfermeria.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(customRequest -> customRequest

                                                    .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                                                    .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN", "CUSTOM", "VIEWER")
                                                    .requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole("ADMIN", "CUSTOM")
                                                    .requestMatchers(HttpMethod.POST,"/api/**").hasAnyRole("ADMIN", "CUSTOM")
                                                    .requestMatchers(HttpMethod.DELETE,"/api/pacientes/**").hasAnyRole("ADMIN", "CUSTOM")
                                                    .requestMatchers(HttpMethod.DELETE,"/api/enfermeros/**").hasRole("ADMIN")
                                                    .anyRequest().authenticated())
                                                    .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
                                                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                    .csrf(AbstractHttpConfigurer::disable)
                                                    .cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
