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

import com.score.entities.Stadium;
import com.score.repositories.StadiumRepository;

@Controller
@RequestMapping( value = "/stadium" )
public class StadiumController {

    @Autowired
    StadiumRepository stadiumRepository;

    @PostMapping( value = "/addStadium" )
    public String addStadium( @Valid Stadium stadium, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {

            return "addStadium";
        }

        stadiumRepository.save( stadium );

        return "redirect:/stadium/listStadium";
    }

    @GetMapping( value = "/addStadium" )
    public String stadiumForm() {

        return "addStadium";
    }

    @GetMapping
    public String indexPage() {

        return "index";

    }

    @GetMapping( value = "/listStadium" )
    public String displayStadium( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Stadium> pageStadiums = stadiumRepository.findAll( new PageRequest( p, s ) );
        model.addAttribute( "listStadium", pageStadiums.getContent() );
        int[] pages = new int[pageStadiums.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listStadium";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        stadiumRepository.deleteById( id );

        return "redirect:/stadium/listStadium";

    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        Stadium updatedStadium = stadiumRepository.findById( id ).orElse( null );
        model.addAttribute( "stadium", updatedStadium );
        return "editStadium";
    }

}
