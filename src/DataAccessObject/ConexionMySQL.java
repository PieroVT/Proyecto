package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ENVIOS;encrypt=true;trustServerCertificate=true;";
    private final String USER = "sa";
    private final String PASS = "123456789";
    private Connection conexion;

    public ConexionMySQL() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado a SQL Server");
        } catch (SQLException e) {
            System.out.println(" Error en conexión: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
