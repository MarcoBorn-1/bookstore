package com.example.bookstore.config;

import com.example.bookstore.service.CustomerManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.example.bookstore.config.SecurityConstants.LOGIN_URL;
import static com.example.bookstore.config.SecurityConstants.SIGN_UP_URL;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomerManager customerManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerManager userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            // swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/books/get/**").permitAll()
                .antMatchers("/api/books/search").permitAll()
                .antMatchers("/api/books/pagination/**").permitAll()
                .antMatchers("/api/books/save").hasAuthority("ADMIN")
                .antMatchers("/api/books/upd").hasAuthority("ADMIN")
                .antMatchers("/api/books/del/**").hasAuthority("ADMIN")
                .antMatchers("/api/books/get/photo/**").permitAll()

                .antMatchers("/api/categories/get/**").permitAll()
                .antMatchers("/api/categories/save").hasAuthority("ADMIN")
                .antMatchers("/api/categories/upd").hasAuthority("ADMIN")
                .antMatchers("/api/categories/del/**").hasAuthority("ADMIN")

                .antMatchers("/api/customers/**").hasAuthority("ADMIN")
                .antMatchers("/api/userOrders/**").hasAuthority("ADMIN")
                .antMatchers("/api/userSubOrders/**").hasAuthority("ADMIN")

                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                //.antMatchers("/**").permitAll()

                .anyRequest().authenticated();
                http.addFilterBefore(new JWTAuthorizationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-resources/", "/webjars/").antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(daoAuthenticationProvider());

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(customerManager);
        return daoAuthenticationProvider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
