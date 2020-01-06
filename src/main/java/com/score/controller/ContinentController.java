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

import com.score.entities.Continent;
import com.score.repositories.ContinentRepository;

@Controller
@RequestMapping( value = "/continent" )
public class ContinentController {

    @Autowired
    private ContinentRepository continentRepository;

    @PostMapping( value = "/addContinent" )
    public String addContinent( @Valid Continent continent, BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {

            return "addContinent";
        }

        continentRepository.save( continent );

        return "redirect:/continent/listContinent";

    }

    @GetMapping( value = "/addContinent" )
    public String continentForm() {

        return "addContinent";

    }

    @GetMapping( value = "/listContinent" )
    public String displayContinent( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Continent> pageContinents = continentRepository.findAll( new PageRequest( p, s ) );
        model.addAttribute( "listContinent", pageContinents.getContent() );
        int[] pages = new int[pageContinents.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listContinent";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        continentRepository.deleteById( id );

        return "redirect:/continent/listContinent";

    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        Continent updatedContinent = continentRepository.findById( id ).orElse( null );
        model.addAttribute( "continent", updatedContinent );
        return "editContinent";
    }

}
