package co.ceiba.parking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	public User(Long id) {
		this.id = id;
	}
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "apellido")
    private String apellido;
	
	@Column(name = "documento", nullable = false, unique = true)
    private String documento;
	
	@Column(name = "telefono")
    private String telefono;
	
	@Column(name = "direccion")
    private String direccion;
	
	@Column(name = "mail", nullable = false, unique = true)
    private String mail;
	
	@Column(name = "clave")
    private String clave;
	
	private transient String confirmPassword;
	
	public User(String nombre, String apellido, String documento, String telefono, String direccion, String mail, String clave) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento=documento;
		this.telefono=telefono;
		this.direccion=direccion;
		this.mail=mail;
		this.clave=clave;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	// GETTERS
	
	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getClave() {
		return clave;
	}
	
	//SETTERS
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getConfirmPassword() {
        return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
	        this.confirmPassword = confirmPassword;
	}
	

}
