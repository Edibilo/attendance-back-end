package iki.attendance.management.attendance.config;

import iki.attendance.management.attendance.security.JwtFilterToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SpringSecurity {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtFilterToken jwtFilterToken;

    public SpringSecurity(AuthenticationEntryPoint authenticationEntryPoint, JwtFilterToken jwtFilterToken) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtFilterToken = jwtFilterToken;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {

                    //Roles
                    request.requestMatchers(HttpMethod.OPTIONS,"/roles/**").hasRole("ADMIN");

                    //Auth
                    request.requestMatchers(HttpMethod.POST,"/auth/**").permitAll();

                    //classes
                    request.requestMatchers(HttpMethod.OPTIONS,"/classes/**").hasRole("ADMIN");

                    //classHistory
                    request.requestMatchers(HttpMethod.OPTIONS,"/class-history/**").hasRole("ADMIN");

                    //students
                    request.requestMatchers(HttpMethod.POST,"/students/**").hasAnyRole("ADMIN","STATISTICIAN");
                    request.requestMatchers(HttpMethod.GET,"/students/**").hasAnyRole("ADMIN","STATISTICIAN","TEACHER");
                    request.requestMatchers(HttpMethod.PUT,"/students/**").hasAnyRole("ADMIN","STATISTICIAN");
                    request.requestMatchers(HttpMethod.DELETE,"/students/**").hasAnyRole("ADMIN");

                    //attendances
                    request.requestMatchers(HttpMethod.OPTIONS,"/attendances/**").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.OPTIONS,"/dashboards/**").hasRole("ADMIN");

                    //class-history
                    request.requestMatchers(HttpMethod.OPTIONS,"/class-history").hasRole("ADMIN");

                    //Users
                    request.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("ADMIN","STATISTICIAN","TEACHER");
                    request.requestMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ADMIN","STATISTICIAN","TEACHER");

                    request.anyRequest().authenticated();
        });
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.exceptionHandling(httpHandling -> httpHandling.authenticationEntryPoint(authenticationEntryPoint));
        httpSecurity.addFilterBefore(jwtFilterToken, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource source(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("POST","GET","PATCH","DELETE","OPTIONS","PUT"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
