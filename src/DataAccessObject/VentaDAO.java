package DataAccessObject;
import BusinessEntity.Venta;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

        
public class VentaDAO extends ConexionMySQL implements IBaseDAO<Venta>
{
    public static void main(String[] args){
        System.out.println("USANDO LA CLASE VENTA DAO");
        VentaDAO myDAO = new VentaDAO();
        
        // Ejemplo de lectura de una venta
        Venta myVenta = myDAO.Read("V001");
        if(myVenta != null && myVenta.getVen_correl() != null) {
            System.out.println("Correlativo: " + myVenta.getVen_correl());
            System.out.println("Subtotal: " + myVenta.getVen_subtotal());
            System.out.println("Total: " + myVenta.getVen_total());
        }
        
    }

    @Override
    public boolean Create(Venta input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO venta(ven_correl, ven_sunat, ven_fecemi, " +
                        "ven_subtotal, ven_igv, ven_dscto, ven_total, ven_estcod, vnd_codigo, cli_codigo) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVen_correl());
            pst.setString(2, input.getVen_sunat());
            pst.setDate(3, new java.sql.Date(input.getVen_fecemi().getTime()));
            pst.setDouble(4, input.getVen_subtotal());
            pst.setDouble(5, input.getVen_igv());
            pst.setDouble(6, input.getVen_dscto());
            pst.setDouble(7, input.getVen_total());
            pst.setInt(8, input.getVen_estcod());
            pst.setString(9, input.getVnd_codigo());
            pst.setString(10, input.getCli_codigo());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Venta Read(String id) {
        Venta venta = new Venta();
        try {
            String SQL = "SELECT * FROM venta WHERE ven_correl = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                venta.setVen_correl(res.getString("ven_correl"));
                venta.setVen_sunat(res.getString("ven_sunat"));
                venta.setVen_fecemi(res.getDate("ven_fecemi"));
                venta.setVen_subtotal(res.getDouble("ven_subtotal"));
                venta.setVen_igv(res.getDouble("ven_igv"));
                venta.setVen_dscto(res.getDouble("ven_dscto"));
                venta.setVen_total(res.getDouble("ven_total"));
                venta.setVen_estcod(res.getInt("ven_estcod"));
                venta.setVnd_codigo(res.getString("vnd_codigo"));
                venta.setCli_codigo(res.getString("cli_codigo"));
            }
        } catch(Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return venta;
    }

    @Override
    public List<Venta> ReadAll() {
        List<Venta> lst = null;
        try {
            String SQL = "SELECT * FROM venta ORDER BY ven_fecemi DESC";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);
            lst = new ArrayList<>();
            
            while(res.next()) {
                Venta venta = new Venta();
                venta.setVen_correl(res.getString("ven_correl"));
                venta.setVen_sunat(res.getString("ven_sunat"));
                venta.setVen_fecemi(res.getDate("ven_fecemi"));
                venta.setVen_subtotal(res.getDouble("ven_subtotal"));
                venta.setVen_igv(res.getDouble("ven_igv"));
                venta.setVen_dscto(res.getDouble("ven_dscto"));
                venta.setVen_total(res.getDouble("ven_total"));
                venta.setVen_estcod(res.getInt("ven_estcod"));
                venta.setVnd_codigo(res.getString("vnd_codigo"));
                venta.setCli_codigo(res.getString("cli_codigo"));
                
                lst.add(venta);
            }
        } catch(Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(Venta input) {
        boolean result = false;
        try {
            String SQL = "UPDATE venta SET ven_sunat=?, ven_fecemi=?, " +
                        "ven_subtotal=?, ven_igv=?, ven_dscto=?, ven_total=?, " +
                        "ven_estcod=?, vnd_codigo=?, cli_codigo=? WHERE ven_correl=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVen_sunat());
            pst.setDate(2, new java.sql.Date(input.getVen_fecemi().getTime()));
            pst.setDouble(3, input.getVen_subtotal());
            pst.setDouble(4, input.getVen_igv());
            pst.setDouble(5, input.getVen_dscto());
            pst.setDouble(6, input.getVen_total());
            pst.setInt(7, input.getVen_estcod());
            pst.setString(8, input.getVnd_codigo());
            pst.setString(9, input.getCli_codigo());
            pst.setString(10, input.getVen_correl());
            
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
            String SQL = "DELETE FROM venta WHERE ven_correl = ?";
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
