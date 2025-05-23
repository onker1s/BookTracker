package booktracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/library/**", "/plib/**", "/review/**","/account").hasRole("USER")
                        .anyRequest().permitAll()  // Все остальные запросы разрешены
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указание страницы логина
                        .permitAll()  // Разрешение всем пользователям доступ к странице логина
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // URL после успешного выхода
                        .permitAll()  // Разрешение всем пользователям доступ к выходу
                )
                .headers(headers -> headers
                        .defaultsDisabled()
                        .cacheControl(withDefaults())
                );


        return http.build();
    }


}