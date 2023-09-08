/**
 * 
 */
package Ejercicio4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import Ejercicio3.Conexion2;

/**
 * @author Brad Lopez
 *
 */
public class Asig_Proyecto {
	private String dni_emp;
	private String num_proy;
	private static String F_inicio;
	private static String F_fin;
	/**
	 * @param dni_emp
	 * @param num_proy
	 * @param f_inicio
	 * @param f_fin
	 */
	public Asig_Proyecto() {

	}
	/**
	 * @return the dni_emp
	 */
	public String getDni_emp() {
		return dni_emp;
	}
	/**
	 * @param dni_emp the dni_emp to set
	 */
	public void setDni_emp(String dni_emp) {
		this.dni_emp = dni_emp;
	}
	/**
	 * @return the num_proy
	 */
	public String getNum_proy() {
		return num_proy;
	}
	/**
	 * @param num_proy the num_proy to set
	 */
	public void setNum_proy(String num_proy) {
		this.num_proy = num_proy;
	}
	/**
	 * @return the f_inicio
	 */
	public String getF_inicio() {
		return F_inicio;
	}
	/**
	 * @param f_inicio the f_inicio to set
	 */
	public void setF_inicio(String f_inicio) {
		F_inicio = f_inicio;
	}
	/**
	 * @return the f_fin
	 */
	public String getF_fin() {
		return F_fin;
	}
	/**
	 * @param f_fin the f_fin to set
	 */
	public void setF_fin(String f_fin) {
		F_fin = f_fin;
	}
	@Override
	public String toString() {
		return "Asig_Proyecto [dni_emp=" + dni_emp + ", num_proy=" + num_proy + ", F_inicio=" + F_inicio + ", F_fin="
				+ F_fin + "]";
	}
	
	public void Asignarproyecto() {
		try {// Asignacion de variables a la peticion en Swing
			Conexion2 conexion = new Conexion2();
			String consulta="";
			if(F_inicio.isEmpty()) {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					 consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO) VALUES (?,?,curdate())";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni_emp);
					statement.setString(2,num_proy );	
					statement.execute();
					
					
					conexion.desconectar();
				}else {
					conexion.conectar();
					 consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES (?,?,curdate(),?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni_emp);
					statement.setString(2, num_proy);
					statement.setString(3, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
				}
			}else {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO) VALUES (?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni_emp);
					statement.setString(2, num_proy);
					statement.setString(3, F_inicio);	
					statement.execute();
					
					
					conexion.desconectar();
				}else {
					conexion.conectar();
					consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES (?,?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni_emp);
					statement.setString(2, num_proy);
					statement.setString(3, F_inicio);
					statement.setString(4, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
				}
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al introducir los datos"+e.getMessage());
		
		}
	
	
	} 
	
	
	public void Lista_Emp() {
		Object[] proyectos = new Object[0];
		ArrayList<Empleados> empleado = new ArrayList<Empleados>();
		Conexion2 conexion = new Conexion2();
		conexion.conectar();

		// Consulta
		String consulta = "select * from proyectos";
		// Praparar la query
		PreparedStatement statement = null;
		// Preparar resultado de la query
		ResultSet result = null;

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); // Ejecuto la query y recibo los resultados

			while (result.next()) {
				proyectos = Arrays.copyOf(proyectos, proyectos.length + 1); // Clonar array aumentando una posicion
				proyectos[proyectos.length - 1] = result.getString("cod")+ "\n"; // Introduce  en la ultima posicion del array																			
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		}

			Object eleccion=JOptionPane.showInputDialog(null, "Elige el proyecto", "Practica",
				JOptionPane.PLAIN_MESSAGE, null, proyectos, proyectos[0]);
			
			// Consulta
			String subconsulta = "select *from empleados where dni =any(select dni_emp from asig_proyectos where num_proy=(select cod from proyectos where cod="+eleccion+"))";
			// Praparar la query
			PreparedStatement state = null;
			// Preparar resultado de la query
			ResultSet result1 = null;

			try {
				state = conexion.getConexion().prepareStatement(subconsulta);
				result1 = state.executeQuery(); // Ejecuto la query y recibo los resultados

				while (result1.next()) {
					String dni=result1.getString("DNI");
					String nombre=result1.getString("NOMBRE");
					empleado.add(new Empleados(dni,nombre));
				}
				conexion.desconectar();
				System.out.println(empleado.get(0));
				String info = "Empleados asignados a: "+eleccion+" \n";
				for(int i=0;i<empleado.size();i++) {
					info += "Nombre= "+empleado.get(i).getNombre()+ " , con dni= " + empleado.get(i).getDni()+"\n";
				}
				JOptionPane.showMessageDialog(null, info);
			} catch (SQLException e) {
				System.out.println("ERROR EN LA QUERY");
			}

		
	}
}
