package DataAccessObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;

public class ConexionMySQL {
    
   
    private final String StrConxSQLServer = "jdbc:sqlserver://localhost:1433;databaseName=ENVIOS;encrypt=true;trustServerCertificate=true;";
    private final String StrUserSQLServer = "sa";
    private final String StrPassSQLServer = "123456789";
    private Connection Conexion;

  
    public ConexionMySQL() {
       
        try {
        
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
     
            Conexion = DriverManager.getConnection(StrConxSQLServer, StrUserSQLServer, StrPassSQLServer);
            
   
          
        } catch (ClassNotFoundException | SQLException e) {
            
        }
    }


    public static void main(String[] args) {
        ConexionMySQL cn = new ConexionMySQL();
    }
    public Connection getConexion() {
        return Conexion;
    }
}
