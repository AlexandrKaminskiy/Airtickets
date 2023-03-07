package com.company.innowise.airticketsapp.businessservice.config;

import com.company.innowise.airticketsapp.businessservice.security.JwtFilter;
import com.company.innowise.airticketsapp.businessservice.security.PassengerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration {

    private final JwtFilter jwtFilter;
    private final PassengerDetailsService passengerDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/passenger/**")
                            .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/airport/**", "/api/flight/**")
                            .permitAll()
                        .requestMatchers("/api/airport/**", "/api/flight/**", "/api/history/**")
                            .hasRole("MANAGER")
                        .requestMatchers("/api/profile/**", "/api/payment/**", "/api/auth/refresh")
                            .hasRole("PASSENGER")
                        .requestMatchers("/api/auth/sign-in", "/api/auth/sign-up")
                            .anonymous()
                        .anyRequest()
                            .permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().disable()
                .csrf().disable()
                .authenticationManager(authenticationManager())
                .httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(passengerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
