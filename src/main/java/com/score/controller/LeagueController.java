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

import com.score.entities.League;
import com.score.repositories.ContinentRepository;
import com.score.repositories.LeagueRepository;

@Controller
@RequestMapping( value = "/league" )
public class LeagueController {

    @Autowired
    private LeagueRepository    leagueRepository;
    @Autowired
    private ContinentRepository continentRepository;

    @GetMapping( value = "/addleague" )
    public String leagueForm( Model model ) {

        model.addAttribute( "listContinent", continentRepository.findAll() );
        return "addleague";

    }

    @PostMapping( value = "/addLeague" )
    public String addLeague( @Valid League league, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {

            return "addLeague";
        }

        leagueRepository.save( league );

        return "redirect:/league/listLeague";

    }

    @GetMapping
    public String indexPage() {

        return "index";

    }

    @GetMapping( value = "/listLeague" )
    public String displayLeague( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<League> pageLeagues = leagueRepository.findAll( new PageRequest( p, s ) );

        model.addAttribute( "listLeagues", pageLeagues.getContent() );
        int[] pages = new int[pageLeagues.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listLeague";
    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        leagueRepository.deleteById( id );

        return "redirect:/league/listLeague";
    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        // Optional<League> league = leagueRepository.findById( id );
        League updatedLeague = leagueRepository.findById( id ).orElse( null );
        // League updatedLeague = league.get();
        model.addAttribute( "listContinent", continentRepository.findAll() );
        model.addAttribute( "league", updatedLeague );
        return "editLeague";
    }

    @GetMapping( value = "/infos" )

    public String infos( Long id, Model model ) {
        League targetLeague = leagueRepository.findById( id ).orElse( null );

        model.addAttribute( "league", targetLeague );

        return "leagueInfos";
    }

}
