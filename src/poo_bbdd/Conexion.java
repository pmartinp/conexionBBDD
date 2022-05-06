package poo_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	//driver JDBC
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	//direcci�n de la BD MySQL
	private static final String BBDD = "jdbc:mysql://localhost:3306/categorias_productos";
	//ususario y contrase�a de acceso a la BD
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";
	
	public Conexion() {}
	public Connection conexionBBDD() {
		Connection conec = null;
		//Controlamos las excepciones que aparecen al interactuar con la BBDD
		try {
			Class.forName(DRIVER);
			//Crear una conexi�n a la Base de Datos
			conec = DriverManager.getConnection(BBDD, USUARIO, PASSWORD);
		} catch (Exception errores) {
			//Control de errores de la conexi�n a la BBDD
			System.err.println("Se ha producido un error al conectar con la Base de Datos.\n" + errores);
		}
		return conec;
	}
	
	public void cerrarConexion(Connection conection) {
		try {
			//Cierre de conexi�n
			conection.close();
		} catch (SQLException e) {
			//Controlamos las excepciones que se puedan producir al cierre de la conexi�n
			System.err.println("Se ha producido un error al conectar con la Base de Datos." + e);
		}
	}
	
}
