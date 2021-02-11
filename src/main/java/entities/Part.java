package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "alkatresz")
public class Part {

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
    //alkatresz volt
    @OneToMany(/*cascade = CascadeType.MERGE, */mappedBy = "part", fetch = FetchType.LAZY)
    private List<UsedPart> felhasznaltAlkatreszek = new ArrayList<>();

    public Part() {}

    public Part(String nev, Integer ar, Integer garanciaIdotartama) {
        this.nev = nev;
        this.ar = ar;
        this.garanciaIdotartama = garanciaIdotartama;
    }


    public Part(String nev, Integer ar, Integer garanciaIdotartama, Integer cikkszam) {
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

    public List<UsedPart> getFelhasznaltAlkatreszek() {
        return felhasznaltAlkatreszek;
    }

    public void setFelhasznaltAlkatreszek(List<UsedPart> felhasznaltAlkatreszek) {
        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
    }

    public Integer getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(Integer cikkszam) {
        this.cikkszam = cikkszam;
    }
}
