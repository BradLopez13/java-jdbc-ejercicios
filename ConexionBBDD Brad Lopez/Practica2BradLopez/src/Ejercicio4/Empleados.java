/**
 * 
 */
package Ejercicio4;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import Ejercicio3.Conexion2;

/**
 * @author Brad Lopez
 *
 */
public class Empleados {
	private static String dni;
	private static String nombre;
	
	public Empleados() {
	}

	/**
	 */
	public Empleados(String dni,String nombre) {
		this.dni=dni;
		this.nombre=nombre;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the nommbre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nommbre the nommbre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleados [dni=" + dni + ", nommbre=" + nombre + "]";
	}
	public void AnadirEmpleado(){
		try {// Asignacion de variables a la peticion en Swing
			Conexion2 conexion = new Conexion2();
			conexion.conectar();
			String consulta = "INSERT INTO EMPLEADOS (DNI, NOMBRE) VALUES (?,?)"; // Consulta
			
																														// realizar
			PreparedStatement statement = null; // Preparar la query

				statement = conexion.getConexion().prepareStatement(consulta);
				statement.setString(1, dni);
				statement.setString(2, nombre);		
				statement.execute();
				
					
			conexion.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al introducir los datos ");
		
		}
	
	}
	
}
