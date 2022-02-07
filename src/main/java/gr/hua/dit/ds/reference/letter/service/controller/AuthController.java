package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.payload.*;
import gr.hua.dit.ds.reference.letter.service.repository.*;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// REST API
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

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
    public ResponseEntity<?> registerStudent(@RequestBody SignUpStudentDto signUpStudentDto) {

        if(userRepository.existsByUsername(signUpStudentDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(signUpStudentDto.getUsername());
        user.setPassword(signUpStudentDto.getPassword());
        userService.registerUser(user, "ROLE_STUDENT");
        Student student = new Student();
        student.setFullName(signUpStudentDto.getFullName());
        student.setEmail(signUpStudentDto.getEmail());
        student.setSchool(signUpStudentDto.getSchool());
        student.setUniId(signUpStudentDto.getUniId());
        student.setUrlGradingFile(signUpStudentDto.getUrlGradingFile());
        student.setUser(user);
        userService.registerStudent(student);

        return new ResponseEntity<>("Student registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signup/teacher") /* TODO: fix teacher sign up,
    using SignUpTeacherDto and add properly his attributes */
    public ResponseEntity<?> registerTeacher(@RequestBody Teacher teacher) {

        if(userRepository.existsByUsername(teacher.getUser().getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = teacher.getUser();
        userService.registerUser(user, "ROLE_TEACHER");
        userService.registerTeacher(teacher);
        return new ResponseEntity<>("Teacher registered successfully", HttpStatus.OK);
    }

    // TODO: Implement the method so as to find out if he is student or teacher and
    // return all personal info in a object
    @GetMapping("/profile")
    public Object getUsersData(Authentication authentication){

        String username = authentication.getName();
        return new Student();
    }

    @PostMapping("/profile")
    public void updateUsersData(@Nullable @RequestBody SignUpStudentDto signUpStudentDto,
                                @Nullable @RequestBody Teacher teacher, Authentication authentication){
        Object user = getUsersData(authentication);
        // TODO: check and update user's data according to arguments
    }

    @DeleteMapping("/profile")
    public void deleteUserAccount(Authentication authentication){
        Object user = getUsersData(authentication);
        // TODO: delete user's account
    }

}
