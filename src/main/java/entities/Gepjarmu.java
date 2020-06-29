package entities;

import org.pmw.tinylog.Logger;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Gepjarmu  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gepjarmuparameter gepjarmuparameter;

    @Column(name = "alvazszam")
    private Integer alvazszam;

    @Column(name = "vizsga_lejarta")
    private LocalDate vizsgaLejarta;

    @Column(name =  "evjarat")
    private Integer evjarat;

    @OneToMany(mappedBy = "gepjarmu", fetch = FetchType.LAZY)
    private List<Szereles> szerelesek = new ArrayList<>();

    public Gepjarmu(){
        Logger.info("Noarg gepjarmu");
    }



    public Gepjarmu(Gepjarmuparameter gepjarmuparameter, Integer alvazszam, LocalDate vizsgaLejarta, Integer evjarat) {
        this.gepjarmuparameter = gepjarmuparameter;
        this.alvazszam = alvazszam;
        this.vizsgaLejarta = vizsgaLejarta;
        this.evjarat = evjarat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Szereles> getSzerelesek() {
        return szerelesek;
    }

    public void setSzerelesek(List<Szereles> szerelesek) {
        this.szerelesek = szerelesek;
    }

    public Gepjarmuparameter getGepjarmuparameter() {
        return gepjarmuparameter;
    }

    public void setGepjarmuparameter(Gepjarmuparameter gepjarmuparameter) {
        this.gepjarmuparameter = gepjarmuparameter;
    }

    public LocalDate getVizsgaLejarta() {
        return vizsgaLejarta;
    }

    public void setVizsgaLejarta(LocalDate vizsgaLejarta) {
        this.vizsgaLejarta = vizsgaLejarta;
    }

    public Integer getEvjarat() {
        return evjarat;
    }

    public void setEvjarat(Integer evjarat) {
        this.evjarat = evjarat;
    }

    public Integer getAlvazszam() {
        return alvazszam;
    }

    public void setAlvazszam(Integer alvazszam) {
        this.alvazszam = alvazszam;
    }

    public String getTipus() {
        return this.gepjarmuparameter.getTipus();
    }

    @Override
    public String toString() {
        return "Gepjarmu{" +
                "id=" + id +
                ", gepjarmuparameter=" + gepjarmuparameter +
                ", alvazszam=" + alvazszam +
                ", vizsgaLejarta=" + vizsgaLejarta +
                ", evjarat=" + evjarat +
                '}';
    }

    public Integer getMotorterfogat() {

        return this.gepjarmuparameter.getMotorterfogat();

    }


    public Integer getTeljesitmeny() {

        return this.gepjarmuparameter.getTeljesitmeny();
    }
}

