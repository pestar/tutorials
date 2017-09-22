package net.starchl.springbootjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.starchl.springbootjpa.domain.Person;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;
	@Autowired
	private PersonQueryRepository qRepository;

	@Override
	public List<Person> findAll() {
		return repository.findAll();
	}

	@Override
	public Person findById(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Person> findByName(String name) {
		return qRepository.findByName(name);
	}

	@Override
	public void savePerson(Person p) {
		repository.saveAndFlush(p);

	}

	@Override
	public void deletePerson(long id) {
		repository.delete(id);

	}

}
