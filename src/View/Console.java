package View;
import BusinessEntity.Vendedor;
import BusinessEntity.Cliente;
public class Console {    
    public static void main(String[] cls){
        //Declaracion de variable
        int miEdad;//0
        Cliente miObject; //NULL
        miObject = new Cliente(); //Inicializacion
        miObject.setCli_codigo("CLI001");
        miObject.setCli_direcc("Calle falsa 123");
        miObject.setCli_docnum("12345678");
        miObject.setCli_email("empresaA@gmail.com");
        miObject.setCli_estcod("ACT");
        miObject.setCli_razsoc("Empresa A");
        
        //-----
        Vendedor miObject2 = new Vendedor();
        miObject2.setVnd_nombres("Juan perez");
        System.out.println(miObject.toString());
        System.out.println(miObject2.toString());
    }    
}
