package View;

import BusinessEntity.Cliente;
import DataAccessObject.ClienteDAO;
import DataAccessObject.ConexionMySQL;

import java.sql.*;
import java.util.Scanner;

public class Consola {

    static ConexionMySQL db = new ConexionMySQL();
    static Connection conn = db.getConexion();
    static Scanner scanner = new Scanner(System.in);
    static String vnd_codigo = null;
    static String vnd_nombre = null;

    public static void main(String[] args) {
        if (conn != null) {
            loginVendedor();
        } else {
            System.out.println("No se pudo establecer conexión.");
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
            System.out.println("2. Clientes");
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
                    gestionarClientes();
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
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    static void nuevoPedido() {
    try {
        System.out.println("Registrar nuevo pedido");
        System.out.print("Código del cliente: ");
        String cliCodigo = scanner.nextLine();

        System.out.print("Código del artículo: ");
        String artCodigo = scanner.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        // Obtener precio del artículo
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
        double total = subtotal;

        // Generar códigos
        String venCorrel = "VEN" + String.format("%03d", (int) (Math.random() * 1000));
        String venSunat = "SUNAT" + String.format("%03d", (int) (Math.random() * 1000));

        // Insertar en tabla venta (sin IGV ni descuento)
        String sqlVenta = "INSERT INTO venta (ven_correl, ven_sunat, ven_fecemi, ven_subtotal, ven_total, ven_estcod, vnd_codigo, cli_codigo) " +
                          "VALUES (?, ?, GETDATE(), ?, ?, 1, ?, ?)";
        PreparedStatement pstVenta = conn.prepareStatement(sqlVenta);
        pstVenta.setString(1, venCorrel);
        pstVenta.setString(2, venSunat);
        pstVenta.setDouble(3, subtotal);
        pstVenta.setDouble(4, total);
        pstVenta.setString(5, vnd_codigo);
        pstVenta.setString(6, cliCodigo);
        pstVenta.executeUpdate();

        // Insertar en venta_detalle (sin IGV ni descuento)
        String sqlDetalle = "INSERT INTO venta_detalle (ven_correl, art_codigo, ven_precio, ven_cantidad, ven_subtotal) " +
                            "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstDetalle = conn.prepareStatement(sqlDetalle);
        pstDetalle.setString(1, venCorrel);
        pstDetalle.setString(2, artCodigo);
        pstDetalle.setDouble(3, precio);
        pstDetalle.setInt(4, cantidad);
        pstDetalle.setDouble(5, subtotal);
        pstDetalle.executeUpdate();

        System.out.println("Pedido registrado correctamente con código: " + venCorrel);

    } catch (SQLException | NumberFormatException e) {
        System.out.println("Error al registrar el pedido: " + e.getMessage());
    }
}

    static void gestionarClientes() {
      ClienteDAO dao = new ClienteDAO();

        System.out.println("1. Buscar cliente por código");
        System.out.println("2. Registrar nuevo cliente");
        System.out.print("Seleccione (1 o 2): ");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            System.out.print("Ingrese código del cliente: ");
            String codigo = scanner.nextLine();
            Cliente cli = dao.Read(codigo);

            if (cli == null || cli.getCli_codigo() == null) {
                System.out.println("Cliente no encontrado.");
            } else {
                System.out.println("Cliente:");
                System.out.println("Razón Social: " + cli.getCli_razsoc());
                System.out.println("Email: " + cli.getCli_email());
                System.out.println("Dirección: " + cli.getCli_direcc());
                System.out.println("Estado: " + cli.getCli_estcod());
            }
        } else if (input.equals("2")) {
            Cliente nuevo = new Cliente();
            System.out.print("Código: ");
            nuevo.setCli_codigo(scanner.nextLine());
            System.out.print("Doc. Num: ");
            nuevo.setCli_docnum(scanner.nextLine());
            System.out.print("Razón Social: ");
            nuevo.setCli_razsoc(scanner.nextLine());
            System.out.print("Dirección: ");
            nuevo.setCli_direcc(scanner.nextLine());
            System.out.print("Email: ");
            nuevo.setCli_email(scanner.nextLine());
            System.out.print("Estado (ACT/INACT): ");
            nuevo.setCli_estcod(scanner.nextLine());

            if (dao.Create(nuevo)) {
                System.out.println("Cliente registrado correctamente.");
            } else {
                System.out.println("Error al registrar cliente.");
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    static void listarPedidos() {
        System.out.println("Pedidos registrados:");
        try {
            String sql = "SELECT ven_correl, ven_fecemi, cli_codigo, ven_total FROM venta ORDER BY ven_fecemi DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Venta: " + rs.getString("ven_correl") +
                        " | Fecha: " + rs.getDate("ven_fecemi") +
                        " | Cliente: " + rs.getString("cli_codigo") +
                        " | Total: " + rs.getDouble("ven_total"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }
    }

    static void verStock() {
        System.out.println("Lista de artículos:");
        try {
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
            System.out.print("Estado (1=ACT, 0=INACT): ");
            int estado = Integer.parseInt(scanner.nextLine());
            System.out.print("Código categoría: ");
            String cat_codigo = scanner.nextLine();

            String sql = "INSERT INTO Articulo (art_codigo, art_description, art_precio, art_estcod, cat_codigo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, codigo);
            pst.setString(2, descripcion);
            pst.setDouble(3, precio);
            pst.setInt(4, estado);
            pst.setString(5, cat_codigo);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Artículo agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el artículo.");
            }

            pst.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    static void eliminarPedido() {
        try {
            System.out.print("Ingrese el código de la venta a eliminar: ");
            String venCorrel = scanner.nextLine();

            // Primero eliminar detalle
            String sqlDetalle = "DELETE FROM venta_detalle WHERE ven_correl = ?";
            PreparedStatement pstDetalle = conn.prepareStatement(sqlDetalle);
            pstDetalle.setString(1, venCorrel);
            pstDetalle.executeUpdate();
            pstDetalle.close();

            // Luego eliminar venta
            String sqlVenta = "DELETE FROM venta WHERE ven_correl = ?";
            PreparedStatement pstVenta = conn.prepareStatement(sqlVenta);
            pstVenta.setString(1, venCorrel);
            int rows = pstVenta.executeUpdate();
            pstVenta.close();

            if (rows > 0) {
                System.out.println("Pedido eliminado correctamente.");
            } else {
                System.out.println("No se encontró el pedido.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
        }
    }
}