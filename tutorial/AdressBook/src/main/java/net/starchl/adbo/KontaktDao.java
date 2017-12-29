package net.starchl.adbo;

import java.util.List;

import net.starchl.adbo.model.Kontakt;

public interface KontaktDao {
	public boolean insert(Kontakt kontakt);

	public boolean update(Kontakt kontakt);

	public boolean delete(int id);

	public List<Kontakt> list();
}
