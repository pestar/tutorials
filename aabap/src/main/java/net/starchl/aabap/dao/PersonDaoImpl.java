package net.starchl.aabap.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.starchl.aabap.model.Person;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

	protected Logger log = Logger.getLogger(this.getClass().getName());
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAllPersons() {
		return (List<Person>) getSession().createQuery("from Person").list();
	}

	public Person findPersonById(int id) {
		String queryString = "from Person where id = :id";
		@SuppressWarnings("unchecked")
		Query<Person> query = getSession().createQuery(queryString);
		query.setParameter("id", id);
		Person p = null;
		p = query.getSingleResult();
		return p;
	}

	public void insertPerson(Person p) {
		getSession().persist(p);
	}

	public void updatePerson(Person p) {
		// Transaction tx = getSession().beginTransaction();
		getSession().update(p);
		// tx.commit();
	}

	public void insertOrUpdatePserson(Person p) {
		// Person p1 = findPersonById(p.getId());
		String query = "select count(id) from Person where id=" + p.getId();
		long anzahl = (Long) getSession().createQuery(query).uniqueResult();
		if (anzahl == 0) {
			// if (p1 == null)
			insertPerson(p);
			log.info("&&&&&&&& insert Person ID=" + p.getId());
		} else {
			updatePerson(p);
			log.info("&&&&&&&& update Person ID=" + p.getId());
		}
	}

	public void deletePerson(Person p) {
		// Transaction tx = getSession().beginTransaction();
		getSession().delete(p);
		// tx.commit();
	}

}
