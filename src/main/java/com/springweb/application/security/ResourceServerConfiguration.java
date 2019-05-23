package com.springweb.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST,"/getUserDetailInfo").permitAll()
                .antMatchers(HttpMethod.POST, "/getStatusUser").permitAll()
                .antMatchers(HttpMethod.POST, "/project/addpro").permitAll()
                .antMatchers(HttpMethod.POST, "/project/listpro").permitAll()
                .antMatchers(HttpMethod.POST, "/project/detailPro").permitAll()
                .antMatchers(HttpMethod.POST, "/project/addMember").permitAll()
                .antMatchers(HttpMethod.POST, "/project/deleteProject").permitAll()
                .antMatchers(HttpMethod.POST, "/project/deleteTask").permitAll()
                .antMatchers(HttpMethod.POST, "/project/updateTask").permitAll()
                .antMatchers(HttpMethod.POST, "/task/addtask").permitAll()
                .antMatchers("/hello").access("hasRole('user')")
                .anyRequest()
                .authenticated();
    }

}
