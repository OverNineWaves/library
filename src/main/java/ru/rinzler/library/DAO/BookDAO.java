package ru.rinzler.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinzler.library.Models.Book;
import ru.rinzler.library.Models.Person;

import java.util.ArrayList;
import java.util.List;

import static ru.rinzler.library.Utils.DaoSetAttribute.getBookAtt;
import static ru.rinzler.library.Utils.DaoSetAttribute.getPersonAtt;

@Component
public class BookDAO {

    final private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        List<Book> bookList = new ArrayList<>();
        String sql = "select * from Book";
        bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }

    public void addBook(Book book){
        String sql = "insert into Book (title, author, year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book show(int id){
        List<Book> bookList;
//        Book book = new Book();
        String sql = "select * from Book where id = ?";
        bookList =  jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        return getBookAtt(bookList);
//        for (Book att : bookList){
//            book.setId(att.getId());
//            book.setAuthor(att.getAuthor());
//            book.setTitle(att.getTitle());
//            book.setYear(att.getYear());
//        }
//        return book;
    }

    public void edit(Book book, int id){
        String sql ="update Book set title = ?, author = ?, year = ? where id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id){
        String sql = "delete from Book where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Person showPerson(int id){
//        Person person = new Person();
        String sql = "select Person.* from Book join Person on Book.author_id = Person.id where Book.id = ?";
        List<Person> personList;
        personList = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
        return getPersonAtt(personList);
//        for (Person p : personList){
//            person.setId(p.getId());
//            person.setFirstName(p.getFirstName());
//            person.setSurName(p.getSurName());
//            person.setPatronymic(p.getPatronymic());
//            person.setYear(p.getYear());
//        }
//        return person;
    }

    public void releaseBook(int id){
        String sql = "update Book set author_id = null where author_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
