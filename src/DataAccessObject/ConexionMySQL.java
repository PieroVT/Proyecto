package DataAccessObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;

public class ConexionMySQL {
    
    // Parámetros de conexión a SQL Server
    private final String StrConxSQLServer = "jdbc:sqlserver://localhost:1433;databaseName=ENVIOS;encrypt=true;trustServerCertificate=true;";
    private final String StrUserSQLServer = "sa";
    private final String StrPassSQLServer = "123456789";
    private Connection Conexion;

    // Constructor
    public ConexionMySQL() {
        System.out.println("📡 Iniciando conexión a SQL Server...");
        try {
            // Cargar el driver (opcional en JDBC 4+ pero recomendado)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Intentar la conexión
            Conexion = DriverManager.getConnection(StrConxSQLServer, StrUserSQLServer, StrPassSQLServer);
            
            // Verificar conexión
            if (Conexion != null) {
                DatabaseMetaData dm = Conexion.getMetaData();
                System.out.println("✅ Conectado a: " + dm.getDatabaseProductName());
                System.out.println("📦 Versión de base de datos: " + dm.getDatabaseProductVersion());
                System.out.println("🔌 Driver: " + dm.getDriverName() + " v" + dm.getDriverVersion());
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
    }

    // Main para pruebas
    public static void main(String[] args) {
        ConexionMySQL cn = new ConexionMySQL();
    }

    // Getter para obtener la conexión
    public Connection getConexion() {
        return Conexion;
    }
}
