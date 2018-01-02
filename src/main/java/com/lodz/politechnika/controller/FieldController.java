package com.lodz.politechnika.controller;

import com.lodz.politechnika.model.Field;
import com.lodz.politechnika.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FieldController {

    @Autowired
    private FieldRepository fieldRepository;

    @GetMapping(value = "/addField")
    public String getForm(Model model) {
        model.addAttribute("field", new Field());
        return "fieldFormDef";
    }

    @PostMapping(value="/addField")
    public String postField(@ModelAttribute Field field) {
        fieldRepository.save(field);
        return "successDef";
    }

    @GetMapping(value = "/listFields")
    public String list(Model model) {
        model.addAttribute("fields", fieldRepository.findAll());
        return "fieldListDef";
    }
}
