package com.voting.voting.controllers;

import com.voting.voting.config.ViewNames;
import com.voting.voting.entity.Object;
import com.voting.voting.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ObjectController {

    private ObjectService objectService;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping("/objects")
    public String getObject(Model model) {
        model.addAttribute("objectList", objectService.findAllAndSort());
        return ViewNames.BOOKS;
    }

    @GetMapping("/objects/add")
    public String addObject(Model model) {
        model.addAttribute("newObject", new Object());
        return "addObject";
    }

    @PostMapping("/setName")
    public String saveObject(@ModelAttribute("newObject") Object object, Model model) {
        objectService.saveObject(object);
        return getObject(model);
    }

    @PutMapping("/setVote")
    public String putObject(@RequestBody Object object, @PathVariable Long id, Model model) {
       // model.addAttribute("object.votes", object.getVotes());
        objectService.editObject(object,id);
        return getObject(model);
    }
}
