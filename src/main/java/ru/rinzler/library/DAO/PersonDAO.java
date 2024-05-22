package ru.rinzler.library.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rinzler.library.Models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
     List<Person> personList = new ArrayList<>();
     String sql = "select * Person";
     personList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class));
        return personList;
    }

    public void addPerson(Person person){
        String sql = "insert into Person (firstName, surName, patronymic, year) values (?,?,?,?)";
        jdbcTemplate.update(sql, person.getFirstName(), person.getSurName(), person.getPatronymic(), person.getYear());
    }
}
