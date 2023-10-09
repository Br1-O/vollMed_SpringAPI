package med.voll.api.model.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//allows to use @Secure("ROLE_ADMIN) on methods and classes paths
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfigurations {

    //dependency injection
        private SecurityFilter securityFilter;
        public SecurityConfigurations(SecurityFilter securityFilter){
            this.securityFilter=securityFilter;
        }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //allows requests comming from this path
                .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                //allows delete request for that path ONLY for users that have the role "admin"
                .requestMatchers(HttpMethod.DELETE, "/medicos/borrar").hasRole("admin")
                //ask all other requests to be authenticated
                .anyRequest().authenticated()
                .and()
                //adds our own filter before spring filters trigger
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                //UsernamePasswordAuthenticationFilter parameter checks that user/password is valid and is authenticated/logged in
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
