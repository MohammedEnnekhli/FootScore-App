package com.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.score.repositories.LeagueRepository;

@Controller
public class IndexController {

    @Autowired
    LeagueRepository leagueRepository;

    @GetMapping( value = "/" )
    public String infosList( Model model ) {

        model.addAttribute( "leagueList", leagueRepository.findAll() );
        return "index";

    }

}
