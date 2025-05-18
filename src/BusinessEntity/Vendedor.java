package BusinessEntity;

public class Vendedor 
        extends Venta{
    private String vnd_codigo;
    private String vnd_nombres;
    private int vnd_estcod;

    public Vendedor() {
    }

    public String getVnd_codigo() {
        return vnd_codigo;
    }

    public void setVnd_codigo(String vnd_codigo) {
        this.vnd_codigo = vnd_codigo;
    }

    public String getVnd_nombres() {
        return vnd_nombres;
    }

    public void setVnd_nombres(String vnd_nombres) {
        this.vnd_nombres = vnd_nombres;
    }

    public int getVnd_estcod() {
        return vnd_estcod;
    }

    public void setVnd_estcod(int vnd_estcod) {
        this.vnd_estcod = vnd_estcod;
    }

 
   

    
    
}
