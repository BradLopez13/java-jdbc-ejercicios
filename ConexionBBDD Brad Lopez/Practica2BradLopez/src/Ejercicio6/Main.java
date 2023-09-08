/**
 * 
 */
package Ejercicio6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Brad
 *
 */
public class Main {
	static String fichero="fichero.txt";
    static File f=new File(fichero);
    static BufferedReader bfr;
	static BufferedWriter bfw;
	static FileReader fr;
	static int cod=0;
	static ArrayList<String>campos=new ArrayList<String>();
	static String buscDat;
	
	static int cont;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if(f.exists()) {	
				menu();
			}else {
				crearFichero();
			}
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	    		    }
	}
	/**
	 * Crea el fichero en el que se va a trabajar
	 */
	private static void crearFichero() {
		try{
			f.createNewFile();
			JOptionPane.showMessageDialog(null, fichero+" generado");
			menu();
			
	    }
	    catch(IOException e) {
			JOptionPane.showMessageDialog(null, "vaya algo salio mal");
	    }
	    
	   
		
	}
	
	/**
	 * Genera el menu donde se contemplan las opciones de trabajo en el fichero 
	 */
	private static void menu() {
		Object[] opciones = { "Anadir","InseratrBBDD"};
		int operacion = JOptionPane.showOptionDialog(null, "Elige una operacion", "Ficheros", 0,
				JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		switch (operacion) {
		case 0:// Añadir
			anadirFichero(); 
			break;
		case 1:
			insertarBBDD();
		default:
			JOptionPane.showMessageDialog(null, "Operacion terminada");
		}
	}
	private static void insertarBBDD() {
		LectorDatosClientes lector=new LectorDatosClientes();
		lector.insertarDatosCliente(fichero, "clientes" ,";");
		menu();
	}
	/**
	 * Metodo que añade datos al fichero
	 */
	private static void anadirFichero()  {
		 try {
		    	String nombre=JOptionPane.showInputDialog("Introduzca el nombre");
		    	String apellidos=JOptionPane.showInputDialog("Introduzca apellidos");
		    	String edad=JOptionPane.showInputDialog("Introduzca la edad");
		    	int edadV=0;
		    	if(isNumeric(edad)) {
		    		edadV=Integer.parseInt(edad);
		    	}else {
					JOptionPane.showInputDialog("ha habido un error, introduzca un numero por favor");
		    	}
		    	String correo=JOptionPane.showInputDialog("Introduzca el correo");
		    	bfw=new BufferedWriter(new FileWriter(f, true));
		    	bfw.write(cod()+";"+nombre+";"+apellidos+";"+edad+";"+correo);
		    	bfw.newLine();
		    	bfw.close();
		    	menu();
		    }
		    catch(IOException e) {
				e.printStackTrace();
		    }
			
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
	 /**
		 * Metodo que cuenta el numero de lineas existentes en el fichero
		 * @return
		 */
	 public static long cod() {
			try {
				fr = new FileReader("fichero.txt");
				bfr=new BufferedReader(fr);
				long lNumeroLineas = 1;
				while ((fichero = bfr.readLine())!=null) {
				  lNumeroLineas++;
				}
				return lNumeroLineas;
			}catch (FileNotFoundException fnfe){
				  fnfe.printStackTrace();
			} catch (IOException ioe){
			  ioe.printStackTrace();
			}
			return  (Long) null;
		}
		
	
}
