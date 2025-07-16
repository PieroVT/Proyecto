
package BusinessEntity;

import java.util.Date;


public class Venta {
   private String ven_correl;
    private String ven_sunat;
    private Date ven_fecemi;
    private Double ven_subtotal;
    private Double ven_igv;
    private Double ven_dscto;
    private Double ven_total;
    private int ven_estcod;
    private String vnd_codigo;
    private String cli_codigo;


    public Venta() {
    }

    public String getVen_correl() {
        return ven_correl;
    }

    public void setVen_correl(String ven_correl) {
        this.ven_correl = ven_correl;
    }

    public String getVen_sunat() {
        return ven_sunat;
    }

    public void setVen_sunat(String ven_sunat) {
        this.ven_sunat = ven_sunat;
    }

    public Date getVen_fecemi() {
        return ven_fecemi;
    }

    public void setVen_fecemi(Date ven_fecemi) {
        this.ven_fecemi = ven_fecemi;
    }

    public Double getVen_subtotal() {
        return ven_subtotal;
    }

    public void setVen_subtotal(Double ven_subtotal) {
        this.ven_subtotal = ven_subtotal;
    }

    public Double getVen_igv() {
        return ven_igv;
    }

    public void setVen_igv(Double ven_igv) {
        this.ven_igv = ven_igv;
    }

    public Double getVen_dscto() {
        return ven_dscto;
    }

    public void setVen_dscto(Double ven_dscto) {
        this.ven_dscto = ven_dscto;
    }

    public Double getVen_total() {
        return ven_total;
    }

    public void setVen_total(Double ven_total) {
        this.ven_total = ven_total;
    }

    public int getVen_estcod() {
        return ven_estcod;
    }

    public void setVen_estcod(int ven_estcod) {
        this.ven_estcod = ven_estcod;
    }

    public String getVnd_codigo() {
        return vnd_codigo;
    }

    public void setVnd_codigo(String vnd_codigo) {
        this.vnd_codigo = vnd_codigo;
    }

    public String getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(String cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    
    
}
