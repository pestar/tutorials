package net.starchl.aabap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.starchl.aabap.dao.PersonDao;
import net.starchl.aabap.model.Person;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	public List<Person> findAllPersons() {
		return personDao.findAllPersons();
	}

	public Person findPersonById(int id) {
		return personDao.findPersonById(id);
	}

	public void insertPerson(Person p) {
		personDao.insertPerson(p);
	}

	public void updatePerson(Person p) {
		personDao.updatePerson(p);
	}

	public void insertOrUpdatePserson(Person p) {
		personDao.insertOrUpdatePserson(p);
	}

	public void deletePerson(Person p) {
		personDao.deletePerson(p);
	}

}
