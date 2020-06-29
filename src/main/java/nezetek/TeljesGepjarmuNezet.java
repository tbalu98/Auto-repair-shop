package nezetek;

import entities.Gepjarmu;
import entities.Gepjarmuparameter;
import org.pmw.tinylog.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeljesGepjarmuNezet {


    public static List<TeljesGepjarmuNezet> of(List<Gepjarmu> gepjarmuvek){

        List<TeljesGepjarmuNezet> teljesGepjarmuNezetek = new ArrayList<>();
        for(Gepjarmu gepjarmu: gepjarmuvek){

            teljesGepjarmuNezetek.add(new TeljesGepjarmuNezet(gepjarmu));

        }

        Logger.info(teljesGepjarmuNezetek);
        return teljesGepjarmuNezetek;

    }
    private Integer id;

    private Integer alvazszam;

    private LocalDate vizsgaLejarta;

    private Integer evjarat;

    private String ptipus;

    private Integer pmotorterfogat;

    private Integer pteljesitmeny;

    private Gepjarmu gepjarmu;


    public TeljesGepjarmuNezet(Gepjarmu gepjarmu){
        this.alvazszam = gepjarmu.getAlvazszam();
        this.vizsgaLejarta = gepjarmu.getVizsgaLejarta();
        this.evjarat = gepjarmu.getEvjarat();
        this.ptipus = gepjarmu.getTipus();
        this.pmotorterfogat = gepjarmu.getMotorterfogat();
        this.pteljesitmeny = gepjarmu.getTeljesitmeny();
        this.gepjarmu = gepjarmu;

    }

    public TeljesGepjarmuNezet(Gepjarmuparameter gepjarmuparameter, Integer alvazszam, LocalDate vizsgaLejarta, Integer evjarat,
                               String ptipus, Integer pmotorterfogat, Integer pteljesitmeny) {

        this.alvazszam = alvazszam;
        this.vizsgaLejarta = vizsgaLejarta;
        this.evjarat = evjarat;
        this.ptipus = ptipus;
        this.pmotorterfogat = pmotorterfogat;
        this.pteljesitmeny = pteljesitmeny;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getAlvazszam() {
        return alvazszam;
    }

    public void setAlvazszam(Integer alvazszam) {
        this.alvazszam = alvazszam;
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

    public String getPtipus() {
        return ptipus;
    }

    public void setPtipus(String ptipus) {
        this.ptipus = ptipus;
    }

    public Integer getPmotorterfogat() {
        return pmotorterfogat;
    }

    public void setPmotorterfogat(Integer pmotorterfogat) {
        this.pmotorterfogat = pmotorterfogat;
    }

    public Integer getPteljesitmeny() {
        return pteljesitmeny;
    }

    public void setPteljesitmeny(Integer pteljesitmeny) {
        this.pteljesitmeny = pteljesitmeny;
    }

    public Gepjarmu getGepjarmu() {
        return gepjarmu;
    }

    public void setGepjarmu(Gepjarmu gepjarmu) {
        this.gepjarmu = gepjarmu;
    }

    @Override
    public String toString() {
        return "TeljesGepjarmuNezet{" +
                "id=" + id +
                ", alvazszam=" + alvazszam +
                ", vizsgaLejarta=" + vizsgaLejarta +
                ", evjarat=" + evjarat +
                ", ptipus='" + ptipus + '\'' +
                ", pmotorterfogat=" + pmotorterfogat +
                ", pteljesitmeny=" + pteljesitmeny +
                '}';
    }
}