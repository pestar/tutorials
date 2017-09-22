package net.starchl.springbootjpa.service;

import java.util.List;

import net.starchl.springbootjpa.domain.Adresse;

public interface AdressenService {
    public List<Adresse> findAll();

    public Adresse findById(long id);

    public List<Adresse> findByOrt(String ort);

    public void saveAdresse(Adresse a);

    public void deleteAdresse(long id);
}
