package com.example.springbootwebapplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication().withUser("Sumit").password("007").roles("USER","ADMIN");
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http)throws Exception{
//        http.authorizeRequests().antMatchers("/login").permitAll()
//                .antMatchers("/","/*todo*/**").access("hasRole('USER)").and()
//                .formLogin();
//
//    }

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Sumit")
                .password("007")
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeRequests((authorize) -> {
            try {
                authorize.antMatchers("/login", "/h2-console/**").permitAll()
                        .antMatchers("/", "/*todo*/**").hasRole("USER")
                        .and().formLogin()
                        .and().csrf().disable()
                        .headers().frameOptions().disable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }
}
