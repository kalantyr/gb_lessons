package com.flamexander.spring.security.cookbook.dao.configs;

import com.flamexander.spring.security.cookbook.dao.services.Right;
import com.flamexander.spring.security.cookbook.dao.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Dao Authentication Provider");

        http
                .authorizeRequests()
                    .antMatchers("/school/lessons").hasAuthority(Right.LessonVew.name())
                    .antMatchers("/school/grades").hasAuthority(Right.GradeView.name())
                    .antMatchers("/school/homework").hasAuthority(Right.SubmitHomework.name())
                    .antMatchers("/school/grade/**").hasAuthority(Right.GiveGrade.name())
                    .antMatchers("/school/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                    .httpBasic() // чтобы из Postman можно было логиниться
                .and().
                    csrf().disable(); // чтобы PUT-запросы работали
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}