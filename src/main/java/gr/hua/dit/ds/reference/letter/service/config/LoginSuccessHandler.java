package gr.hua.dit.ds.reference.letter.service.config;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        String username = authentication.getName();
        User userDetails = userRepository.findByUsername(username).get();
         
        String redirectURL = request.getContextPath();
         
        if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "admin/";
        } else if (userDetails.hasRole("ROLE_STUDENT")) {
            redirectURL = "students/";
        } else if (userDetails.hasRole("ROLE_TEACHER")) {
            redirectURL = "teachers/";
        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}