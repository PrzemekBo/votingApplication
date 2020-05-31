package com.voting.voting.controllers;

import com.voting.voting.config.ViewNames;
import com.voting.voting.entity.Object;
import com.voting.voting.repositories.ObjectRepository;
import com.voting.voting.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ObjectController {

    private ObjectService objectService;

    private ObjectRepository objectRepository;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping("/objects")
    public String getObject(Model model) {
        model.addAttribute("objectList", objectService.findAllAndSort());
        return ViewNames.OBJECTS;
    }

    @GetMapping("/addObject")
    public String addObject(Model model) {
        model.addAttribute("newObject", new Object());
        return ViewNames.ADDOBJECT;
    }

    @PostMapping("/addObject")
    public String addObject(@ModelAttribute("newObject") Object object, Model model) {
        Object singleObject = objectService.saveObject(object);
        model.addAttribute("singleObject", singleObject);
        return ViewNames.OBJECT;
    }

    @PostMapping("/setVote/{id}")
    public ResponseEntity<Void> putObject(Object object, @PathVariable int id) {
        objectService.editObject(object,id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        Object singleObject = objectService.update(id);
        model.addAttribute("singleObject", singleObject);
        return ViewNames.OBJECT;
    }
}
