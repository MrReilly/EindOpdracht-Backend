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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service,@Qualifier("users") UserRepository userRepos) {
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
                .antMatchers(HttpMethod.POST, "/review/**").hasAnyAuthority("VISITOR")
                .antMatchers(HttpMethod.DELETE,"/review/**").hasAnyAuthority("VISITOR")
                .antMatchers(HttpMethod.POST, "/event").hasAnyAuthority("ORGANIZER")
                .antMatchers(HttpMethod.PUT, "/event/**").hasAnyAuthority("ORGANIZER")
                .antMatchers(HttpMethod.GET,"/event/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/event/**").hasAnyAuthority("ORGANIZER")
                .antMatchers("/user/myFavorites/**").hasAnyAuthority("VISITOR")
                .antMatchers("/user/myFavorites").hasAnyAuthority("VISITOR")
                .antMatchers("/user/myEvents/**").hasAnyAuthority("ORGANIZER")
                .antMatchers("/user/myEvents").hasAnyAuthority("ORGANIZER")
                .antMatchers("/category/**").hasAnyAuthority("VISITOR", "ORGANIZER")
                .antMatchers("/**").hasAnyAuthority("ORGANIZER", "VISITOR")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}