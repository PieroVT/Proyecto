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
                System.out.println(" Bienvenido, " + vnd_nombre);
                menuPrincipal();
            } else {
                System.out.println(" Código de vendedor no válido.");
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(" Error al validar vendedor: " + e.getMessage());
        }
    }

    static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n---- Menú Principal ----");
            System.out.println("1. Nueva venta");
            System.out.println("2. Clientes");
            System.out.println("3. Detalle de ventas");
            System.out.println("4. Artículos");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    nuevaVenta();
                    break;
                case 2:
                    gestionarClientes();
                    break;
                case 3:
                    mostrarVentas();
                    break;
                case 4:
                    mostrarArticulos();
                    break;
                case 0:
                    System.out.println("Hasta Luego");
                    break;
                default:
                    System.out.println(" Opción inválida.");
            }
        } while (opcion != 0);
    }

    static void nuevaVenta() {
    try {
        System.out.println("Registro de nueva venta");
        System.out.print("Ingrese código del cliente (ej: CLI001): ");
        String cliCodigo = scanner.nextLine();

        System.out.print("Ingrese código del artículo (ej: JNE3798): ");
        String artCodigo = scanner.nextLine();

        System.out.print("Ingrese cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        // Obtener precio del artículo
        String sqlPrecio = "SELECT art_precio FROM Articulo WHERE art_codigo = ?";
        PreparedStatement pstPrecio = conn.prepareStatement(sqlPrecio);
        pstPrecio.setString(1, artCodigo);
        ResultSet rsPrecio = pstPrecio.executeQuery();

        if (!rsPrecio.next()) {
            System.out.println(" Artículo no encontrado.");
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
        System.out.println(" Error al registrar venta: " + e.getMessage());
    }
}

   static void gestionarClientes() {
    System.out.println("1. Buscar cliente por código");
    System.out.println("2. Registrar nuevo cliente");
    System.out.print("Seleccione (1 o 2): ");
    String input = scanner.nextLine();

    ClienteDAO dao = new ClienteDAO();

    if (input.equals("1")) {
        System.out.print("Ingrese código del cliente: ");
        String codigo = scanner.nextLine();
        Cliente cli = dao.Read(codigo);

        if (cli.getCli_codigo() == null) {
            System.out.println(" Cliente no encontrado.");
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
            System.out.println(" Error al registrar cliente.");
        }
    } else {
        System.out.println(" Opción inválida.");
    }
}


    static void mostrarVentas() {
        System.out.println("Ventas registradas:");
        try {
            String sql = "SELECT ven_correl, ven_fecemi, cli_codigo, ven_total FROM venta ORDER BY ven_fecemi DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Venta: " + rs.getString("ven_correl") +
                                   " Fecha: " + rs.getDate("ven_fecemi") +
                                   " Cliente: " + rs.getString("cli_codigo") +
                                   " Total: " + rs.getDouble("ven_total"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(" Error al obtener ventas: " + e.getMessage());
        }
    }

    static void mostrarArticulos() {
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
}