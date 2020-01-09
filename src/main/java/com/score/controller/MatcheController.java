package com.score.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.score.Services.FootService;
import com.score.entities.Club;
import com.score.entities.League;
import com.score.entities.Matche;
import com.score.repositories.ClubRepository;
import com.score.repositories.LeagueRepository;
import com.score.repositories.MatcheRepository;
import com.score.repositories.StadiumRepository;

@Controller
@RequestMapping( value = "/matche" )
public class MatcheController {

    @Autowired
    MatcheRepository  matcheRepository;
    @Autowired
    LeagueRepository  leagueRepository;
    @Autowired
    StadiumRepository stadiumRepository;
    @Autowired
    ClubRepository    clubRepository;
    @Autowired
    FootService       footService;

    @GetMapping( value = "/addMatche" )
    public String matcheForm( Model model ) {

        model.addAttribute( "stadiumList", stadiumRepository.findAll() );
        model.addAttribute( "leagueList", leagueRepository.findAll() );
        model.addAttribute( "clubList", clubRepository.findAll() );

        return "addMatche";
    }

    @PostMapping( value = "/addMatche" )
    public String addMatche( @Valid Matche matche, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return "addMatche";

        }
        footService.goalsClub( matche );

       matche.getHome_team().getMatchs().add( matche );
        matche.getAway_team().getMatchs().add( matche );
      matche.setStadium( matche.getHome_team().getStadium() );
        System.out.println("matche est modifié: "+ matche.getGoals_home_team());
        matcheRepository.save( matche );
        System.out.println("matche est modifié: "+ matche.getGoals_away_team());

        return "redirect:/matche/listMatche";
    }

    @GetMapping( value = "/listMatche" )
    public String displayMatche( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Matche> pageMatches = matcheRepository.findAll( new PageRequest( p, s ) );
        model.addAttribute( "listMatche", pageMatches.getContent() );
        int[] pages = new int[pageMatches.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listMatche";

    }



    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        matcheRepository.deleteById( id );

        return "redirect:/matche/listMatche";

    }
}
