package representations;

import entities.Repair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FinishedRepairsRepresentation<T> {

    public static List<FinishedRepairsRepresentation> of(List<Repair> szerelesek){
        List<FinishedRepairsRepresentation> lezartSzerelesNezetek = new ArrayList<>();
        for(Repair repair : szerelesek){

            lezartSzerelesNezetek.add(new FinishedRepairsRepresentation(repair));

        }
        return lezartSzerelesNezetek;
}


    private String nev;
    private String telefonszam;
    private Integer alvazszam;
    private String tipus;
    private Timestamp szerelesVege;
    private Integer ar;
    private Repair repair;

    public FinishedRepairsRepresentation(Repair repair) {
        this.nev = repair.getUgyfelNev();
        this.telefonszam = repair.getUgyfelTelefonszam();
        this.alvazszam = repair.getGepjarmuAlvazszam();
        //this.tipus = szereles.getGepjarmu().getGepjarmuparameter().getTipus();
        this.tipus = repair.getGepjarmuTipus();
        this.szerelesVege = repair.getSzerelesVege();
        this.ar = repair.getAr();
        this.repair = repair;
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

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
