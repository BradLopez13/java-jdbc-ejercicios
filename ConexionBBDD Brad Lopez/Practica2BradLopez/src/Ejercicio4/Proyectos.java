/**
 * 
 */
package Ejercicio4;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Ejercicio3.Conexion2;

/**
 * @author Brad Lopez
 *
 */
public class Proyectos {
	private static int codP;
	private static String nombre;
	private static String dni;
	private static String F_Inicio;
	private static String F_Fin;
	/**
	 * @param codP
	 * @param nombre
	 * @param dni
	 * @param f_Inicio
	 * @param f_Fin
	 */
	public Proyectos() {
	}
	/**
	 * @return the codP
	 */
	public int getCodP() {
		return codP;
	}
	/**
	 * @param codP the codP to set
	 */
	public void setCodP(int codP) {
		this.codP = codP;
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
	 * @return the f_Inicio
	 */
	public String getF_Inicio() {
		return F_Inicio;
	}
	/**
	 * @param f_Inicio the f_Inicio to set
	 */
	public void setF_Inicio(String f_Inicio) {
		F_Inicio = f_Inicio;
	}
	/**
	 * @return the f_Fin
	 */
	public String getF_Fin() {
		return F_Fin;
	}
	/**
	 * @param f_Fin the f_Fin to set
	 */
	public void setF_Fin(String f_Fin) {
		F_Fin = f_Fin;
	}
	@Override
	public String toString() {
		return "Proyectos [codP=" + codP + ", nombre=" + nombre + ", dni=" + dni + ", F_Inicio=" + F_Inicio + ", F_Fin="
				+ F_Fin + "]";
	}
	public  void AnadirProyecto(){
		try {// Asignacion de variables a la peticion en Swing
			Conexion2 conexion = new Conexion2();
			String consulta="";
			PreparedStatement statement = null; // Preparar la query
			conexion.conectar();
			if(F_Inicio.isEmpty()) {
				if(F_Fin.isEmpty()) {
					
					consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO) VALUES (?,?,curdate())";
						// realizar
					
					statement = conexion.getConexion().prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, nombre);
					statement.setString(2, dni);	
					statement.execute();
					
					
				}else {
					consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO,F_FIN) VALUES (?,?,curdate(),?)";
						// realizar
					
					statement = conexion.getConexion().prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_Fin);		
					statement.execute();
					
					
				}
			}else {
				if(F_Fin.isEmpty()) {
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO) VALUES (?,?,?)";
						// realizar
					
					statement = conexion.getConexion().prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_Inicio);	
					statement.execute();
					
					
				}else {
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO,F_FIN) VALUES (?,?,?,?)";
						// realizar
					
					statement = conexion.getConexion().prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_Inicio);
					statement.setString(4, F_Fin);		
					statement.execute();
					
					
					
				}
			}
			ResultSet generatedKeys =  statement.getGeneratedKeys();
			if (generatedKeys.next()) {
			        codP= generatedKeys.getInt(1);
			}
			String insert = "select * from PROYECTOS where COD ="+codP;
			// Praparar la query
			PreparedStatement state = null; // Preparar la query
			// Preparar resultado de la query
			ResultSet result = null;
				state = conexion.getConexion().prepareStatement(insert);
				result = state.executeQuery(); // Ejecuto la query y recibo los resultados

				while (result.next()) {
					JOptionPane.showMessageDialog(null,"Numero del proyecto: "+result.getInt("cod"));
				}
			conexion.desconectar();
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al introducir los datos");
		
		}
	
	}
	
}
