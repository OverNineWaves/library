package ru.rinzler.library.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rinzler.library.DAO.PersonDAO;
import ru.rinzler.library.Models.Person;

@Controller
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    public LibraryController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    PersonDAO personDAO;

//    @GetMapping
//    public String printWelcome(Model model) {
//
//        model.addAttribute("message", "Simple text");
//        return "start";
//
//    }

    @GetMapping("")
    public String peopleIndex(Model model){
        model.addAttribute("index", personDAO.index());
        return "person/index";
    }

    @GetMapping("/addPerson")
    public String addPersonPage(Model model){
        model.addAttribute("person", new Person());
        return "person/add";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute ("person") Person person){
        personDAO.addPerson(person);
        return "redirect:/library";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable ("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "person/show";
    }

    @GetMapping("/edit/{id}")
    public String editPersonPage(@PathVariable ("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "person/edit";
    }

    @PatchMapping("/edit/{id}")
    public String editPerson(@ModelAttribute ("person") Person person, @PathVariable ("id") int id){
        personDAO.edit(person, id);
        return "redirect:/library";
    }
    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/library";
    }
}
