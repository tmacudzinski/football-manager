package com.lodz.politechnika.controller;

import com.lodz.politechnika.model.Skill;
import com.lodz.politechnika.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping(value = "/addSkill")
    public String getForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skillFormDef";
    }

    @PostMapping(value = "/addSkill")
    public String postSkill(@ModelAttribute Skill skill) {
        skillRepository.save(skill);
        return "successDef";
    }

    @GetMapping(value = "/listSkills")
    public String list(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skillListDef";
    }
}
