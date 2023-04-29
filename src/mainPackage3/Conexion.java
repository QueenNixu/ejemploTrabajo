package mainPackage3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {
	//variables necesarias para hacer la conexion
	private static  String ip = "190.60.174.223";
	private static  int port = 50128;
	private static  String instanceName = "RPSISTEMAS";
	private static  String dbName = "factu_full_central_desa";
	
	private static  String dbUrl = 
			"jdbc:sqlserver://"+ip+":"+port
			+";instanceName="+instanceName
			+";database="+dbName
			+";TrustServerCertificate=True";
	//variable que es la conexion
	static Connection con;
	//funcion que hace la conexion
	public static Connection getConection(String dbUser, String dbPassword) {
		try {
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
		catch(SQLException ex){
				@SuppressWarnings("unused")
				Alerta conf = new Alerta("Error al conectarse a la base de datos: \n \""+ex.toString()+"\"");
				con = null;
			}
		return con;
	}
	//funcion que devuelve la coneccion hecha
	public static Connection getCon() {
		return con;
	}
}