package com.ggshin.databaseprac.jdbc;

import com.ggshin.databaseprac.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //select * from person
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE id=?", new BeanPropertyRowMapper<>(Person.class)
                ,id);
    }
    public Person findByLocation(String location) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE location=?",
                new BeanPropertyRowMapper<>(Person.class),
                location);

    }

    public Person findByName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE name=?",
                new BeanPropertyRowMapper<>(Person.class),
                name
        );
    }

    public int deleteById(int id) {
        return jdbcTemplate.update(
                "DELETE FROM person WHERE id=?", id);
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) values(?, ?, ?, ?)",
                person.getId(),
                person.getName(),
                person.getLocation(),
                new Timestamp(person.getBirthDate().getTime()));
    }

    public int update(Person person) {
        return jdbcTemplate.update(
                "update person set name = ?, location=?, birth_date=? where id =?",
                person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId());

    }

}
