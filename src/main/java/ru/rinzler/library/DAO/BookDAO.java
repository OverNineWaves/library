package ru.rinzler.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinzler.library.Models.Book;

import java.util.ArrayList;
import java.util.List;

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
}
