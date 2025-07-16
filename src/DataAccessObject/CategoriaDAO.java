package DataAccessObject;
import BusinessEntity.Categoria;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO extends ConexionMySQL implements IBaseDAO<Categoria> {

    public static void main(String[] args) {
        System.out.println("USANDO LA CLASE CATEGORIA DAO");
        CategoriaDAO myDAO = new CategoriaDAO();

        // Ejemplo de lectura de una categoría
        Categoria myCategoria = myDAO.Read("CAT001");
        if (myCategoria != null && myCategoria.getCat_codigo() != null) {
            System.out.println("Código: " + myCategoria.getCat_codigo());
            System.out.println("Descripción: " + myCategoria.getCat_descri());
            System.out.println("Estado: " + myCategoria.getCat_estcod());
        }
    }

    @Override
    public boolean Create(Categoria input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO categoria(cat_codigo, cat_descri, cat_estcod) VALUES(?, ?, ?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getCat_codigo());
            pst.setString(2, input.getCat_descri());
            pst.setString(3, input.getCat_estcod());

            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error en Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Categoria Read(String id) {
        Categoria categoria = new Categoria();
        try {
            String SQL = "SELECT * FROM categoria WHERE cat_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                categoria.setCat_codigo(res.getString("cat_codigo"));
                categoria.setCat_descri(res.getString("cat_descri"));
                categoria.setCat_estcod(res.getString("cat_estcod"));
            }
        } catch (Exception e) {
            System.out.println("Error en Read: " + e.getMessage());
        }
        return categoria;
    }

    @Override
    public List<Categoria> ReadAll() {
        List<Categoria> lista = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM categoria ORDER BY cat_descri";
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery(SQL);

            while (res.next()) {
                Categoria categoria = new Categoria();
                categoria.setCat_codigo(res.getString("cat_codigo"));
                categoria.setCat_descri(res.getString("cat_descri"));
                categoria.setCat_estcod(res.getString("cat_estcod"));
                lista.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("Error en ReadAll: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean Update(Categoria input) {
        boolean result = false;
        try {
            String SQL = "UPDATE categoria SET cat_descri=?, cat_estcod=? WHERE cat_codigo=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getCat_descri());
            pst.setString(2, input.getCat_estcod());
            pst.setString(3, input.getCat_codigo());

            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error en Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            String SQL = "DELETE FROM categoria WHERE cat_codigo = ?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, id);

            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error en Delete: " + e.getMessage());
        }
        return result;
    }
}



