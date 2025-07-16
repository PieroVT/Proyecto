package DataAccessObject;
import BusinessEntity.Cliente;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

        
public class ClienteDAO extends ConexionMySQL implements IBaseDAO<Cliente>
{
    public static void main(String[] args){
        System.out.println("USANDO LA CLASE CLIENTE DAO");
        ClienteDAO myDAO = new ClienteDAO();
        
        // Ejemplo de lectura de un cliente
        Cliente myCliente = myDAO.Read("CLI001");
        if(myCliente != null && myCliente.getCli_codigo() != null) {
            System.out.println("Código: " + myCliente.getCli_codigo());
            System.out.println("Documento: " + myCliente.getCli_docnum());
            System.out.println("Razón Social: " + myCliente.getCli_razsoc());
        }
    }

    @Override
    public boolean Create(Cliente input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO cliente(cli_codigo, cli_docnum, cli_razsoc, " +
                        "cli_direcc, cli_email, cli_estcod) " +
                        "VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getCli_codigo());
            pst.setString(2, input.getCli_docnum());
            pst.setString(3, input.getCli_razsoc());
            pst.setString(4, input.getCli_direcc());
            pst.setString(5, input.getCli_email());
            pst.setString(6, input.getCli_estcod());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Cliente Read(String id) {
        Cliente cliente = new Cliente();
        try {
            String SQL = "SELECT * FROM cliente WHERE cli_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                cliente.setCli_codigo(res.getString("cli_codigo"));
                cliente.setCli_docnum(res.getString("cli_docnum"));
                cliente.setCli_razsoc(res.getString("cli_razsoc"));
                cliente.setCli_direcc(res.getString("cli_direcc"));
                cliente.setCli_email(res.getString("cli_email"));
                cliente.setCli_estcod(res.getString("cli_estcod"));
            }
        } catch(Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> ReadAll() {
        List<Cliente> lst = null;
        try {
            String SQL = "SELECT * FROM cliente ORDER BY cli_razsoc";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);
            lst = new ArrayList<>();
            
            while(res.next()) {
                Cliente cliente = new Cliente();
                cliente.setCli_codigo(res.getString("cli_codigo"));
                cliente.setCli_docnum(res.getString("cli_docnum"));
                cliente.setCli_razsoc(res.getString("cli_razsoc"));
                cliente.setCli_direcc(res.getString("cli_direcc"));
                cliente.setCli_email(res.getString("cli_email"));
                cliente.setCli_estcod(res.getString("cli_estcod"));
                
                lst.add(cliente);
            }
        } catch(Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(Cliente input) {
        boolean result = false;
        try {
            String SQL = "UPDATE cliente SET cli_docnum=?, cli_razsoc=?, " +
                        "cli_direcc=?, cli_email=?, cli_estcod=? " +
                        "WHERE cli_codigo=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getCli_docnum());
            pst.setString(2, input.getCli_razsoc());
            pst.setString(3, input.getCli_direcc());
            pst.setString(4, input.getCli_email());
            pst.setString(5, input.getCli_estcod());
            pst.setString(6, input.getCli_codigo());
            
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
            String SQL = "DELETE FROM cliente WHERE cli_codigo = ?";
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
