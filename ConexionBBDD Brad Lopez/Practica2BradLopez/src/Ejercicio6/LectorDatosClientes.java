package Ejercicio6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Ejercicio1.Conexion;

public class LectorDatosClientes {
	
	public void insertarDatosCliente(String nombreFichero, String nombreTabla, String separadorCampos) {
		try  {
			ArrayList<String> buscar = new ArrayList<String>();
			Conexion conexion=new Conexion();
			String[] split;
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader bfr=new BufferedReader(fr);
			String contenido=bfr.readLine();
			while ((contenido)!=null) {
				buscar.add(contenido);
				contenido=bfr.readLine();
			}
			System.out.println(buscar.size());
			for(int i = 0; i < buscar.size(); i++) {
				split = buscar.get(i).split(separadorCampos);
				conexion.conectar();
				String consulta = "INSERT INTO "+nombreTabla+" (NOMBRE, APELLIDOS, EDAD, CORREO) VALUES (?,?,?,?)"; // Consulta
																															// a
																															// realizar
				PreparedStatement statement = null; // Preparar la query

					statement = conexion.getConexion().prepareStatement(consulta);
					statement.setString(1, split[1]);
					statement.setString(2, split[2]);
					statement.setInt(3, Integer.parseInt(split[3]));
					statement.setString(4, split[4]);			
					statement.execute();
					
						
				conexion.desconectar();
				JOptionPane.showMessageDialog(null, "Datos del fichero insertado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error");
		} 
		
	}
}

