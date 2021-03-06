package com.ali.bugtracker.security;

import com.ali.bugtracker.entities.Employee;
import com.ali.bugtracker.repositories.EmployeeRepository;
import com.ali.bugtracker.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || !authentication.isAuthenticated()){
            return null;
        }
        User user =(User)authentication.getPrincipal();
        return Optional.of(user.getUsername());
    }
}
