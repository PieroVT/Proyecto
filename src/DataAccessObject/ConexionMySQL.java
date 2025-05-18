package DataAccessObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;

public class ConexionMySQL {
    //mysql://root:CCLwuwLtOAGMcOoPATxKGYOJILKngkZu@hopper.proxy.rlwy.net:55324/railway
    private String StrConxMySQL ="jdbc:mysql://localhost:3306/ENVIOS";
    private String StrUserMySQL ="root";
    private String StrPassMySQL ="";
    private Connection Conexion;
    //Constructor
    public ConexionMySQL() {
        System.out.println("Me estas llamando :)");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.setLoginTimeout(300);
            Conexion = DriverManager
                    .getConnection(StrConxMySQL,StrUserMySQL,StrPassMySQL);
            if(Conexion != null){
                DatabaseMetaData dm = Conexion.getMetaData();
                System.out.println("Product Name:" + dm.getDatabaseProductName());
                System.out.println("Product Version:" 
                        + dm.getDatabaseProductVersion());
                System.out.println("Driver Name:" + dm.getDriverName());
                System.out.println("Driver Version:" + dm.getDriverVersion());
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
        
    public static void main(String [] args){
        ConexionMySQL cn = new ConexionMySQL();
    }
    //Encapsulamiento
    public Connection getConexion() {
        return Conexion;
    }
}
