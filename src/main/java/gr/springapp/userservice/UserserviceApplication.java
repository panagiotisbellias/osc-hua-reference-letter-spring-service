package gr.springapp.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import gr.springapp.userservice.domain.ReferenceLetterRequest;
import gr.springapp.userservice.domain.Role;
import gr.springapp.userservice.domain.User;
import gr.springapp.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_STUDENT"));
			userService.saveRole(new Role(null, "ROLE_TEACHER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Wil Smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_STUDENT");
			userService.addRoleToUser("will", "ROLE_STUDENT");
			userService.addRoleToUser("jim", "ROLE_TEACHER");
			userService.addRoleToUser("arnold", "ROLE_TEACHER");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");

			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 0", "john", "arnold", "Harokopio", "hua.gr", "PENDING", ""));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 2", "john", "arnold", "Harokopio", "hua.gr", "PENDING", ""));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 3", "will", "arnold", "Harokopio", "hua.gr", "PENDING", ""));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 4", "will", "jim", "Harokopio", "hua.gr", "PENDING", ""));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 5", "john", "jim", "Harokopio", "hua.gr", "PENDING", ""));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 6", "john", "jim", "Harokopio", "hua.gr", "APPROVED", "sdfgsdfgsdgfdgsdg Demo6"));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 7", "john", "jim", "Harokopio", "hua.gr", "DECLINED", "sdfgsdfgsdgfdgsdg Demo7"));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 6", "john", "arnold", "Harokopio", "hua.gr", "APPROVED", "sdfgsdfgsdgfdgsdg Demo8"));
			userService.saveReferenceLetterRequest(new ReferenceLetterRequest(null, "Demo Title 7", "will", "arnold", "Harokopio", "hua.gr", "DECLINED", "sdfgsdfgsdgfdgsdg Demo9"));

		};
	}

}
