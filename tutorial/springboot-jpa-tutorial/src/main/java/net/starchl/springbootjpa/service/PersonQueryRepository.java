package net.starchl.springbootjpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import net.starchl.springbootjpa.domain.Person;

public interface PersonQueryRepository extends Repository<Person, Long> {
	@Query(value = "select * from #{#entityName} b where b.nachname=?1", nativeQuery = true)
	List<Person> findByName(String name);
}
