package entities;

import org.pmw.tinylog.Logger;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "gepjarmu")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarParameter gepjarmuParameter;

    @Column(name = "alvazszam")
    private Integer alvazszam;

    @Column(name = "vizsga_lejarta")
    private LocalDate vizsgaLejarta;

    @Column(name =  "evjarat")
    private Integer evjarat;

    //gepjarmu volt
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Repair> szerelesek = new ArrayList<>();

    public Car(){
        Logger.info("Noarg gepjarmu");
    }



    public Car(CarParameter gepjarmuParameter, Integer alvazszam, LocalDate vizsgaLejarta, Integer evjarat) {
        this.gepjarmuParameter = gepjarmuParameter;
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

    public List<Repair> getSzerelesek() {
        return szerelesek;
    }

    public void setSzerelesek(List<Repair> szerelesek) {
        this.szerelesek = szerelesek;
    }

    public CarParameter getGepjarmuParameter() {
        return gepjarmuParameter;
    }

    public void setGepjarmuParameter(CarParameter gepjarmuParameter) {
        this.gepjarmuParameter = gepjarmuParameter;
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
        return this.gepjarmuParameter.getTipus();
    }

    @Override
    public String toString() {
        return "Gepjarmu{" +
                "id=" + id +
                ", gepjarmuparameter=" + gepjarmuParameter +
                ", alvazszam=" + alvazszam +
                ", vizsgaLejarta=" + vizsgaLejarta +
                ", evjarat=" + evjarat +
                '}';
    }

    public Integer getMotorterfogat() {

        return this.gepjarmuParameter.getMotorterfogat();

    }


    public Integer getTeljesitmeny() {

        return this.gepjarmuParameter.getTeljesitmeny();
    }
}

