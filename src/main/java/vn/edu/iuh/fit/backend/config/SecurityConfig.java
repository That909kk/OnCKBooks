package vn.edu.iuh.fit.backend.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder builder, PasswordEncoder encoder) throws Exception{
        builder.inMemoryAuthentication()
                .withUser(User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build())
                .withUser(User.withUsername("user").password(encoder.encode("user")).roles("USER").build());
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity security) throws Exception {
        security.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                    http->
                    {
                        http.requestMatchers("/home").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/books").hasRole("ADMIN")
                                .anyRequest().permitAll();

                    }

                );
        security.formLogin(
                    Customizer.withDefaults())
                .logout(
                        Customizer.withDefaults()
                );
        return security.build();
    }
}
