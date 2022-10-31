package com.evgshev.spring.springrest.PP3_1_4.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SuccessUserHandler successUserHandler;

    private final UserDetailsService userDetailsService;


    public WebSecurityConfig(PasswordEncoder passwordEncoder, SuccessUserHandler successUserHandler, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/logins/").permitAll()
//                .antMatchers("/login/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/admin").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user-create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/user-update/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user-delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
//                .defaultSuccessUrl("/enter")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");
//                .logoutSuccessUrl("/login")
    }



//    @Bean
//    public JdbcUserDetailsManager user(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }




    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}