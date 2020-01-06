package com.score.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.score.entities.Club;
import com.score.entities.Matche;
import com.score.repositories.ClubRepository;
import com.score.repositories.LeagueRepository;
import com.score.repositories.StadiumRepository;

@Controller
@RequestMapping( value = "/club" )
public class ClubController {

    @Autowired
    LeagueRepository  leagueRepository;
    @Autowired
    StadiumRepository stadiumRepository;
    @Autowired
    ClubRepository    clubRepository;

    @GetMapping( value = "/addClub" )
    public String clubForm( Model model ) {

        model.addAttribute( "leaguesList", leagueRepository.findAll() );
        model.addAttribute( "stadiumsList", stadiumRepository.findAll() );
        return "/addClub";
    }

    @PostMapping( value = "/addClub" )
    public String addClub( @Valid Club club, BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() )
            return "addClub";
        clubRepository.save( club );
        return "redirect:/club/listClub";

    }

    @GetMapping( value = "/listClub" )
    public String displayClub( Model model, @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s ) {

        Page<Club> pageClubs = clubRepository.findAll( new PageRequest( p, s ) );
        model.addAttribute( "listClub", pageClubs.getContent() );
        int[] pages = new int[pageClubs.getTotalPages()];
        model.addAttribute( "pages", pages );
        model.addAttribute( "currentPage", p );
        model.addAttribute( "size", s );
        return "listClub";

    }

    @GetMapping( value = "/delete" )
    public String delete( Long id ) {

        clubRepository.deleteById( id );

        return "redirect:/club/listClub";

    }

    @GetMapping( value = "/edit" )
    public String edit( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        model.addAttribute( "listLeague", leagueRepository.findAll() );
        model.addAttribute( "listStadium", stadiumRepository.findAll() );
        model.addAttribute( "club", updatedClub );
        return "editClub";
    }

    @GetMapping( value = "/playedHomeMatches" )
    public String playedHomeMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> HomeMatches = updatedClub.getHome_matchs();
        model.addAttribute( "playedHomeMatches", HomeMatches );
        return "playedHomeMatches";

    }

    @GetMapping( value = "/winHomeMatches" )
    public String winHomeMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        List<Matche> winHomeMatches = new ArrayList<Matche>();
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() > matche.getGoals_away_team() ) {
                winHomeMatches.add( matche );
            }
        }
        model.addAttribute( "winHomeMatches", winHomeMatches );
        return "winHomeMatches";

    }

    @GetMapping( value = "/lostHomeMatches" )
    public String lostHomeMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> homeMatches = updatedClub.getHome_matchs();

        List<Matche> lostHomeMatches = new ArrayList<Matche>();
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() < matche.getGoals_away_team() ) {
                lostHomeMatches.add( matche );
            }
        }
        model.addAttribute( "lostHomeMatches", lostHomeMatches );
        return "lostHomeMatches";

    }

    @GetMapping( value = "/drawnHomeMatches" )
    public String drawnHomeMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        List<Matche> drawnHomeMatches = new ArrayList<Matche>();
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() == matche.getGoals_away_team() ) {
                drawnHomeMatches.add( matche );
            }
        }
        model.addAttribute( "drawnHomeMatches", drawnHomeMatches );
        return "drawnHomeMatches";

    }

    @GetMapping( value = "/playedAwayMatches" )
    public String playedAwayMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> AwayMatches = updatedClub.getAway_matchs();
        model.addAttribute( "playedAwayMatches", AwayMatches );
        return "playedAwayMatches";

    }

    @GetMapping( value = "/winAwayMatches" )
    public String winAwayMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> winAwayMatches = new ArrayList<Matche>();
        for ( Matche matche : awayMatches ) {
            if ( matche.getGoals_home_team() < matche.getGoals_away_team() ) {
                winAwayMatches.add( matche );
            }
        }
        model.addAttribute( "winAwayMatches", winAwayMatches );

        return "winAwayMatches";

    }

    @GetMapping( value = "/lostAwayMatches" )
    public String lostAwayMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> lostAwayMatches = new ArrayList<Matche>();
        for ( Matche matche : awayMatches ) {
            if ( matche.getGoals_home_team() > matche.getGoals_away_team() ) {
                lostAwayMatches.add( matche );
            }
        }
        model.addAttribute( "lostAwayMatches", lostAwayMatches );

        return "lostAwayMatches";

    }

    @GetMapping( value = "/drawnAwayMatches" )
    public String drawnAwayMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> drawnAwayMatches = new ArrayList<Matche>();
        for ( Matche matche : awayMatches ) {
            if ( matche.getGoals_home_team() == matche.getGoals_away_team() ) {
                drawnAwayMatches.add( matche );
            }
        }
        model.addAttribute( "drawnAwayMatches", drawnAwayMatches );

        return "drawnAwayMatches";

    }

    @GetMapping( value = "/playedMatches" )
    public String playedMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        homeMatches.addAll( awayMatches );
        model.addAttribute( "playedMatches", homeMatches );
        return "playedMatches";

    }

    @GetMapping( value = "/winMatches" )
    public String winMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        List<Matche> winMatches = new ArrayList<Matche>();
        for ( Matche matche : awayMatches ) {
            if ( matche.getGoals_home_team() < matche.getGoals_away_team() ) {
                winMatches.add( matche );
            }
        }
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() > matche.getGoals_away_team() ) {
                winMatches.add( matche );
            }
        }

        model.addAttribute( "winMatches", winMatches );
        return "winMatches";

    }

    @GetMapping( value = "/lostMatches" )
    public String lostMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        List<Matche> lostMatches = new ArrayList<Matche>();
        for ( Matche matche : awayMatches ) {
            if ( matche.getGoals_home_team() > matche.getGoals_away_team() ) {
                lostMatches.add( matche );
            }
        }
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() < matche.getGoals_away_team() ) {
                lostMatches.add( matche );
            }
        }

        model.addAttribute( "lostMatches", lostMatches );
        return "lostMatches";

    }

    @GetMapping( value = "/drawnMatches" )
    public String drawnMatches( Model model, Long id ) {

        Club updatedClub = clubRepository.findById( id ).orElse( null );
        List<Matche> awayMatches = updatedClub.getAway_matchs();
        List<Matche> homeMatches = updatedClub.getHome_matchs();
        homeMatches.addAll( awayMatches );
        List<Matche> drawnMatches = new ArrayList<Matche>();
        for ( Matche matche : homeMatches ) {
            if ( matche.getGoals_home_team() == matche.getGoals_away_team() ) {
                drawnMatches.add( matche );
            }
        }

        model.addAttribute( "drawnMatches", drawnMatches );
        return "drawnMatches";

    }
}
