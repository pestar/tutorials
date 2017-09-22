package net.starchl.springbootjpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import net.starchl.springbootjpa.domain.Adresse;

public interface AdressenQueryRepository extends Repository<Adresse, Long> {

    @Query(value = "select * from #{#entityName} b where b.ort=?1", nativeQuery = true)
    List<Adresse> findByOrt(String ort);
    
}
