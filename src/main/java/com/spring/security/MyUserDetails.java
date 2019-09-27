package com.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MyUserDetails {

    private String userName;
    private String password;
    private String[] roles;

    public MyUserDetails(String userName, String password, String[] roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public MyUserDetails() {
    }

    static List<MyUserDetails> users = Arrays.asList(new MyUserDetails("prakash","kumar",new String[]{"admin","dev"}),
            new MyUserDetails("test","1234",new String[]{"dev"}));

    static Map<String,MyUserDetails> userMap =users.stream().collect(Collectors.toMap(MyUserDetails::getUserName, Function.identity()));

    static List<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("DEV"));

    public UserDetails loadUserByUsername(String username) {
        MyUserDetails myUserDetails = userMap.get(username);
        if(myUserDetails==null) throw new RuntimeException("Username doesn't exist");
        return new User(myUserDetails.getUserName(),myUserDetails.getPassword(), authorities);

    }
}
