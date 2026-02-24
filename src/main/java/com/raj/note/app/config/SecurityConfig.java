package com.raj.note.app;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Enable CORS using the configuration source below
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // 2. Disable CSRF (Standard for REST APIs)
            .csrf(csrf -> csrf.disable())
            
            // 3. Set your endpoint rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register").permitAll() // Anyone can register
                .requestMatchers("/h2-console/**").permitAll() // (Optional) If you still use H2
                .anyRequest().authenticated() // Everything else requires login
            )
            
            // 4. Enable Basic Authentication
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Allow all frontend domains (You can lock this down to just your GitHub Pages URL later)
        configuration.setAllowedOriginPatterns(List.of("*")); 
        
        // Allow standard HTTP methods like GET, POST, DELETE
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Allow headers like Authorization and Content-Type
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        // Allow credentials (required for Basic Auth headers to pass through)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

