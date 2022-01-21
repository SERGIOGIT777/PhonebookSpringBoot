package com.example.bootspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                    .inMemoryAuthentication()
                    .withUser("user")
                    .password("user")
                    .authorities("ROLE_USER")
                .and()
                    .withUser("admin")
                    .password("admin")
                    .authorities("ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole( "ADMIN")
                    .antMatchers("/user/**")
                    .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/**")
                    .hasAnyRole("USER")
                .and()
                    .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .loginProcessingUrl("/perform-login")
                    .usernameParameter("u")
                    .passwordParameter("p")
                    .defaultSuccessUrl("/home")
                .and()
                    .logout()
                    .permitAll();
    }
}
