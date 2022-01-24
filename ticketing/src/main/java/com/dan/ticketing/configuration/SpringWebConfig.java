package com.dan.ticketing.configuration;

import com.dan.ticketing.interceptor.UserDataInterceptor;
import com.dan.ticketing.security.CustomUserDetailsService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.dan.ticketing.controller", "com.dan.ticketing.test"})
@EnableJpaRepositories(basePackages = "com.dan.ticketing.repo")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SpringWebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final String SECRET_KEY = "123456";

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public LayoutDialect layoutDialect() {

        return new LayoutDialect();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login") // Custom user login page
                .failureUrl("/login?error") // Customize the failed login page. The front end can provide friendly user login prompts by using error s in the url.
                .and()
                .logout()
                .clearAuthentication(true)
                .and()
                .rememberMe() // Turn on Password Memory
                .rememberMeServices(getRememberMeServices()) // Must be provided
                .key(SECRET_KEY) // This SECRET needs the same key as the one used to generate TokenBasedRememberMeServices
                .and()
                /*
                 * By default, all paths are accessible to everyone, ensuring normal access to static resources.
                 * Later, the method annotations are used to control the right of control.
                 */
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403"); // Insufficient permission automatic jump 403
    }

    private TokenBasedRememberMeServices getRememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(SECRET_KEY, customUserDetailsService);
        services.setCookieName("remember-cookie");
        services.setTokenValiditySeconds(100); // Default 14 days
        return services;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // LogInterceptor apply to all URLs.
        registry.addInterceptor(new UserDataInterceptor()).excludePathPatterns("/login", "/logout");

    }

}
