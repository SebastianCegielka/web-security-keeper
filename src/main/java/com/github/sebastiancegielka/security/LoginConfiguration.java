package com.github.sebastiancegielka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LoginConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable();

        http
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/registrationConfirm").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/login")
                .failureHandler(
                        (req, resp, e) -> resp.sendError(HttpStatus.I_AM_A_TEAPOT.value(), "Invalid username or password"))
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/swagger-ui.html").permitAll();

        http
                .logout()
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
