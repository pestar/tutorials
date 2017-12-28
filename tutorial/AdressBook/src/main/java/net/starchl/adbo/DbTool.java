package net.starchl.adbo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.starchl.adbo.model.Kontakt;

public class DbTool {

	protected static Logger log = Logger.getLogger("net.starchl.adbo.DbTool");
	private Connection con;
	private String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
	private String url = "jdbc:derby:/Users/peter/testdata/derby/adbodb;create=true";
	private boolean isFirstRun = true;

	public DbTool() {
		if (isFirstRun)
			checkFirstRun();
	}

	private Connection getCon() {
		String user = "app";
		String password = "app";

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			log.severe("PROBLEM: " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			log.severe("PROBLEM: " + e.getMessage());
		}

		return con;
	}

	private void checkFirstRun() {
		getCon();
		try {
			Statement stmtCheck = con.createStatement();
			ResultSet rsCheck = stmtCheck.executeQuery("select count(*) from kontakte");
			if (rsCheck != null && rsCheck.next()) {
				log.fine("Anzahl der Kontakte=" + rsCheck.getInt(1));
				isFirstRun = false;
			}
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
			log.info("SQL State: " + e.getSQLState());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Erzeuge Tabelle");
			createTable(con);
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				log.severe("PROBLEM: " + e.getMessage());
			}
		}
	}

	private void createTable(Connection cc) {
		String sql = "create table kontakte (id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),"
				+ "vorname varchar(50)," + "nachname varchar(50)," + "titel varchar(10)," + "email varchar(50),"
				+ "telefon varchar(30)," + "strasse varchar(50)," + "ort varchar(50)," + "geburtsdatum date,"
				+ "plz smallint)";
		try {
			Statement stmt = cc.createStatement();
			stmt.execute(sql);
			log.info("Tabelle KONTAKTE erzeugt");
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
		}
	}

	public boolean insert(Kontakt kontakt) {
		getCon();
		boolean isOk = false;
		String sqlP = "insert into kontakte (vorname,nachname,titel,geburtsdatum,strasse,ort,plz,email,telefon) values (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlP);
			pstmt.setString(1, kontakt.getVorname());
			pstmt.setString(2, kontakt.getNachname());
			pstmt.setString(3, kontakt.getTitel());
			pstmt.setDate(4, new java.sql.Date(kontakt.getGeburtsdatum().getTime()));
			pstmt.setString(5, kontakt.getStrasse());
			pstmt.setString(6, kontakt.getOrt());
			pstmt.setShort(7, kontakt.getPlz());
			pstmt.setString(8, kontakt.getEmail());
			pstmt.setString(9, kontakt.getTelefon());
			pstmt.executeUpdate();
			isOk = true;
			log.info("Insert OK");
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.severe("PROBLEM: " + e.getMessage());
			}
		}
		return isOk;
	}

	public boolean update(Kontakt kontakt) {
		getCon();
		boolean isOk = false;
		String sqlP="update kontakte set vorname=?, nachname=?, titel=?, geburtsdatum=?, strasse=?, ort=?, plz=?, email=?, telefon=? where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlP);
			pstmt.setString(1, kontakt.getVorname());
			pstmt.setString(2, kontakt.getNachname());
			pstmt.setString(3, kontakt.getTitel());
			pstmt.setDate(4, new java.sql.Date(kontakt.getGeburtsdatum().getTime()));
			pstmt.setString(5, kontakt.getStrasse());
			pstmt.setString(6, kontakt.getOrt());
			pstmt.setShort(7, kontakt.getPlz());
			pstmt.setString(8, kontakt.getEmail());
			pstmt.setString(9, kontakt.getTelefon());
			pstmt.setInt(10, kontakt.getId());
			pstmt.executeUpdate();
			isOk = true;
			log.info("Update OK");
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.severe("PROBLEM: " + e.getMessage());
			}
		}
		return isOk;
	}

	public boolean delete(int id) {
		boolean isOk = false;
		getCon();
		String sql = "delete from kontakte where id=" + id;
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			isOk = true;
			log.info("Delete OK");
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.severe("PROBLEM: " + e.getMessage());
			}
		}
		return isOk;
	}

	public List<Kontakt> list() {
		List<Kontakt> liste = new ArrayList<Kontakt>();
		getCon();
		String sql = "select * from kontakte order by id";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Kontakt k = new Kontakt();
			while (rs.next()) {
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
				liste.add(k);
				k = new Kontakt();
			}
		} catch (SQLException e) {
			log.severe("PROBLEM: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.severe("PROBLEM: " + e.getMessage());
			}
		}
		return liste;
	}

}
