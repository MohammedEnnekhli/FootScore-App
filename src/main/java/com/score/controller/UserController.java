package com.score.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.score.entities.User;
import com.score.repositories.RoleRepository;
import com.score.repositories.UserRepository;

@Controller
@RequestMapping( value = "/user" )
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping( value = "/login" )

    public String Login() {
        return "login";

    }

    @GetMapping( value = "/403" )
    public String accessDenied() {
        return "403";

    }

    @GetMapping
    public String indexPage() {

        return "redirect:/";

    }

    @GetMapping( value = "/signUp" )
    public String signUp( Model model ) {

        model.addAttribute( "listRoles", roleRepository.findAll() );
        return "signUp";

    }

    @PostMapping( value = "/signUp" )
    public String signUpPost( @Valid User user ) {

        user = new User( user.getLogin(), new BCryptPasswordEncoder().encode( user.getPassword() ), 1, user.getRole() );
        user.getRole().get( 0 ).getUser().add( user );
        /*
         * Role role = user.getRole().get( 0 );
         * 
         * System.out.println( "Role is " + role.getRole() ); if (
         * role.getRole() == "ADMIN" ) {
         * 
         * user.getRole().add( new Role( "USER" ) ); } System.out.println(
         * "Role 1 : " + user.getRole().get( 0 ).getRole() + " Role 2: " +
         * user.getRole().get( 1 ).getRole() );
         */
        userRepository.save( user );
        return "redirect:/";

    }

    @GetMapping( value = "/logout" )
    public String logout( HttpSession session ) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
