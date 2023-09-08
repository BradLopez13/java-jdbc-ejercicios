/**
 * 
 */
package Ejercicio4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Ejercicio3.Conexion2;

/**
 * @author Brad Lopez
 *
 */
public class Main3 {
	static Conexion2 conexion = new Conexion2();
	/**
	 * @param args
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
	public static  void menu(){
		// TODO Auto-generated method stub
		Object[] opciones = { "Anadir Empleado", "Anadir Proyectos","Asignar Proyectos","Empleados Asignados"};
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
		case 3:
			List_Emp();
		default:
			JOptionPane.showMessageDialog(null, "Operacion terminada \n Gracias por confiar en nosotros");
		}
	}
	
	private static void AnadirEmpleado() {
		Empleados empleado=new Empleados();
		empleado.setDni(dni());
		empleado.setNombre(JOptionPane.showInputDialog("Introduzca Nombre"));
		empleado.AnadirEmpleado();
		menu();
		
	}
	
	private static void AnadirProyecto() {
		Proyectos proyecto=new Proyectos();
		proyecto.setNombre(JOptionPane.showInputDialog("Introduzca Nombre \n Asegurese de no duplicar el nombre de otro proyecto"));
		proyecto.setDni(dni());
		proyecto.setF_Inicio(JOptionPane.showInputDialog("Introduzca Fecha Inicio del proyecto \n Introducir con este formato='anio-mes-dia'"));
		proyecto.setF_Fin(JOptionPane.showInputDialog("Introduzca Fecha Fin del proyecto \n Introducir con este formato='anio-mes-dia'"));
		proyecto.AnadirProyecto();
		menu();
	}
	
	private static void Asignarproyecto() {
		Asig_Proyecto asignar =new Asig_Proyecto();
		asignar.setDni_emp(dni());
		asignar.setNum_proy(JOptionPane.showInputDialog("Introduzca Numero del proyecto"));
		asignar.setF_inicio(JOptionPane.showInputDialog("Introduzca Fecha Inicio del proyecto \n Introducir con este formato='anio-mes-dia'"));
		asignar.setF_fin(JOptionPane.showInputDialog("Introduzca Fecha Fin del proyecto \n Introducir con este formato='anio-mes-dia'"));
		asignar.Asignarproyecto();
		menu();
	}
	
	private static void List_Emp() {
		Asig_Proyecto asignar =new Asig_Proyecto();
		asignar.Lista_Emp();
		menu();
	}
	
	public static String dni() {
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
