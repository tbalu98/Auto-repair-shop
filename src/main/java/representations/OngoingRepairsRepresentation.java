package representations;


import entities.Car;
import entities.Repair;
import entities.Customer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OngoingRepairsRepresentation {

    public static List<OngoingRepairsRepresentation> of(List<Repair> szerelesek){

        List<OngoingRepairsRepresentation> folyamatbanLevoSzerelesNezetek = new ArrayList<>();
        for(Repair repair : szerelesek){

            folyamatbanLevoSzerelesNezetek.add(new OngoingRepairsRepresentation(repair));

        }

        return folyamatbanLevoSzerelesNezetek;

    }


    private Integer id;

    private String nev;

    private String telefonszam;

    private Timestamp szerelesKezdete;

    private String tipus;

    public OngoingRepairsRepresentation(Customer customer, Car car, Repair repair){


        this.id = repair.getId();
        this.nev = customer.getNev();
        this.telefonszam = customer.getTelefonszam();
        this.szerelesKezdete = repair.getSzerelesKezdete();
        this.tipus = car.getTipus();

    }

    public OngoingRepairsRepresentation(Repair repair){

        this.id = repair.getId();
        this.nev = repair.getCustomer().getNev();
        this.telefonszam = repair.getCustomer().getTelefonszam();
        this.szerelesKezdete = repair.getSzerelesKezdete();
        //demeter torvenye
        this.tipus = repair.getCar().getTipus();

    }

    public OngoingRepairsRepresentation(){}

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
