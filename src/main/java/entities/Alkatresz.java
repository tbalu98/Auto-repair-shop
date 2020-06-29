package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Alkatresz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nev")
    private String nev;

    @Column(name = "ar")
    private Integer ar;

    @Column(name = "garancia_idotartama")
    private Integer garanciaIdotartama;

    @Column(name ="cikkszam")
    private Integer cikkszam;
    //persistbol merge
    @OneToMany(/*cascade = CascadeType.MERGE, */mappedBy = "alkatresz", fetch = FetchType.LAZY)
    private List<FelhasznaltAlkatresz> felhasznaltAlkatreszek = new ArrayList<>();

    public Alkatresz() {}

    public Alkatresz(String nev, Integer ar, Integer garanciaIdotartama) {
        this.nev = nev;
        this.ar = ar;
        this.garanciaIdotartama = garanciaIdotartama;
    }


    public Alkatresz(String nev, Integer ar, Integer garanciaIdotartama, Integer cikkszam) {
        this.nev = nev;
        this.ar = ar;
        this.garanciaIdotartama = garanciaIdotartama;
        this.cikkszam = cikkszam;
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

    public List<FelhasznaltAlkatresz> getFelhasznaltAlkatreszek() {
        return felhasznaltAlkatreszek;
    }

    public void setFelhasznaltAlkatreszek(List<FelhasznaltAlkatresz> felhasznaltAlkatreszek) {
        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
    }

    public Integer getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(Integer cikkszam) {
        this.cikkszam = cikkszam;
    }
}
