package com.voting.voting.controllers;

import com.voting.voting.config.ViewNames;
import com.voting.voting.entity.Object;
import com.voting.voting.repositories.ObjectRepository;
import com.voting.voting.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/objects/")
public class ObjectController {

    private ObjectService objectService;

    private ObjectRepository objectRepository;


    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }



    @GetMapping("list")
    public String getObject(Model model) {
        model.addAttribute("objects", objectService.findAllAndSort());
        return "index";
    }



    @RequestMapping(value = "add", method={RequestMethod.POST, RequestMethod.GET})
    public String addObject(@Valid Object object, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-object";
        }
        objectService.saveObject(object);
        return "index";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Object object = objectService.findObjectById(id);
        model.addAttribute("object", object);
        return "update-object";
    }




   @PostMapping("update/{id}")
    public String updateObject(@PathVariable("id") long id, @Valid Object object, BindingResult result,
                                Model model) {
        Object updatedObject = objectService.update(id);
        Object savedObject = objectService.saveObject(updatedObject);
        model.addAttribute("objects", savedObject);
        return "index";
    }

/*    @PostMapping("/update/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("update-object");
        Object updatedObject = objectService.update(id);
        mav.addObject("updatedObject", updatedObject);
        return mav;
    }*/



/*    @PostMapping("/addObject")
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
    }*/
}
