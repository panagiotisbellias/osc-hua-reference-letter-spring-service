package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.payload.*;
import gr.hua.dit.ds.reference.letter.service.repository.*;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST API Authentication Controller
 *
 * The REST API Authentication Controller provides operations like logging in as an existing user
 * and registering as a new one, either student or teacher. Also, profile operation is provided where
 * there is obtained all the current user's information, with the ability of being updated or deleted too.
 *
 * @author Panagiotis Bellias
 * @see <a href="https://www.javaguides.net/2021/10/login-and-registration-rest-api-using-spring-boot-spring-security-hibernate-mysql-database.html?m=1">...</a>
 * @since 2022-02-09
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:8081")
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
    private TeacherRepository teacherRepository;

    @Autowired
    private UserService userService;

    /**
     * Authenticate User Method
     * With this method we are able to login to the system, providing the parameter loginDto
     * @param loginDto is an object which represents the JSON object passed through the api and
     *                 includes user's username and password
     * @return a message that informs user that everything went good and user is logged in the system successfully
     */
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        // create authentication object passing username and password
        System.out.println("CREDENTIALS:"  + loginDto.toString());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        // authenticate this object using spring security
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK); // response
    }

    /**
     * Register Student Method
     * With this method we are able to sign up as a student to the system, providing the parameter signUpStudentDto
     * @param studentDto is an object which represents the JSON object passed through the api and
     *                         includes all the student's details
     * @return a message that informs student that everything went good and he/she has signed up successfully
     */
    @PostMapping("/signup/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentDto studentDto) {

        // check for username's existence
        if(userRepository.existsByUsername(studentDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); // inform user
        }

        /* Create a user object setting its values according to the corresponding arguments */
        User user = new User();
        user.setUsername(studentDto.getUsername());
        user.setPassword(studentDto.getPassword());
        // call user service to register user with certain role
        userService.registerUser(user, "ROLE_STUDENT");
        /* Create a student object setting its values accordingly */
        Student student = new Student();
        student.setFullName(studentDto.getFullName());
        student.setEmail(studentDto.getEmail());
        student.setSchool(studentDto.getSchool());
        student.setUniId(studentDto.getUniId());
        student.setUrlGradingFile(studentDto.getUrlGradingFile());
        student.setUser(user);
        userService.registerStudent(student); // call user service to register the student to the system

        return new ResponseEntity<>("Student registered successfully", HttpStatus.OK); // inform user
    }

    /**
     * Register Teacher Method
     * With this method we are able to sign up as a teacher to the system, providing the parameter signUpTeacherDto
     * @param teacherDto is an object which represents the JSON object passed through the api and
     *                         includes all the teacher's details
     * @return a message that informs teacher that everything went good and he/she has signed up successfully
     */
    @PostMapping("/signup/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherDto teacherDto) {

        // check for username's existence
        if(userRepository.existsByUsername(teacherDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); // inform user
        }

        /* Create a user object setting its values according to the corresponding arguments */
        User user = new User();
        user.setUsername(teacherDto.getUsername());
        user.setPassword(teacherDto.getPassword());
        // call user service to register user with certain role
        userService.registerUser(user, "ROLE_TEACHER");
        /* Create a teacher object setting its values accordingly */
        Teacher teacher = new Teacher();
        teacher.setFullName(teacherDto.getFullName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setDescription(teacherDto.getDescription());
        teacher.setUser(user);
        System.out.println(teacher);

        userService.registerTeacher(teacher); // call user service to register the teacher to the system
        return new ResponseEntity<>("Teacher registered successfully", HttpStatus.OK); // inform user
    }

    public ProfileDto getProfileDTO(Authentication authentication){

        // take username from authentication bean
        String username;
        try {
            username = authentication.getName();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new ProfileDto();
        }

        System.out.println("USERNAME: " + username);

        // Create profile object
        ProfileDto profileDto = new ProfileDto();

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            System.out.println("USERNAME 404: " + username);
            profileDto = new ProfileDto();
        } else {

            /* Check HERE, if turns False. We could leave authorities and do check for null directly to students*/
            Student student = studentRepository.findStudentByUser(user.get().getUsername());
            Teacher teacher = teacherRepository.findTeacherByUser(user.get().getUsername());
            if (student != null) {
                System.out.println("USERNAME 3: " + user.get().getUsername());
                profileDto.setUsername(username);
                profileDto.setFullName(student.getFullName());
                profileDto.setEmail(student.getEmail());
                profileDto.setSchool(student.getSchool());
                profileDto.setUniId(student.getUniId());
                profileDto.setUrlGradingFile(student.getUrlGradingFile());
                profileDto.setType("student");
            } else if (teacher != null) {
                profileDto.setUsername(username);
                profileDto.setFullName(teacher.getFullName());
                profileDto.setEmail(teacher.getEmail());
                profileDto.setDescription(teacher.getDescription());
                profileDto.setType("teacher");
            } else {
                System.out.println("ERROR!");
                profileDto = new ProfileDto();

            }
        }

        return profileDto;

    }

    /**
     * Get User's Data Method
     * With this method we are able to retrieve user's information, calling the Authentication bean
     * @param authentication is an object to take information for the current session
     *                       using the Authentication autowired bean
     * @return an object, which can contain either student's information or teacher's information
     */
    @GetMapping("/profile")
    public ProfileDto getUsersData(Authentication authentication){
        return getProfileDTO(authentication);
    }

    /**
     * Update User's Data Method
     * With this method we are able to update user's information, using these parameters
     * @param profileDto an object, which can contain either student's information or teacher's information
     * @param authentication is an object to take information for the current session
     *                       using the Authentication autowired bean
     * @return a message to inform user about his/her requests
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateUsersData(@RequestBody ProfileDto profileDto, Authentication authentication){

        ProfileDto profile = getProfileDTO(authentication);
        if (profile.equals(profileDto))
            return new ResponseEntity<>("No changes! All Set!",
                    HttpStatus.OK); // inform user

        Optional<User> userFromDB = userRepository.findByUsername(profile.getUsername());
        if (userFromDB.isEmpty())
            return new ResponseEntity<>("No user found with that username",
                    HttpStatus.BAD_REQUEST); // inform user

        User user = userFromDB.get();
        // user area
        if (!profile.getUsername().equals(profileDto.getUsername())) {
            user.setUsername(profileDto.getUsername());
            userRepository.save(user);
        }


        if (profileDto.getType().equals("student")) {
            Student student = studentRepository.findStudentByUser(profile.getUsername());
            // student area
            student.setUser(user);
            student.setFullName(profileDto.getFullName());
            student.setEmail(profileDto.getEmail());
            student.setSchool(profileDto.getSchool());
            student.setUniId(profileDto.getUniId());
            student.setUrlGradingFile(profileDto.getUrlGradingFile());
            studentRepository.save(student);

            return new ResponseEntity<>("Student profile updated",
                    HttpStatus.OK); // inform user
        } else if (profileDto.getType().equals("teacher")) {
            // teacher area
            Teacher teacher = teacherRepository.findTeacherByUser(profile.getUsername());
            teacher.setUser(user);
            teacher.setFullName(profileDto.getFullName());
            teacher.setEmail(profileDto.getEmail());
            teacher.setDescription(profileDto.getDescription());
            teacherRepository.save(teacher);
            return new ResponseEntity<>("Teacher profile updated",
                    HttpStatus.OK); // inform user
        } else return new ResponseEntity<>("User type not found",
                HttpStatus.NOT_FOUND);

    }

    /**
     * Delete User's Account Method
     * With this method we are able to delete user's information, using these parameters
     * @param authentication is an object to take information for the current session
     *                       using the Authentication autowired bean
     * @return a message to inform user about his/her requests
     */
    @DeleteMapping("/profile")
    public ResponseEntity<?> deleteUserAccount(Authentication authentication){
        ProfileDto profile = getProfileDTO(authentication);
        Optional<User> user = userRepository.findByUsername(profile.getUsername());
        if (user.isEmpty())
            return new ResponseEntity<>("User Not Found!",
                    HttpStatus.NOT_FOUND); // inform user

        User thisUser = user.get();
        thisUser.setEnabled(0);
        userRepository.save(thisUser);
        return new ResponseEntity<>("User Account is Disabled!", HttpStatus.OK); // inform user

    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser() {
	//... TODO implementation
	return new ResponseEntity<>("User logged out successfully!.", HttpStatus.OK);
    }

}
