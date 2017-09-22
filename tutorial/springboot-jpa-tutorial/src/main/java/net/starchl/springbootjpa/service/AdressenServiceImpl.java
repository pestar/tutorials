package net.starchl.springbootjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.starchl.springbootjpa.domain.Adresse;

@Service
@Transactional
public class AdressenServiceImpl implements AdressenService {

	@Autowired
	private AdressenRepository repository;
	@Autowired
	private AdressenQueryRepository qRepository;

	@Override
	public List<Adresse> findAll() {
		return repository.findAll();
	}

	@Override
	public Adresse findById(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Adresse> findByOrt(String ort) {
		return qRepository.findByOrt(ort);
	}

	@Override
	public void saveAdresse(Adresse a) {
		repository.saveAndFlush(a);
	}

	@Override
	public void deleteAdresse(long id) {
		repository.delete(id);
	}

}
