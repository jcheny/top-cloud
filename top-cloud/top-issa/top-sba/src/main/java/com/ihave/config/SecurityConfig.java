package com.ihave.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userServiceDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminServerProperties adminServerProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String path = adminServerProperties.getContextPath();

        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                //静态资源和login以及健康检查
                .antMatchers(path + "/assets/**").permitAll()
                .antMatchers(path + "/login").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().hasAuthority("SBA_BOOT")
                .and()
                .formLogin().loginPage(path + "/login")
                .successHandler(successHandler)
                .and().logout().logoutUrl(path + "/logout")
                .and().httpBasic().and().csrf().disable();
    }


    /**
     * 注入密码的验证管理器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }


}