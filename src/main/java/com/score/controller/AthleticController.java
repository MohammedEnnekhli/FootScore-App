package com.score.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.score.entities.Athletic;
import com.score.repositories.AthleticRepository;
import com.score.repositories.ClubRepository;

@Controller
@RequestMapping( value = "/athletic" )
public class AthleticController {

    @Autowired
    ClubRepository     clubRepository;
    @Autowired
    AthleticRepository athleticRepository;

    @GetMapping( value = "/addAthletic" )
    public String athleticForm( Model model ) {

        model.addAttribute( "clubList", clubRepository.findAll() );
        return "addAthletic";
    }

    @PostMapping( value = "/addAthletic" )
    public String addAthletic( @Valid Athletic athletic, BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {
            return "addAthletic";

        }
        athleticRepository.save( athletic );
        return "redirect:/athletic/listAthletic";
    }

    @GetMapping( value = "/listAthletic" )
    public String displayAthletic( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Athletic> pageAthletics = athleticRepository.findAll( new PageRequest( p, s ) );
        model.addAttribute( "listAthletic", pageAthletics.getContent() );
        int[] pages = new int[pageAthletics.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listAthletic";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        athleticRepository.deleteById( id );

        return "redirect:/athletic/listAthletic";

    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        Athletic updatedAthletic = athleticRepository.findById( id ).orElse( null );
        model.addAttribute( "listClub", clubRepository.findAll() );
        model.addAttribute( "athletic", updatedAthletic );
        return "editAthletic";
    }
}
