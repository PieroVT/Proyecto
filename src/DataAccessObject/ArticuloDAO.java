package DataAccessObject;
import BusinessEntity.Articulo;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

        
public class ArticuloDAO extends ConexionMySQL implements IBaseDAO<Articulo>
{
    public static void main(String[] args){
        System.out.println("USANDO LA CLASE ARTICULO DAO");
        ArticuloDAO myDAO = new ArticuloDAO();
        
        // Ejemplo de lectura de un artículo
        Articulo myArticulo = myDAO.Read("ART001");
        if(myArticulo != null && myArticulo.getArt_codigo() != null) {
            System.out.println("Código: " + myArticulo.getArt_codigo());
            System.out.println("Descripción: " + myArticulo.getArt_descripcion());
            System.out.println("Precio: " + myArticulo.getArt_precio());
        }
    }

    @Override
    public boolean Create(Articulo input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO articulo(art_codigo, art_descripcion, art_precio, " +
                        "art_estcod, cat_codigo) " +
                        "VALUES(?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getArt_codigo());
            pst.setString(2, input.getArt_descripcion());
            pst.setDouble(3, input.getArt_precio());
            pst.setInt(4, input.getArt_estcod());
            pst.setString(5, input.getCat_codigo());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch(Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Articulo Read(String id) {
        Articulo articulo = new Articulo();
        try {
            String SQL = "SELECT * FROM articulo WHERE art_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                articulo.setArt_codigo(res.getString("art_codigo"));
                articulo.setArt_descripcion(res.getString("art_descripcion"));
                articulo.setArt_precio(res.getDouble("art_precio"));
                articulo.setArt_estcod(res.getInt("art_estcod"));
                articulo.setCat_codigo(res.getString("cat_codigo"));
            }
        } catch(Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return articulo;
    }

    @Override
    public List<Articulo> ReadAll() {
        List<Articulo> lst = null;
        try {
            String SQL = "SELECT * FROM articulo ORDER BY art_descripcion";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);
            lst = new ArrayList<>();
            
            while(res.next()) {
                Articulo articulo = new Articulo();
                articulo.setArt_codigo(res.getString("art_codigo"));
                articulo.setArt_descripcion(res.getString("art_descripcion"));
                articulo.setArt_precio(res.getDouble("art_precio"));
                articulo.setArt_estcod(res.getInt("art_estcod"));
                articulo.setCat_codigo(res.getString("cat_codigo"));
                
                lst.add(articulo);
            }
        } catch(Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(Articulo input) {
        boolean result = false;
        try {
            String SQL = "UPDATE articulo SET art_descripcion=?, art_precio=?, " +
                        "art_estcod=?, cat_codigo=? " +
                        "WHERE art_codigo=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            
            pst.setString(1, input.getArt_descripcion());
            pst.setDouble(2, input.getArt_precio());
            pst.setInt(3, input.getArt_estcod());
            pst.setString(4, input.getCat_codigo());
            pst.setString(5, input.getArt_codigo());
            
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
            String SQL = "DELETE FROM articulo WHERE art_codigo = ?";
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

