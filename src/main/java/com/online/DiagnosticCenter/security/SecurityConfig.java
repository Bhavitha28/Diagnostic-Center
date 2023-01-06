package com.online.DiagnosticCenter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private LoginAuthService service;

        @Autowired
        private JwtFilter jwtFilter;
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(service);
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }

//        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()throws Exception{
            return super.authenticationManagerBean();
        }

//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().
//                    disable()
//                    .authorizeRequests()
//                    .antMatchers("/authenticate","/swagger-ui.html","/v2/api-docs","/exam/fetchAll").permitAll().anyRequest().authenticated()
//
//                    .and().exceptionHandling().and().sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
//        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf()
                .disable()
                .authorizeRequests().antMatchers("/authenticate","/user/Signup","/pg/createOrder","/user/getAllUsers")
                .permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


    }

//    public  void configure(WebSecurity web){
//            web.ignoring()
//                    .antMatchers(
//                            "/","/authenticate");
////            "/Employee/getOneEmployee/{employeeId}","/Employee/get-payslips/{id}","/user/updateAdmin","/user/Signup","/user/getByUsername/{username}","/admin/getAllEmployees","/admin/addEmployee","/admin/deleteEmployee/{id}","/workdays/getAllWorkdays","/admin/getOneEmployee/{id}","/admin/updateEmployee","/user/getByUsername/{username}","/admin/createPayslips","/workdays/upload","admin/generate-payroll","/v2/api-docs"
//
//    }
}

