package nezetek;

import controllers.LezartSzerelesek;
import entities.Szereles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LezartSzerelesNezet<T> {

    public static List<LezartSzerelesNezet> of(List<Szereles> szerelesek){
        List<LezartSzerelesNezet> lezartSzerelesNezetek = new ArrayList<>();
        for(Szereles szereles: szerelesek){

            lezartSzerelesNezetek.add(new LezartSzerelesNezet(szereles));

        }
        return lezartSzerelesNezetek;
}


    private String nev;
    private String telefonszam;
    private Integer alvazszam;
    private String tipus;
    private Timestamp szerelesVege;
    private Integer ar;
    private Szereles szereles;

    public LezartSzerelesNezet(Szereles szereles) {
        this.nev = szereles.getUgyfelNev();
        this.telefonszam = szereles.getUgyfelTelefonszam();
        this.alvazszam = szereles.getGepjarmuAlvazszam();
        //this.tipus = szereles.getGepjarmu().getGepjarmuparameter().getTipus();
        this.tipus = szereles.getGepjarmuTipus();
        this.szerelesVege = szereles.getSzerelesVege();
        this.ar = szereles.getAr();
        this.szereles = szereles;
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

    public Integer getAlvazszam() {
        return alvazszam;
    }

    public void setAlvazszam(Integer alvazszam) {
        this.alvazszam = alvazszam;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Timestamp getSzerelesVege() {
        return szerelesVege;
    }

    public void setSzerelesVege(Timestamp szerelesVege) {
        this.szerelesVege = szerelesVege;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Szereles getSzereles() {
        return szereles;
    }

    public void setSzereles(Szereles szereles) {
        this.szereles = szereles;
    }
}
