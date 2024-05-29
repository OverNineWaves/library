package ru.rinzler.library.Utils;

import ru.rinzler.library.Models.Book;
import ru.rinzler.library.Models.Person;

import java.util.List;

public class DaoSetAttribute {

    public static Person getPersonAtt(List<Person> person){
        Person newPerson = new Person();
        for (Person p : person){
            newPerson.setId(p.getId());
            newPerson.setFirstName(p.getFirstName());
            newPerson.setSurName(p.getSurName());
            newPerson.setPatronymic(p.getPatronymic());
            newPerson.setYear(p.getYear());
        }
        return newPerson;
    }

    public static Book getBookAtt(List<Book> book){
        Book newBook = new Book();
        for (Book b : book){
            newBook.setId(b.getId());
            newBook.setAuthor(b.getAuthor());
            newBook.setTitle(b.getTitle());
            newBook.setYear(b.getYear());
        }
        return newBook;
    }
}
