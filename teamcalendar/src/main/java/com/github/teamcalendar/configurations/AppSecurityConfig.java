package com.github.teamcalendar.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {

//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.csrf().disable()

                .authorizeRequests()

                .antMatchers("/admincp/**").hasAnyRole("ADMINISTRATOR")

                .antMatchers("/calendar/**").hasAnyRole("ADMINISTRATOR", "USER")

                .antMatchers("/login**").permitAll()

                .and()

                .formLogin().loginPage("/login").loginProcessingUrl("/login.xhtml").failureUrl("/login?error").defaultSuccessUrl("/", true)
                .successHandler(authenticationSuccessHandler)

                .and()

                .logout().invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()

                .httpBasic();

    }
}
