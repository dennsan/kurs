package com.tms.springsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig {
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(cust -> {
            cust
                    .requestMatchers("/", "/login", "/logout", "/registration").permitAll()
                    .requestMatchers("/applicant", "/applicant/update", "/applicant/logout").hasAnyRole("ADMIN", "APPLICANT")
                    .requestMatchers("/employer", "/employer/**", "/employer/delete").hasRole("EMPLOYER");

        });

        http.formLogin(cust -> {
            cust
                    .loginPage("/")
                    .loginProcessingUrl("/login")
                    .usernameParameter("login")
                    .passwordParameter("pass")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler((request, response, exception) -> response.sendRedirect("/"));

        });
        http.logout(cust -> {
            cust
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        });
//лучше отключать{
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        //}
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
