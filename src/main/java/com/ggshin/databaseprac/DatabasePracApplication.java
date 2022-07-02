package com.ggshin.databaseprac;

import com.ggshin.databaseprac.entity.Person;
import com.ggshin.databaseprac.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatabasePracApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(DatabasePracApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", personJdbcDao.findAll());
		logger.info("id=10001 user -> {}", personJdbcDao.findById(10001));
		logger.info("location=Hyderabad user -> {}", personJdbcDao.findByLocation("Hyderabad"));
		logger.info("name=Pieter user -> {}", personJdbcDao.findByName("Pieter"));
		logger.info("delete user -> {}", personJdbcDao.deleteById(10001));

		logger.info("Inserting 10004 -> {}", personJdbcDao.insert(new Person(10004, "Tara", "BErlin", new Date())));

		logger.info("Update 10003 -> {}", personJdbcDao.update(new Person(10003, "Pieter", "Utrecht", new Date())));

	}
}
