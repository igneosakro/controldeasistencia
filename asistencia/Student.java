package asistencia;

public class Student {
	private String _dni;
	private String _name;
	private String _lastName;
	private String _mail;
	
	public Student() {
		setDni("");
		setName("");
		setLastName("");
		setMail("");
	}
	
	public Student(String dni, String name, String lastName, String mail) {
		setDni(dni);
		setName(name);
		setLastName(lastName);
		setMail(mail);
	}
	
	public void setDni(String dni) {
		_dni = dni;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setLastName(String lastName) {
		_lastName = lastName;
	}
	
	public void setMail(String mail) {
		_mail = mail;
	}
	
	public String getDni() {
		return _dni;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getLastName() {
		return _lastName;
	}
	
	public String getMail() {
		return _mail;
	}
}
