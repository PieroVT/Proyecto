
package BusinessEntity;

public class Venta_detalle {
    
    private String ven_correl;
    private String art_codigo;
    private Double ven_precio;
    private int ven_cantidad;
    private Double ven_dscto;
    private Double ven_igv;
    private Double ven_subtotal;
    
    public Venta_detalle() {
    }

    public String getVen_correl() {
        return ven_correl;
    }

    public void setVen_correl(String ven_correl) {
        this.ven_correl = ven_correl;
    }

    public String getArt_codigo() {
        return art_codigo;
    }

    public void setArt_codigo(String art_codigo) {
        this.art_codigo = art_codigo;
    }

    public Double getVen_precio() {
        return ven_precio;
    }

    public void setVen_precio(Double ven_precio) {
        this.ven_precio = ven_precio;
    }

    public int getVen_cantidad() {
        return ven_cantidad;
    }

    public void setVen_cantidad(int ven_cantidad) {
        this.ven_cantidad = ven_cantidad;
    }

    public Double getVen_dscto() {
        return ven_dscto;
    }

    public void setVen_dscto(Double ven_dscto) {
        this.ven_dscto = ven_dscto;
    }

    public Double getVen_igv() {
        return ven_igv;
    }

    public void setVen_igv(Double ven_igv) {
        this.ven_igv = ven_igv;
    }

    public Double getVen_subtotal() {
        return ven_subtotal;
    }

    public void setVen_subtotal(Double ven_subtotal) {
        this.ven_subtotal = ven_subtotal;
    }

    
  
    
}
