package KiwiDevSoft.clinia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import KiwiDevSoft.clinia.infrastructure.helpers.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter authenticationFilter;
        private final UserDetailsService userDetailsService;

        @Bean
        public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder);
                return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
                return new ProviderManager(authenticationProvider);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider)
                        throws Exception {

                final String[] PUBLIC_RESOURCES = {
                                "/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                };

                return http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers(PUBLIC_RESOURCES).permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(sessionManager -> sessionManager
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
