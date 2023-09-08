/**
 * 
 */
package Ejercicio1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Brad Lopez
 *
 */
public class Main {
	static Conexion conexion = new Conexion();
	private static ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	static String info="";
	static String operacion="";
	static int num=0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			menu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Vaya parece q has llegado muy lejos, esperemos hay cumplido su objetivo");
		}	
		
	
	}

	private static void menu() {
		// TODO Auto-generated method stub
		Object[] opciones = { "Anadir Clientes", "Mostrar Clientes"};
		int operacion = JOptionPane.showOptionDialog(null, "Elige una operacion", "Practica Banco", 0,
				JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		switch (operacion) {
		case 0:// Anadir
			AnadirClientes();
			break;
		case 1:// Mostrar
			MostrarClientes();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Operacion terminada \n Gracias por confiar en nosotros");
		}
	}

	/**
	 * Anadir clientes a la base de datos
	 */
	private static void AnadirClientes() {
		// TODO Auto-generated method stub
		try {// Asignacion de variables a la peticion en Swing
			String nombre = JOptionPane.showInputDialog("Introduzca Nombre");
			String apell = JOptionPane.showInputDialog("Introduzca Apellidos");
			String edad = JOptionPane.showInputDialog("Introduzca Edad");
			int edadV=0;
			if(isNumeric(edad)) {
	             edadV=Integer.parseInt(edad);

			}else {
				JOptionPane.showInputDialog("ha habido un error, introduzca un numero por favor");
			}
			String correo = JOptionPane.showInputDialog("Introduzca Email");
			conexion.conectar();
			String consulta = "INSERT INTO CLIENTES (NOMBRE, APELLIDOS, EDAD, CORREO) VALUES (?,?,?,?)"; // Consulta
																														// a
																														// realizar
			PreparedStatement statement = null; // Preparar la query

				statement = conexion.getConexion().prepareStatement(consulta);
				statement.setString(1, nombre);
				statement.setString(2, apell);
				statement.setInt(3, edadV);
				statement.setString(4, correo);			
				statement.execute();
				
					
			conexion.desconectar();
			menu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al introducir los datos ");
			menu();
		
		}
	
	}
	/**
	 * Mostrar Clientes a la base de datos
	 * @param EXIT_ON_CLOSE 
	 */
	private static void MostrarClientes() {
		cargarClientes();
		info(num);
			
	}
	private static void info(int num) {
		info="fila "+cliente.get(num).getCodC()+"= Nombre: "+cliente.get(num).getNombre()+", Apellidos: "+cliente.get(num).getApellidos()
				+ ", Edad: "+cliente.get(num).getEdad()+", Correo: "+cliente.get(num).getCorreo();
		operacion=JOptionPane.showInputDialog(info+"\n Filas disponibles:"+cliente.size()+"\n Introduzca:\n ´.´= para mostrar todo y cerrar\n ´k´=para ir a la siguiente fila\n"
			+ "´d´=para ir a la fila anterior\n O introduzca el numero de la fila que desea ver");
		operacionesBD();
	}
	private static void operacionesBD() {
		if(operacion.equals(".")) {
			for(int i=0;i<cliente.size();i++) {
				info +="fila "+cliente.get(i).getCodC()+"= Nombre: "+cliente.get(i).getNombre()+", Apellidos: "+cliente.get(i).getApellidos()
						+ ", Edad: "+cliente.get(i).getEdad()+", Correo: "+cliente.get(i).getCorreo()+"\n";
			}
			JOptionPane.showMessageDialog(null, info);
		}else if(operacion.equals("k")) {
			num++;
			info(num);	
				
		}else if(operacion.equals("d")) {
			num--;
			info(num);	
		}else if(isNumeric(operacion)) {
			int op=Integer.parseInt(operacion);
			op--;
			if(op>cliente.size()|| op <0) {
				JOptionPane.showMessageDialog(null, "fila no existente");
				menu();
			}else {
				info(op);
			}
		}
	}
	/*SELECCIONAR LOS CLIENTES DE LA BASE DE DATOS*/
	private static void cargarClientes() {
		conexion.conectar();

		// Consulta
		String consulta = "select * from clientes";
		// Praparar la query
		PreparedStatement statement = null;
		// Preparar resultado de la query
		ResultSet result = null;
		cliente.clear();

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); // Ejecuto la query y recibo los resultados

			while (result.next()) {
				int cod=result.getInt("cod");
				String nombre=result.getString("nombre");
				String apell=result.getString("apellidos");
				int edad=result.getInt("edad");
				String correo=result.getString("CORREO");
				cliente.add(new Cliente(cod,nombre,apell,edad,correo));
			}
		} catch (SQLException e) {
			System.out.println("NO SE HAN ENCONTRADO CLIENTES O NO EXISTEN \n VUELVA A INTENTARLO=)");
		}

		conexion.desconectar();
	}
	/**
	 * Validacion campos es numero
	 * @param cadena
	 * @return
	 */
	public static boolean isNumeric(String cadena) {

	        boolean resultado;

	        try {
	            Integer.parseInt(cadena);
	            resultado = true;
	        } catch (NumberFormatException excepcion) {
	            resultado = false;
	        }

	        return resultado;
	    }
	
	
}


