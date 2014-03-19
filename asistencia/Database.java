package asistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	Connection connection = null;
	
	public Database() {
		String databaseName = "database.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+databaseName);
			connection.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (connection != null)
			init();
	}
	
	public void init() {
		update("CREATE TABLE IF NOT EXISTS student" +
				"(dni			VARCHAR		PRIMARY KEY		NOT NULL," +
				" name			VARCHAR						NOT NULL," +
				" last_name		VARCHAR						NOT NULL," +
				" mail			VARCHAR						NOT NULL);");
		update("CREATE TABLE IF NOT EXISTS subject" +
				"(name			VARCHAR		PRIMARY KEY		NOT NULL," +
				" password		VARCHAR						NOT NULL);");
		update("CREATE TABLE IF NOT EXISTS register" +
				"(id			INTEGER		PRIMARY KEY		AUTOINCREMENT," +
				" subject		VARCHAR						NOT NULL," +
				" student		VARCHAR						NOT NULL," +
				" date			DATE						NOT NULL);");
		update("CREATE TABLE IF NOT EXISTS session" +
				"(id			INTEGER		PRIMARY KEY		AUTOINCREMENT," +
				" subject		VARCHAR						NOT NULL," +
				" date			DATE						NOT NULL);");
		update("CREATE TABLE IF NOT EXISTS attendance" +
				"(id			INTEGER		PRIMARY KEY		AUTOINCREMENT," +
				" session		VARCHAR						NOT NULL," +
				" student		VARCHAR						NOT NULL);");
		//update("INSERT INTO subject (name, password) VALUES ('Redes', 'ITISREDES2014');");
		//update("INSERT INTO student (dni, name, last_name, mail) VALUES('13123','asdasd','asdasdas','asdasdas@asdasd.com')");
		//update("INSERT INTO register (subject, student, date) VALUES('Redes','13123','12/03/2014')");
	}
	
	public ResultSet query(String queryText) {
		Statement s = null;
		ResultSet result = null;
		try {
			s = connection.createStatement();
			result = s.executeQuery (queryText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public void update(String queryText) {
		Statement s = null;
		try {
			s = connection.createStatement();
			s.executeUpdate (queryText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
