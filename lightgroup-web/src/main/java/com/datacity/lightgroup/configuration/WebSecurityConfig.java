package com.datacity.lightgroup.configuration;

import com.datacity.lightgroup.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder gePasswordEncoder() {
        return new BCryptPasswordEncoder( 8 );
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userService )
                .passwordEncoder( passwordEncoder );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/", "/static/**", "/image/**", "/message/send", "/portfolio/**" )
                .permitAll()
                .antMatchers( "/admin/**" ).hasAuthority( "ADMIN" )
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage( "/login" )
                .defaultSuccessUrl( "/admin/texts" )
                .permitAll()
                .and()
                .logout()
                .permitAll().and()
                .exceptionHandling().accessDeniedPage( "/" );
        http.csrf().ignoringAntMatchers( "/message/send" ).disable();
    }

}