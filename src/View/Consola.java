package View;

import java.sql.*;
import java.util.Scanner;
import DataAccessObject.ClienteDAO;
import BusinessEntity.Cliente;

public class Consola {

    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ENVIOS;encrypt=true;trustServerCertificate=true;";
    static final String USER = "sa";
    static final String PASS = "123456789";

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);
    static String vnd_codigo = null;
    static String vnd_nombre = null;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            loginVendedor();
        } catch (SQLException e) {
            System.out.println(" Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    static void loginVendedor() {
        System.out.println("---- Login de Vendedor ----");
        System.out.print("Ingrese código de vendedor: ");
        String inputCode = scanner.nextLine();

        try {
            String sql = "SELECT * FROM vendedor WHERE vnd_codigo = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, inputCode);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                vnd_codigo = rs.getString("vnd_codigo");
                vnd_nombre = rs.getString("vnd_nombres");
                System.out.println("Bienvenido, " + vnd_nombre);
                menuPrincipal();
            } else {
                System.out.println("Código de vendedor no válido.");
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Error al validar vendedor: " + e.getMessage());
        }
    }

    static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n---- Menú Principal ----");
            System.out.println("1. Nuevo Pedido");
            System.out.println("2. Editar Pedido");
            System.out.println("3. Ver Lista de Pedidos");
            System.out.println("4. Ver Stock");
            System.out.println("5. Agregar Producto al Stock");
            System.out.println("6. Eliminar Pedido");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    nuevoPedido();
                    break;
                case 2:
                    editarPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    verStock();
                    break;
                case 5:
                    agregarProductoStock();
                    break;
                case 6:
                    eliminarPedido();
                    break;
                case 7:
                    System.out.println("Hasta Luego");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    static void nuevoPedido() {
        try {
            System.out.println("Registro de nueva venta");
            System.out.print("Código del cliente: ");
            String cliCodigo = scanner.nextLine();

            System.out.print("Código del artículo: ");
            String artCodigo = scanner.nextLine();

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            String sqlPrecio = "SELECT art_precio FROM Articulo WHERE art_codigo = ?";
            PreparedStatement pstPrecio = conn.prepareStatement(sqlPrecio);
            pstPrecio.setString(1, artCodigo);
            ResultSet rsPrecio = pstPrecio.executeQuery();

            if (!rsPrecio.next()) {
                System.out.println("Artículo no encontrado.");
                return;
            }

            double precio = rsPrecio.getDouble("art_precio");
            double subtotal = precio * cantidad;
            double igv = subtotal * 0.18;
            double total = subtotal + igv;

            String venCorrel = "VEN" + String.format("%03d", (int) (Math.random() * 1000));
            String venSunat = "SUNAT" + String.format("%03d", (int) (Math.random() * 1000));

            String sqlVenta = "INSERT INTO venta (ven_correl, ven_sunat, ven_fecemi, ven_subtotal, ven_igv, ven_dscto, ven_total, ven_estcod, vnd_codigo, cli_codigo) VALUES (?, ?, GETDATE(), ?, ?, 0, ?, 1, ?, ?)";
            PreparedStatement pstVenta = conn.prepareStatement(sqlVenta);
            pstVenta.setString(1, venCorrel);
            pstVenta.setString(2, venSunat);
            pstVenta.setDouble(3, subtotal);
            pstVenta.setDouble(4, igv);
            pstVenta.setDouble(5, total);
            pstVenta.setString(6, vnd_codigo);
            pstVenta.setString(7, cliCodigo);
            pstVenta.executeUpdate();

            String sqlDetalle = "INSERT INTO venta_detalle (ven_correl, art_codigo, ven_precio, ven_cantidad, ven_dsct, ven_igv, ven_subtotal) VALUES (?, ?, ?, ?, 0, ?, ?)";
            PreparedStatement pstDetalle = conn.prepareStatement(sqlDetalle);
            pstDetalle.setString(1, venCorrel);
            pstDetalle.setString(2, artCodigo);
            pstDetalle.setDouble(3, precio);
            pstDetalle.setInt(4, cantidad);
            pstDetalle.setDouble(5, igv);
            pstDetalle.setDouble(6, subtotal);
            pstDetalle.executeUpdate();

            System.out.println("Venta registrada correctamente con código: " + venCorrel);

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
    }

    static void editarPedido() {
    try {
        System.out.print("Ingrese código de la venta : ");
        String venCorrel = scanner.nextLine();

        // Buscar el detalle
        String sqlDetalle = "SELECT * FROM venta_detalle WHERE ven_correl = ?";
        PreparedStatement pstDetalle = conn.prepareStatement(sqlDetalle);
        pstDetalle.setString(1, venCorrel);
        ResultSet rsDetalle = pstDetalle.executeQuery();

        if (!rsDetalle.next()) {
            System.out.println("No se encontró detalle para esa venta.");
            return;
        }

        String artCodigo = rsDetalle.getString("art_codigo");
        double precioUnitario = rsDetalle.getDouble("ven_precio");
        int cantidadActual = rsDetalle.getInt("ven_cantidad");

        System.out.println("Artículo actual: " + artCodigo);
        System.out.println("Cantidad actual: " + cantidadActual);
        System.out.print("Ingrese nueva cantidad: ");
        int nuevaCantidad = Integer.parseInt(scanner.nextLine());

        double nuevoSubtotal = precioUnitario * nuevaCantidad;
        double nuevoIGV = nuevoSubtotal * 0.18;
        double nuevoTotal = nuevoSubtotal + nuevoIGV;

        String sqlUpdateDetalle = "UPDATE venta_detalle SET ven_cantidad = ?, ven_igv = ?, ven_subtotal = ? WHERE ven_correl = ?";
        PreparedStatement pstUpdateDetalle = conn.prepareStatement(sqlUpdateDetalle);
        pstUpdateDetalle.setInt(1, nuevaCantidad);
        pstUpdateDetalle.setDouble(2, nuevoIGV);
        pstUpdateDetalle.setDouble(3, nuevoSubtotal);
        pstUpdateDetalle.setString(4, venCorrel);
        pstUpdateDetalle.executeUpdate();

    
        String sqlUpdateVenta = "UPDATE venta SET ven_subtotal = ?, ven_igv = ?, ven_total = ? WHERE ven_correl = ?";
        PreparedStatement pstUpdateVenta = conn.prepareStatement(sqlUpdateVenta);
        pstUpdateVenta.setDouble(1, nuevoSubtotal);
        pstUpdateVenta.setDouble(2, nuevoIGV);
        pstUpdateVenta.setDouble(3, nuevoTotal);
        pstUpdateVenta.setString(4, venCorrel);
        pstUpdateVenta.executeUpdate();

        System.out.println("Venta actualizada correctamente.");

    } catch (SQLException | NumberFormatException e) {
        System.out.println("Error al editar pedido: " + e.getMessage());
    }
}

    static void listarPedidos() {
        try {
            System.out.println("Ventas registradas:");
            String sql = "SELECT ven_correl, ven_fecemi, cli_codigo, ven_total FROM venta ORDER BY ven_fecemi DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Venta: " + rs.getString("ven_correl") +
                        " | Fecha: " + rs.getDate("ven_fecemi") +
                        " | Cliente: " + rs.getString("cli_codigo") +
                        " | Total: S/ " + rs.getDouble("ven_total"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }
    }

    static void verStock() {
        try {
            System.out.println("Lista de artículos:");
            String sql = "SELECT art_codigo, art_description, art_precio FROM Articulo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Código: " + rs.getString("art_codigo") +
                        " | Descripción: " + rs.getString("art_description") +
                        " | Precio: S/ " + rs.getDouble("art_precio"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener artículos: " + e.getMessage());
        }
    }

   static void agregarProductoStock() {
    try {
        System.out.println("Agregar nuevo artículo al stock:");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Categoría (cat_codigo): ");
        String categoria = scanner.nextLine(); 

        System.out.print("Estado (1(ACT) o 0(INACT)): ");
        String estadoTexto = scanner.nextLine().trim().toUpperCase();
        int estado = estadoTexto.equals("ACT") ? 1 : 0;

        String sql = "INSERT INTO Articulo (art_codigo, art_description, art_precio, art_estcod, cat_codigo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, codigo);
        pst.setString(2, descripcion);
        pst.setDouble(3, precio);
        pst.setInt(4, estado);
        pst.setString(5, categoria); 
        int filas = pst.executeUpdate();

        if (filas > 0) {
            System.out.println("Producto agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar el producto.");
        }

        pst.close();
    } catch (SQLException | NumberFormatException e) {
        System.out.println("Error al agregar producto: " + e.getMessage());
    }
}

    static void eliminarPedido() {
        try {
            System.out.print("Ingrese código de la venta a eliminar: ");
            String codigo = scanner.nextLine();

            String sqlDetalle = "DELETE FROM venta_detalle WHERE ven_correl = ?";
            PreparedStatement pstDetalle = conn.prepareStatement(sqlDetalle);
            pstDetalle.setString(1, codigo);
            pstDetalle.executeUpdate();

            String sqlVenta = "DELETE FROM venta WHERE ven_correl = ?";
            PreparedStatement pstVenta = conn.prepareStatement(sqlVenta);
            pstVenta.setString(1, codigo);
            pstVenta.executeUpdate();

            System.out.println("Pedido eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
        }
    }
}