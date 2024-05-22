package ru.rinzler.library.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String printWelcome(Model model) {

        model.addAttribute("message", "Simple text");
        return "start";

    }

    @GetMapping("/all")
    public String indexPersons(Model model){
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
}
