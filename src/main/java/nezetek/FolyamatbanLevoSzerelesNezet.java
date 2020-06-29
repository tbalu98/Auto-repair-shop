package nezetek;


import entities.Gepjarmu;
import entities.Szereles;
import entities.Ugyfel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FolyamatbanLevoSzerelesNezet {

    public static List<FolyamatbanLevoSzerelesNezet> of(List<Szereles> szerelesek){

        List<FolyamatbanLevoSzerelesNezet> folyamatbanLevoSzerelesNezetek = new ArrayList<>();
        for(Szereles szereles: szerelesek){

            folyamatbanLevoSzerelesNezetek.add(new FolyamatbanLevoSzerelesNezet(szereles));

        }

        return folyamatbanLevoSzerelesNezetek;

    }


    private Integer id;

    private String nev;

    private String telefonszam;

    private Timestamp szerelesKezdete;

    private String tipus;

    public FolyamatbanLevoSzerelesNezet(Ugyfel ugyfel, Gepjarmu gepjarmu, Szereles szereles){


        this.id = szereles.getId();
        this.nev = ugyfel.getNev();
        this.telefonszam = ugyfel.getTelefonszam();
        this.szerelesKezdete = szereles.getSzerelesKezdete();
        this.tipus = gepjarmu.getTipus();

    }

    public FolyamatbanLevoSzerelesNezet(Szereles szereles){

        this.id = szereles.getId();
        this.nev = szereles.getUgyfel().getNev();
        this.telefonszam = szereles.getUgyfel().getTelefonszam();
        this.szerelesKezdete = szereles.getSzerelesKezdete();
        //demeter torvenye
        this.tipus = szereles.getGepjarmu().getTipus();

    }

    public FolyamatbanLevoSzerelesNezet(){}

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

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public Timestamp getSzerelesKezdete() {
        return szerelesKezdete;
    }

    public void setSzerelesKezdete(Timestamp szerelesKezdete) {
        this.szerelesKezdete = szerelesKezdete;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
