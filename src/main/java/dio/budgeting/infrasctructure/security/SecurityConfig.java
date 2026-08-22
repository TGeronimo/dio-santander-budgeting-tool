package dio.budgeting.infrasctructure.security;

import dio.budgeting.infrasctructure.security.authentication.AuthProvider;
import dio.budgeting.infrasctructure.security.jwt.JwtAuthenticationFilter;
import dio.budgeting.infrasctructure.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtService jwtService,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // APIs não usam CSRF
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sem sessão
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // libera o /hello
                        .anyRequest().authenticated() // protege o resto
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                (req, res, authException) ->
                                        res.sendError(401)))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable()) // desliga o form login
                .httpBasic(basic -> basic.disable()); // desliga basic auth

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity https,
            AuthProvider authProvider
    ) throws Exception {
        return https.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider)
                .build();
    }
}
