/**
 * 
 */
package Ejercicio3;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


/**
 * @author Brad Lopez
 *
 */
public class Main2 {
	static Conexion2 conexion = new Conexion2();
	static String F_fin=""; 
	static String F_inicio="";
	static String consulta="";
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			menu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Vaya parece q has llegado muy lejos, esperemos hay cumplido su objetivo");
			menu();
		}
	}
	private static void menu(){
		// TODO Auto-generated method stub
		Object[] opciones = { "Anadir Empleado", "Anadir Proyectos","Asignar Proyectos"};
		int operacion = JOptionPane.showOptionDialog(null, "Elige una operacion", "Practica Banco", 0,
				JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		switch (operacion) {
		case 0:// Anadir
			AnadirEmpleado();
			break;
		case 1:// Añadir
			AnadirProyecto();
			break;
		case 2://Asignar 
			Asignarproyecto();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Operacion terminada \n Gracias por confiar en nosotros");
		}
	}
	/**
	 * Anadir clientes a la base de datos
	 */
	private static void AnadirEmpleado(){
		try {// Asignacion de variables a la peticion en Swing
			String dni = dni();
			String nombre = JOptionPane.showInputDialog("Introduzca Nombre");
			conexion.conectar();
			String consulta = "INSERT INTO EMPLEADOS (DNI, NOMBRE) VALUES (?,?)"; // Consulta
			
																														// realizar
			PreparedStatement statement = null; // Preparar la query

				statement = conexion.getConexion().prepareStatement(consulta);
				statement.setString(1, dni);
				statement.setString(2, nombre);		
				statement.execute();
				
					
			conexion.desconectar();
			menu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al introducir los datos ");
			menu();
		
		}
	
	}
	/**
	 * Anadir clientes a la base de datos
	 */
	private static void AnadirProyecto(){
		try {// Asignacion de variables a la peticion en Swing
			String nombre = JOptionPane.showInputDialog("Introduzca Nombre");
			String dni = dni();
			F_inicio=JOptionPane.showInputDialog("Introduzca Fecha Inicio del proyecto \n Introducir con este formato='anio-mes-dia'");
			F_fin =JOptionPane.showInputDialog("Introduzca Fecha Fin del proyecto \n Introducir con este formato='anio-mes-dia'");
			if(F_inicio.isEmpty()) {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO) VALUES (?,?,curdate())";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, nombre);
					statement.setString(2, dni);	
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}else {
					conexion.conectar();
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO,F_FIN) VALUES (?,?,curdate(),?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}
			}else {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO) VALUES (?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_inicio);	
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}else {
					conexion.conectar();
					 consulta = "INSERT INTO PROYECTOS (NOMBRE,DNI_EMP,F_INICIO,F_FIN) VALUES (?,?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, nombre);
					statement.setString(2, dni);
					statement.setString(3, F_inicio);
					statement.setString(4, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al introducir los datos ");
			menu();
		
		}
	
	}
	
	/**
	 * Asigna proyectos a empleados
	 */
	private static void Asignarproyecto() {
		try {// Asignacion de variables a la peticion en Swing
			String dni = dni();
			String num = JOptionPane.showInputDialog("Introduzca Numero del proyecto");
			F_inicio=JOptionPane.showInputDialog("Introduzca Fecha Inicio del proyecto \n Introducir con este formato='anio-mes-dia'");
			F_fin =JOptionPane.showInputDialog("Introduzca Fecha Fin del proyecto \n Introducir con este formato='anio-mes-dia'");
			if(F_inicio.isEmpty()) {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					 consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO) VALUES (?,?,curdate())";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni);
					statement.setString(2,num );	
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}else {
					conexion.conectar();
					 consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES (?,?,curdate(),?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni);
					statement.setString(2, num);
					statement.setString(3, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}
			}else {
				if(F_fin.isEmpty()) {
					conexion.conectar();
					consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO) VALUES (?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni);
					statement.setString(2, num);
					statement.setString(3, F_inicio);	
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}else {
					conexion.conectar();
					consulta = "INSERT INTO ASIG_PROYECTOS (DNI_EMP,NUM_PROY,F_INICIO,F_FIN) VALUES (?,?,?,?)";
						// realizar
					PreparedStatement statement = null; // Preparar la query
					
					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, dni);
					statement.setString(2, num);
					statement.setString(3, F_inicio);
					statement.setString(4, F_fin);		
					statement.execute();
					
					
					conexion.desconectar();
					menu();
				}
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al introducir los datos ");
			menu();
		
		}
	
	
	} 
	
	private static String dni() {
		try {
			String dni=JOptionPane.showInputDialog("Intorduzca el DNI");
	        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
	        Matcher mat = patron.matcher(dni);
	        while(!mat.matches()){
	           dni=JOptionPane.showInputDialog("Error Intorduzca un DNI correcto");
	           mat = patron.matcher(dni);
	        }
	        return dni;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Operacion Terminada");
		}
		return null;
    }
}
