package com.Student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Student.exception.ResourceNotFoundException;
import com.Student.model.Student;
import com.Student.repository.StudentRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
	private StudentRepository studentRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database username
		Student student = this.studentRepository.findByEmail(username)
				 .orElseThrow(() -> new ResourceNotFoundException("User", "email :" + username, 0));
		return student;
	}

}
