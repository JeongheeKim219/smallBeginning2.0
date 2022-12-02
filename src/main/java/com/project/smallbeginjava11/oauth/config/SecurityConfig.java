package com.project.smallbeginjava11.oauth.config;

import com.project.smallbeginjava11.oauth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuthService oAuthService;
    private final JWTRequestFilter jwtRequestFilter;


    public SecurityConfig(OAuthService oAuthService, JWTRequestFilter jwtRequestFilter) {
        this.oAuthService = oAuthService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors();
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests()
//                .antMatchers("/login/oauth2/**/**").permitAll()
//                .anyRequest().authenticated();
//
//
////        http.
////            csrf()
////            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////            .ignoringAntMatchers("/login/oauth2/code/google");
////
        http
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
////        .antMatchers("/api/v1/**").hasRole(Role.MEMBER.name())
        .antMatchers("/oauth/login/google").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .oauth2Login()
        .userInfoEndpoint()
        .userService(oAuthService);
    }
}
