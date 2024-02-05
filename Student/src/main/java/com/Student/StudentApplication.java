package com.Student;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Student.config.AppConstant;
import com.Student.model.Role;
import com.Student.repository.RoleRepository;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	@Bean
	 ModelMapper modelMapper() {
		return new ModelMapper();	
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setRid(AppConstant.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			Role role1 = new Role();
			role1.setRid(AppConstant.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role,role1);
			List<Role> result = this.roleRepository.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
