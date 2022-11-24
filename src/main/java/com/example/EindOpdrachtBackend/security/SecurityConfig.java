package com.example.EindOpdrachtBackend.security;

import com.example.EindOpdrachtBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, @Qualifier("users") UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/image/**").permitAll()
                .antMatchers(HttpMethod.GET, "/review/**").permitAll()
                .antMatchers(HttpMethod.POST, "/review/**").hasAnyAuthority("VISITOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/review/**").hasAnyAuthority("VISITOR", "ADMIN")
                .antMatchers(HttpMethod.POST, "/event").hasAnyAuthority("ORGANIZER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/event/**").hasAnyAuthority("ORGANIZER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/event/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/event/**").hasAnyAuthority("ORGANIZER", "ADMIN")
                .antMatchers("/user/myFavorites/**").hasAnyAuthority("VISITOR", "ORGANIZER", "ADMIN")
                .antMatchers("/user/myFavorites").hasAnyAuthority("VISITOR", "ORGANIZER", "ADMIN")
                .antMatchers("/user/myEvents/**").hasAnyAuthority("ORGANIZER", "ADMIN")
                .antMatchers("/user/myEvents").hasAnyAuthority("ORGANIZER", "ADMIN")
                .antMatchers("/category/**").hasAnyAuthority("VISITOR", "ORGANIZER", "ADMIN")
                .antMatchers("/**").hasAnyAuthority("ORGANIZER", "VISITOR", "ADMIN")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}



