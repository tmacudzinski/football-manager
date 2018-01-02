package com.lodz.politechnika.controller;

import com.lodz.politechnika.binder.TeamBinder;
import com.lodz.politechnika.model.FootballPlayer;
import com.lodz.politechnika.model.Skill;
import com.lodz.politechnika.model.Team;
import com.lodz.politechnika.repository.FootballPlayerRepository;
import com.lodz.politechnika.repository.SkillRepository;
import com.lodz.politechnika.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FootballPlayerController {

    @Autowired
    private FootballPlayerRepository playerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamBinder teamBinder;

    @GetMapping(value = "/addPlayer")
    public String getForm(Model model) {
        model.addAttribute("player", new FootballPlayer());
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "playerFormDef";
    }

    @PostMapping(value = "/addPlayer")
    public String postPlayer(@ModelAttribute FootballPlayer player) {
        playerRepository.save(player);
        return "successDef";
    }

    @GetMapping(value = "/listPlayers")
    public String list(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "playerListDef";
    }

    @GetMapping(value = "/player/{playerId}")
    public String getTeam(Model model, @PathVariable String playerId) {
        Long id = Long.parseLong(playerId);
        FootballPlayer player = playerRepository.findOne(id);
        model.addAttribute("player", player);
        List<Skill> freeSkills = skillRepository.findAll();
        freeSkills.removeAll(player.getSkills());
        model.addAttribute("freeSkills", freeSkills);
        return "playerDef";
    }

    /**
     * AJAX method
     */
    @GetMapping(value = "/player/{playerId}/deleteSkill/{skillId}")
    public @ResponseBody void deleteSkill(Model model, @PathVariable String playerId, @PathVariable String skillId) {
        Long.parseLong(playerId);
        FootballPlayer player = playerRepository.findOne(Long.parseLong(playerId));
        Skill skill = player.getSkills()
                .stream()
                .filter(s -> s.getSkillId().equals(Long.parseLong(skillId)))
                .findFirst().get();
        player.getSkills().remove(skill);
        playerRepository.save(player);
    }

    /**
     * AJAX method
     */
    @PostMapping(value = "/player/{playerId}/addSkill/{skillId}")
    public @ResponseBody void addSkill(Model model, @PathVariable String playerId, @PathVariable String skillId) {
        Long.parseLong(playerId);
        FootballPlayer player = playerRepository.findOne(Long.parseLong(playerId));
        Skill skill = skillRepository.findOne(Long.parseLong(skillId));
        player.getSkills().add(skill);
        playerRepository.save(player);
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "skills", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = Long.parseLong(String.valueOf(element));
                Skill skill = skillRepository.findOne(id);
                return skill;
            }
        });
        binder.registerCustomEditor(Team.class, "team", teamBinder);
    }
}
