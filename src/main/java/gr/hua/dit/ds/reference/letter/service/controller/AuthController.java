package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.payload.LoginDto;
import gr.hua.dit.ds.reference.letter.service.repository.*;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

// REST API
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup/student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {

        if(userRepository.existsByUsername(student.getUser().getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = student.getUser();
        userService.registerUser(user, "ROLE_STUDENT");
        userService.registerStudent(student);

        return new ResponseEntity<>("Student registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signup/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody Teacher teacher) {

        if(userRepository.existsByUsername(teacher.getUser().getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = teacher.getUser();
        userService.registerUser(user, "ROLE_TEACHER");
        userService.registerTeacher(teacher);
        return new ResponseEntity<>("Teacher registered successfully", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public void getUsersData(){
        // TODO: return user's data
    }

    @PostMapping("/profile")
    public void updateUsersData(@Nullable @RequestBody Student student, @Nullable @RequestBody Teacher teacher){
        // TODO: update user's data according to arguments
    }

    @DeleteMapping("/profile")
    public void deleteUserAccount(){
        // TODO: delete user's account
    }

}
