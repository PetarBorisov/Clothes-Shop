package com.example.clothes_shop.config;

import com.example.clothes_shop.repository.UserRepository;
import com.example.clothes_shop.service.impl.ClothesShopUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${clothes_shop.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Define which urls are visible by which users
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/comments/delete/{id}/**").hasRole("ADMIN")
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers( "/","/users/login", "/users/register", "/users/login-error", "/css/style.css").permitAll()
                        .requestMatchers("/api/comments/add","/api/comments/view").permitAll()
                        // all other requests are authenticated.*/
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            formLogin
                                    // redirect here when we access something which is not allowed.
                                    // also this is the page where we perform login.
                                    .loginPage("/users/login")
                                    // The names of the input fields (in our case in auth-login.html)
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/",true)
                                    .failureForwardUrl("/users/login-error");
                        }

                ).logout(
                        logout -> {
                            logout
                                    // the URL where we should POST something in order to perform the logout
                                    .logoutUrl("/users/logout")
                                    // where to go when logged out?
                                    .logoutSuccessUrl("/")
                                    // invalidate the HTTP session
                                    .invalidateHttpSession(true);
                        }

                ).rememberMe(
                        rememberMe -> {
                            rememberMe
                                    .key(rememberMeKey)
                                    .rememberMeParameter("rememberme")
                                    .rememberMeCookieName("rememberme");
                        }
                ).build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the restaurant users and roles
        // to representation which spring security understands.
        return new ClothesShopUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    }
}
