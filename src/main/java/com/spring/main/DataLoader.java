package com.spring.main;

import com.spring.domain.Employee;
import com.spring.domain.Manager;
import com.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);

       Authentication auth =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(new User("prakash","kumar", Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))),"kumar",Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))));
       SecurityContextHolder.getContext().setAuthentication(auth);
       this.employeeRepository.save(new Employee("prakash","kumar","I am a software engineer",new Manager("mm","masalawala", new String[]{"manager"})));
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
        //passwordEncoder.encode("kumar");
        System.out.println(passwordEncoder.matches("kumar",passwordEncoder.encode("kumar")));
    }
}
