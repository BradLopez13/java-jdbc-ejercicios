/**
 * 
 */
package Ejercicio1;

/**
 * @author Brad
 *
 */
public class Cliente {
	private int codC;
	private String nombre;
	private String apellidos;
	private int edad;
	private String correo;
	/**
	 * @param codC
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param correo
	 */
	public Cliente(int codC, String nombre, String apellidos, int edad, String correo) {
		this.codC = codC;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.correo = correo;
	}
	/**
	 * @return the codC
	 */
	public int getCodC() {
		return codC;
	}
	/**
	 * @param codC the codC to set
	 */
	public void setCodC(int codC) {
		this.codC = codC;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	@Override
	public String toString() {
		return "Cliente " + codC + "[ nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
				+ ", correo=" + correo + "]";
	}
	
	
}