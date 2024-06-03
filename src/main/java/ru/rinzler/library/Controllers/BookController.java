package ru.rinzler.library.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rinzler.library.DAO.BookDAO;
import ru.rinzler.library.DAO.PersonDAO;
import ru.rinzler.library.Models.Book;
import ru.rinzler.library.Models.Person;

@Controller()
@RequestMapping("/books")
public class BookController {

    BookDAO bookDAO;
    PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO)
    {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String bookIndex(Model model){
        model.addAttribute("index",bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable ("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("bookPerson", bookDAO.showPerson(id));
        return "book/show";
    }

    @GetMapping("/add")
    public String addBookPage(Model model){
        model.addAttribute("book", new Book());
        return "/book/add";
    }

    @PostMapping("/add")
    public String addBook (@ModelAttribute("book") Book book){
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }


    @PatchMapping("/edit/{id}")
    public String editBook(@ModelAttribute ("book") Book book, @PathVariable ("id") int id){
        bookDAO.edit(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable ("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook (@PathVariable ("id") int id){
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/select")
    public String giveBookTo(@PathVariable ("id") int id, @ModelAttribute ("person") Person people){
        bookDAO.giveTo(id, people);
        return "redirect:/books/" + id;
    }

}
