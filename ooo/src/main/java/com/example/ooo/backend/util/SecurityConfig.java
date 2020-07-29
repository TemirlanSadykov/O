package com.example.ooo.backend.util;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/")
                .failureUrl("/?error=true")
                .defaultSuccessUrl("/user", true);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/user/**")
                .authenticated();

        http.authorizeRequests()
                .antMatchers("/")
                .anonymous()
                .anyRequest()
                .permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String fetchAdminsQuery = "select login, password, enabled"
                + " from users"
                + " where login = ?";

        String fetchRolesQuery = "select login, role" +
                " from users" +
                " where login = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchAdminsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery)
                .dataSource(dataSource);
    }
}
