
package DataAccessObject;
import BusinessEntity.Vendedor;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

        
public class VendedorDAO extends ConexionMySQL implements IBaseDAO<Vendedor>
{
    public static void main(String[] args){
        System.out.println("USANDO LA CLASE VENDEDOR DAO");
        VendedorDAO myDAO = new VendedorDAO();
        
        // Ejemplo de lectura de un vendedor
        Vendedor myVendedor = myDAO.Read("VND001");
        if(myVendedor != null && myVendedor.getVnd_codigo() != null) {
            System.out.println("Código: " + myVendedor.getVnd_codigo());
            System.out.println("Nombres: " + myVendedor.getVnd_nombres());
            System.out.println("Estado: " + myVendedor.getVnd_estcod());
        }
    }

    @Override
    public boolean Create(Vendedor input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO vendedor(vnd_codigo, vnd_nombres, vnd_estcod) " +
                        "VALUES(?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVnd_codigo());
            pst.setString(2, input.getVnd_nombres());
            pst.setInt(3, input.getVnd_estcod());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Vendedor Read(String id) {
        Vendedor vendedor = new Vendedor();
        try {
            String SQL = "SELECT * FROM vendedor WHERE vnd_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                vendedor.setVnd_codigo(res.getString("vnd_codigo"));
                vendedor.setVnd_nombres(res.getString("vnd_nombres"));
                vendedor.setVnd_estcod(res.getInt("vnd_estcod"));
            }
        } catch(Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return vendedor;
    }

    @Override
    public List<Vendedor> ReadAll() {
        List<Vendedor> lst = null;
        try {
            String SQL = "SELECT * FROM vendedor ORDER BY vnd_nombres";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);
            lst = new ArrayList<>();
            
            while(res.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setVnd_codigo(res.getString("vnd_codigo"));
                vendedor.setVnd_nombres(res.getString("vnd_nombres"));
                vendedor.setVnd_estcod(res.getInt("vnd_estcod"));
                
                lst.add(vendedor);
            }
        } catch(Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(Vendedor input) {
        boolean result = false;
        try {
            String SQL = "UPDATE vendedor SET vnd_nombres=?, vnd_estcod=? " +
                        "WHERE vnd_codigo=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVnd_nombres());
            pst.setInt(2, input.getVnd_estcod());
            pst.setString(3, input.getVnd_codigo());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            String SQL = "DELETE FROM vendedor WHERE vnd_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Delete: " + e.getMessage());
        }
        return result;
    }
    
}
