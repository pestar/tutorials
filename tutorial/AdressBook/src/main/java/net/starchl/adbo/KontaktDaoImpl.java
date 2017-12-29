package net.starchl.adbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import net.starchl.adbo.model.Kontakt;

public class KontaktDaoImpl implements KontaktDao {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	// @Autowired
	private JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

	public KontaktDaoImpl() {
		checkFirstRun();
	}

	private Logger log = Logger.getLogger(this.getClass().getName());

	public boolean insert(Kontakt k) {
		String sql = "insert into kontakte (vorname,nachname,titel,geburtsdatum,strasse,ort,plz,email,telefon) values (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { k.getVorname(), k.getNachname(), k.getTitel(), k.getGeburtsdatum(),
				k.getStrasse(), k.getOrt(), k.getPlz(), k.getEmail(), k.getTelefon() });
		return true;
	}

	public boolean update(Kontakt k) {
		String sql = "update kontakte set vorname=?, nachname=?, titel=?, geburtsdatum=?, strasse=?, ort=?, plz=?, email=?, telefon=? where id=?";
		jdbcTemplate.update(sql, new Object[] { k.getVorname(), k.getNachname(), k.getTitel(), k.getGeburtsdatum(),
				k.getStrasse(), k.getOrt(), k.getPlz(), k.getEmail(), k.getTelefon() });
		return true;
	}

	public boolean delete(int id) {
		String sql = "delete from kontakte where id = ?";
		jdbcTemplate.update(sql, new Integer(id));
		return true;
	}

	public List<Kontakt> list() {
		String sql = "select * from kontakte order by id";
		return jdbcTemplate.query(sql, new KontaktRowMapper());
	}

	private static final class KontaktRowMapper implements RowMapper<Kontakt> {

		public Kontakt mapRow(ResultSet rs, int row) throws SQLException {
			Kontakt k = new Kontakt();
			k.setId(rs.getInt("id"));
			k.setVorname(rs.getString("vorname"));
			k.setNachname(rs.getString("nachname"));
			k.setTitel(rs.getString("titel"));
			k.setEmail(rs.getString("email"));
			k.setGeburtsdatum(rs.getDate("geburtsdatum"));
			k.setOrt(rs.getString("ort"));
			k.setStrasse(rs.getString("strasse"));
			k.setPlz(rs.getShort("plz"));
			k.setTelefon(rs.getString("telefon"));
			return k;
		}

	}

	private void checkFirstRun() {
		try {
			String sql = "select count(*) from kontakte";
			log.info("&&&&&& Anzahl Kontakte in Tabelle: " + jdbcTemplate.queryForObject(sql, Integer.class));
		} catch (Exception e) {
			log.severe(e.getMessage());
			log.info("Call createTable() ...");
			createTable();
		}
	}

	private void createTable() {
		String sql = "create table kontakte (id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),"
				+ "vorname varchar(50)," + "nachname varchar(50)," + "titel varchar(10)," + "email varchar(50),"
				+ "telefon varchar(30)," + "strasse varchar(50)," + "ort varchar(50)," + "geburtsdatum date,"
				+ "plz smallint)";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			log.severe("EEEEE " + e.getMessage());
		}
	}

}
