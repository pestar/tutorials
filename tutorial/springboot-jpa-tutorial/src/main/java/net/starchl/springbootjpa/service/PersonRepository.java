package net.starchl.springbootjpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import net.starchl.springbootjpa.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
