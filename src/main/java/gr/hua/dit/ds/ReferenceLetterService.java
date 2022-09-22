package gr.hua.dit.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:8080")
public class ReferenceLetterService {

	public static void main(String[] args) {
		SpringApplication.run(ReferenceLetterService.class, args);
	}

}
