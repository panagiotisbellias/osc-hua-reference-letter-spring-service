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
@CrossOrigin(origins = "http://localhost:8080")
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
    private CourseRepository courseRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserService userService;

    /**
     * Authenticate User Method
     * With this method we are able to login to the system, providing the parameter loginDto
     * @param loginDto is an object which represents the JSON object passed through the api and
     *                 includes user's username and password
     * @return a message that informs user that everything went good and user is logged in the system successfully
     * @todo test it with frontend
     */
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        // create authentication object passing username and password
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        // authenticate this object using spring security
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK); // response
    }

    /**
     * Register Student Method
     * With this method we are able to sign up as a student to the system, providing the parameter signUpStudentDto
     * @param signUpStudentDto is an object which represents the JSON object passed through the api and
     *                         includes all the student's details
     * @return a message that informs student that everything went good and he/she has signed up successfully
     * @todo test it with frontend
     */
    @PostMapping("/signup/student")
    public ResponseEntity<?> registerStudent(@RequestBody SignUpStudentDto signUpStudentDto) {

        // check for username's existence
        if(userRepository.existsByUsername(signUpStudentDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); // inform user
        }

        /* Create a user object setting its values according to the corresponding arguments */
        User user = new User();
        user.setUsername(signUpStudentDto.getUsername());
        user.setPassword(signUpStudentDto.getPassword());
        // call user service to register user with certain role
        userService.registerUser(user, "ROLE_STUDENT");
        /* Create a student object setting its values accordingly */
        Student student = new Student();
        student.setFullName(signUpStudentDto.getFullName());
        student.setEmail(signUpStudentDto.getEmail());
        student.setSchool(signUpStudentDto.getSchool());
        student.setUniId(signUpStudentDto.getUniId());
        student.setUrlGradingFile(signUpStudentDto.getUrlGradingFile());
        student.setUser(user);
        userService.registerStudent(student); // call user service to register the student to the system

        return new ResponseEntity<>("Student registered successfully", HttpStatus.OK); // inform user
    }

    /**
     * Register Teacher Method
     * With this method we are able to sign up as a teacher to the system, providing the parameter signUpTeacherDto
     * @param signUpTeacherDto is an object which represents the JSON object passed through the api and
     *                         includes all the teacher's details
     * @return a message that informs teacher that everything went good and he/she has signed up successfully
     * @todo one-to-many, add also many-to-one to courses and certificates, test it with postman (or frontend)
     */
    @PostMapping("/signup/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody SignUpTeacherDto signUpTeacherDto) {

        // check for username's existence
        if(userRepository.existsByUsername(signUpTeacherDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); // inform user
        }

        /* Create a user object setting its values according to the corresponding arguments */
        User user = new User();
        user.setUsername(signUpTeacherDto.getUsername());
        user.setPassword(signUpTeacherDto.getPassword());
        // call user service to register user with certain role
        userService.registerUser(user, "ROLE_TEACHER");
        /* Create a teacher object setting its values accordingly */
        Teacher teacher = new Teacher();
        teacher.setFullName(signUpTeacherDto.getFullName());
        teacher.setEmail(signUpTeacherDto.getEmail());

        for (CourseDto courseDto : signUpTeacherDto.getCourses()) {
            Course course = new Course();
            course.setTitle(courseDto.getTitle());
            course.setUniversity(courseDto.getUniversity());
            teacher.addCourse(course);
        }
        for (CertificateDto certificateDto : signUpTeacherDto.getCertificates()) {
            Certificate certificate = new Certificate();
            certificate.setTitle(certificateDto.getTitle());
            certificate.setUniversity(certificateDto.getUniversity());
            teacher.addCertificate(certificate);
        }

        teacher.setUser(user);
        userService.registerTeacher(teacher); // call user service to register the teacher to the system
        return new ResponseEntity<>("Teacher registered successfully", HttpStatus.OK); // inform user
    }

    /**
     * Get User's Data Method
     * With this method we are able to retrieve user's information, calling the Authentication bean
     * @param authentication is an object to take information for the current session
     *                       using the Authentication autowired bean
     * @return an object, which can contain either student's information or teacher's information
     * @todo test it with postman (or frontend)
     */
    @GetMapping("/profile")
    public ProfileDto getUsersData(Authentication authentication){

        // take username from authentication bean
        String username = authentication.getName();
        System.out.println("USERNAME: " + username);

        // Create profile object
        ProfileDto profileDto = new ProfileDto();

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            System.out.println("USERNAME 2: " + username);
            return new ProfileDto();
        }

        /* Set its attributes accordingly after taking the data */
        Collection<Authorities> authorities = user.get().getAuthorities();
        /* Check HERE, if turns False. We could leave authorities and do check for null directly to students*/
        if (authorities.contains(new Authorities("ROLE_STUDENT", user.get()))){
            System.out.println("USERNAME 3: " + user.get().getUsername());
            Student student = studentRepository.findStudentByUser(user.get().getUsername());
            profileDto.setUsername(username);
            profileDto.setFullName(student.getFullName());
            profileDto.setEmail(student.getEmail());
            profileDto.setSchool(student.getSchool());
            profileDto.setUniId(student.getUniId());
            profileDto.setUrlGradingFile(student.getUrlGradingFile());
        } else if (authorities.contains(new Authorities("ROLE_TEACHER", user.get()))) {
            Teacher teacher = teacherRepository.findTeacherByUser(user.get());
            profileDto.setUsername(username);
            profileDto.setFullName(teacher.getFullName());
            profileDto.setEmail(teacher.getEmail());
            for (Course course : teacher.getCourses()) {
                CourseDto courseDto = new CourseDto();
                courseDto.setTitle(course.getTitle());
                courseDto.setUniversity(course.getUniversity());
                profileDto.addCourse(courseDto);
            }
            for (Certificate certificate : teacher.getCertificates()) {
                CertificateDto certificateDto = new CertificateDto();
                certificateDto.setTitle(certificate.getTitle());
                certificateDto.setUniversity(certificate.getUniversity());
                profileDto.addCertificate(certificateDto);
            }

        } else {
            // Error!
            return new ProfileDto();
        }

        return profileDto;
    }

    /**
     * Update User's Data Method
     * With this method we are able to update user's information, using these parameters
     * @param profileDto an object, which can contain either student's information or teacher's information
     * @param authentication is an object to take information for the current session
     *                       using the Authentication autowired bean
     * @todo check and update user's data according to arguments, test it with postman (or frontend)
     */
    @PostMapping("/profile")
    public ResponseEntity<?> updateUsersData(@RequestBody ProfileDto profileDto, Authentication authentication){
        ProfileDto profile = getUsersData(authentication);

        if (!profile.equals(profileDto)){
            if (profileDto.getCourses() == null && profileDto.getCertificates() == null){
                // make a new student object according to the given values
                // and update it to the db using the correct repository

            } else {
                // make a new teacher object according to the given values
                // and update it to the db using the correct repository

            }
        }

        return new ResponseEntity<>("Profile Update is under construction! Please wait",
                HttpStatus.OK); // inform user
    }

    @DeleteMapping("/profile")
    public void deleteUserAccount(Authentication authentication){
        Object user = getUsersData(authentication);
        // TODO: delete user's account
    }

}
