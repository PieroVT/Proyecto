package View;

import BusinessEntity.Vendedor;
import BusinessEntity.Cliente;

public class Console {
    public static void main(String[] args) {
        // Crear un cliente de ejemplo
        Cliente miCliente = new Cliente();
        miCliente.setCli_codigo("CLI001");
        miCliente.setCli_direcc("Calle falsa 123");
        miCliente.setCli_docnum("12345678");
        miCliente.setCli_email("empresaA@gmail.com");
        miCliente.setCli_estcod("ACT");
        miCliente.setCli_razsoc("Empresa A");

        // Crear un vendedor de ejemplo
        Vendedor miVendedor = new Vendedor();
        miVendedor.setVnd_nombres("Juan Pérez");

        // Mostrar los datos en consola
        System.out.println("🧾 Cliente:");
        System.out.println(miCliente.toString());
        System.out.println("👨‍💼 Vendedor:");
        System.out.println(miVendedor.toString());
    }
}