package com.score.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication().dataSource( dataSource )
                .usersByUsernameQuery(
                        "select login as principal,password as credentials,active from user where login=?" )
                .authoritiesByUsernameQuery( "select login as principal,role as role from authorities where login=?" )
                .passwordEncoder( new BCryptPasswordEncoder() );

    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.formLogin().loginPage( "/user/login" ).defaultSuccessUrl( "/" );
        http.authorizeRequests()
                .antMatchers( "/matche/addMatche", "/athletic/addAthletic", "/club/addClub", "/stadium/addStadium",
                        "/league/addLeague", "/continent/addContinent" )
                .hasAuthority( "ADMIN" );
        http.authorizeRequests()
                .antMatchers( "/matche/listMatche", "/athletic/listAthletic", "/club/listClub", "/stadium/listStadium",
                        "/league/listLeague", "/continent/listContinent" )
                .hasAnyAuthority( "USER","ADMIN" );
        http.exceptionHandling().accessDeniedPage( "/user/403" );
    }

}
