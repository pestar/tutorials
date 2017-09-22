package net.starchl.springbootjpa.service;

import java.util.List;

import net.starchl.springbootjpa.domain.Person;

public interface PersonService {
    public List<Person> findAll();

    public Person findById(long id);

    public List<Person> findByName(String name);

    public void savePerson(Person p);

    public void deletePerson(long id);
}
