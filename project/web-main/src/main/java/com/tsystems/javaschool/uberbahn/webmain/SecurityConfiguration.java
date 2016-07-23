package com.tsystems.javaschool.uberbahn.webmain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/ticketPurchaseForm").hasAuthority("CLIENT")
                .antMatchers("/purchasedTicket").hasAuthority("CLIENT")
                .antMatchers("ticketsPurchased").hasAuthority("CLIENT")
                .antMatchers("/addStationForm").hasAuthority("EMPLOYEE")
                .antMatchers("/addedStation").hasAuthority("EMPLOYEE")
                .antMatchers("/addRouteForm").hasAuthority("EMPLOYEE")
                .antMatchers("/addStationsToRouteForm").hasAuthority("EMPLOYEE")
                .antMatchers("/routeInfo").hasAuthority("EMPLOYEE")
                .antMatchers("/addTrainForm").hasAuthority("EMPLOYEE")
                .antMatchers("/addedTrain").hasAuthority("EMPLOYEE")
                .antMatchers("/findTrainForm").hasAuthority("EMPLOYEE")
                .antMatchers("/tableOfTrains").hasAuthority("EMPLOYEE")
                .antMatchers("/listOfPassengers").hasAuthority("EMPLOYEE")
                .and().formLogin().loginPage("/loginPage")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/", false)
                .and().logout().logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage("/accessDeniedPage");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
