
package DataAccessObject;
import BusinessEntity.Venta_detalle;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

        
public class VentaDetalleDAO extends ConexionMySQL implements IBaseDAO<Venta_detalle>
{
    public static void main(String[] args){
        System.out.println("USANDO LA CLASE VENTA DETALLE DAO");
        VentaDetalleDAO myDAO = new VentaDetalleDAO();
        
        // Ejemplo de lectura de un detalle de venta
        Venta_detalle myDetalle = myDAO.Read("ART001");
        if(myDetalle != null && myDetalle.getArt_codigo() != null) {
            System.out.println("Artículo: " + myDetalle.getArt_codigo());
            System.out.println("Cantidad: " + myDetalle.getVen_cantidad());
            System.out.println("Precio: " + myDetalle.getVen_precio());
        }
    }

    @Override
    public boolean Create(Venta_detalle input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO venta_detalle(ven_correl, art_codigo, ven_precio, " +
                        "ven_cantidad, ven_dscto, ven_igv, ven_subtotal) " +
                        "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVen_correl());
            pst.setString(2, input.getArt_codigo());
            pst.setDouble(3, input.getVen_precio());
            pst.setInt(4, input.getVen_cantidad());
            pst.setDouble(5, input.getVen_dscto());
            pst.setDouble(6, input.getVen_igv());
            pst.setDouble(7, input.getVen_subtotal());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Venta_detalle Read(String id) {
        Venta_detalle detalle = new Venta_detalle();
        try {
            String SQL = "SELECT * FROM venta_detalle WHERE art_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                detalle.setVen_correl(res.getString("ven_correl"));
                detalle.setArt_codigo(res.getString("art_codigo"));
                detalle.setVen_precio(res.getDouble("ven_precio"));
                detalle.setVen_cantidad(res.getInt("ven_cantidad"));
                detalle.setVen_dscto(res.getDouble("ven_dscto"));
                detalle.setVen_igv(res.getDouble("ven_igv"));
                detalle.setVen_subtotal(res.getDouble("ven_subtotal"));
            }
        } catch(Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return detalle;
    }

    @Override
    public List<Venta_detalle> ReadAll() {
        List<Venta_detalle> lst = null;
        try {
            String SQL = "SELECT * FROM venta_detalle ORDER BY ven_correl, art_codigo";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);
            lst = new ArrayList<>();
            
            while(res.next()) {
                Venta_detalle detalle = new Venta_detalle();
                detalle.setVen_correl(res.getString("ven_correl"));
                detalle.setArt_codigo(res.getString("art_codigo"));
                detalle.setVen_precio(res.getDouble("ven_precio"));
                detalle.setVen_cantidad(res.getInt("ven_cantidad"));
                detalle.setVen_dscto(res.getDouble("ven_dscto"));
                detalle.setVen_igv(res.getDouble("ven_igv"));
                detalle.setVen_subtotal(res.getDouble("ven_subtotal"));
                
                lst.add(detalle);
            }
        } catch(Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(Venta_detalle input) {
        boolean result = false;
        try {
            String SQL = "UPDATE venta_detalle SET ven_correl=?, ven_precio=?, " +
                        "ven_cantidad=?, ven_dscto=?, ven_igv=?, ven_subtotal=? " +
                        "WHERE art_codigo=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getVen_correl());
            pst.setDouble(2, input.getVen_precio());
            pst.setInt(3, input.getVen_cantidad());
            pst.setDouble(4, input.getVen_dscto());
            pst.setDouble(5, input.getVen_igv());
            pst.setDouble(6, input.getVen_subtotal());
            pst.setString(7, input.getArt_codigo());
            
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
            String SQL = "DELETE FROM venta_detalle WHERE art_codigo = ?";
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
