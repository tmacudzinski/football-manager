package com.lodz.politechnika.controller;

import com.lodz.politechnika.binder.FieldBinder;
import com.lodz.politechnika.model.Field;
import com.lodz.politechnika.model.FootballPlayer;
import com.lodz.politechnika.model.Team;
import com.lodz.politechnika.repository.FieldRepository;
import com.lodz.politechnika.repository.FootballPlayerRepository;
import com.lodz.politechnika.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FieldBinder fieldBinder;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FootballPlayerRepository playerRepository;

    @GetMapping(value = "/addTeam")
    public String getTeam(Model model) {
        model.addAttribute("fields", fieldRepository.findAll());
        model.addAttribute("team", new Team());
        return "teamFormDef";
    }

    @PostMapping(value = "/addTeam")
    public String postTeam(@ModelAttribute Team team) {
        teamRepository.save(team);
        return "successDef";
    }

    @GetMapping(value = "/listTeams")
    public String list(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teamListDef";
    }

    @GetMapping(value = "/team/{teamId}")
    public String getTeam(Model model, @PathVariable String teamId) {
        Long id = Long.parseLong(teamId);
        model.addAttribute("team",teamRepository.findOne(id));
        List<FootballPlayer> freePlayers = playerRepository.findAll()
                .stream()
                .filter(p -> p.getTeam() == null)
                .collect(Collectors.toList());
        model.addAttribute("freePlayers", freePlayers);
        return "teamDef";
    }

    /**
     * AJAX method
     */
    @GetMapping(value = "/team/{teamId}/deletePlayer/{playerId}")
    public @ResponseBody void deletePlayer(Model model, @PathVariable String teamId, @PathVariable String playerId) {
        Long.parseLong(teamId);
        Team team = teamRepository.findOne(Long.parseLong(teamId));
        FootballPlayer player = team.getPlayers()
                .stream()
                .filter(p -> p.getPlayerId().equals(Long.parseLong(playerId)))
                .findFirst().get();
        player.setTeam(null);
        playerRepository.save(player);
    }

    /**
     * AJAX method
     */
    @PostMapping(value = "/team/{teamId}/addPlayer/{playerId}")
    public @ResponseBody void addPlayer(Model model, @PathVariable String teamId, @PathVariable String playerId) {
        Long.parseLong(teamId);
        Team team = teamRepository.findOne(Long.parseLong(teamId));
        FootballPlayer player = playerRepository.findOne(Long.parseLong(playerId));
        player.setTeam(team);
        playerRepository.save(player);
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Field.class, "field", fieldBinder);
    }
}
