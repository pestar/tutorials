package net.starchl.springbootjpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import net.starchl.springbootjpa.domain.Adresse;

public interface AdressenRepository extends JpaRepository<Adresse, Long> {

}
