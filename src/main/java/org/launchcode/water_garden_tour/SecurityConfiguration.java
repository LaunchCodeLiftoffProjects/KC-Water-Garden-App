package org.launchcode.water_garden_tour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    // To enable Spring Data in Spring Security https://www.baeldung.com/spring-data-security
    // "enables activation of automatic resolving of spring-data specific expressions annotated on classes"
//    @Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set configuration on auth object the method takes in



        auth.inMemoryAuthentication()
                .withUser("water")
                .password("garden")
                .roles("USER")
                .and()
                .withUser("water_garden")
                .password("admin")
                .roles("ADMIN");
    }
        @Bean
            public PasswordEncoder getPasswordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
