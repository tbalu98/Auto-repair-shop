package filters;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

public class AlkatreszFilter {

    private Integer id;
    private String nev;

    private Integer ar;

    private Integer garanciaIdotartama;

    private Integer cikkszam;
    public AlkatreszFilter(Integer id, String nev, Integer ar, Integer garanciaIdotartama, Integer cikkszam) {
        this.id = id;
        this.nev = nev;
        this.ar = ar;
        this.garanciaIdotartama = garanciaIdotartama;
        this.cikkszam = cikkszam;
    }

    public AlkatreszFilter(String nev, String ar, String garanciaIdotartama,String cikkszam){

        if(!cikkszam.equals("")){
            this.cikkszam = Integer.parseInt(cikkszam);
        }
        this.id = null;
        //if(!nev.equals("")){
            this.nev = nev;

        if(!ar.equals("")){
            this.ar = Integer.parseInt(ar);
        }
        if(!garanciaIdotartama.equals("")){
            this.garanciaIdotartama = Integer.parseInt(garanciaIdotartama);
        }

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getGaranciaIdotartama() {
        return garanciaIdotartama;
    }

    public void setGaranciaIdotartama(Integer garanciaIdotartama) {
        this.garanciaIdotartama = garanciaIdotartama;
    }

    public Integer getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(Integer cikkszam) {
        this.cikkszam = cikkszam;
    }
}
