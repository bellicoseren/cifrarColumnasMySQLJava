package bellicose.com.usuario_cifrado.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory instance = new ConnectionFactory();
	private String url;
	private String usr;
	private String pass;
	private String driver;
	private Connection conn;
	
	private ConnectionFactory() {
		
		url = "jdbc:mysql://localhost:3306/db_cifrada?useSSL=false";
		usr = "root";
		pass = "root";
		driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConexion() {
		try {
			conn = DriverManager.getConnection(url, usr, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static ConnectionFactory getInstance() {
		return instance;
	}
}
