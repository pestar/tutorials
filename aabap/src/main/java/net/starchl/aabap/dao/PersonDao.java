package net.starchl.aabap.dao;

import java.util.List;

import net.starchl.aabap.model.Person;

public interface PersonDao {

	public List<Person> findAllPersons();

	public Person findPersonById(int id);

	public void insertPerson(Person p);

	public void updatePerson(Person p);

	public void insertOrUpdatePserson(Person p);

	public void deletePerson(Person p);
}
