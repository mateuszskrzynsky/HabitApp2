package com.habitsapp.confiq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security, defining security filters and rules for the application.
 * Enables web security and method-level security with annotations. It specifies the authorization
 * requirements for different URL patterns and configures an in-memory user for authentication.
 *
 * Security Configuration:
 * - Disables CSRF protection for simplicity.
 * - Restricts access to paths under "/goal/**", "/**", and "/habit/**" to users with the "USER" role.
 * - Allows public access to "/user/registration" and "/user/login".
 * - Configures HTTP Basic authentication.
 *
 * Additionally, it defines a default UserService with a single in-memory user (USER)
 * and a BCryptPasswordEncoder for password encoding. It also provides a custom AuthenticationManager bean
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(autz->
                        autz
                                .requestMatchers("/goal/**").hasRole("USER")
                                .requestMatchers("/**").hasRole("USER")
                                .requestMatchers("habit/**").hasRole("USER")
                                .requestMatchers("user/registration").permitAll()
                                .requestMatchers("user/login").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails user1= User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}