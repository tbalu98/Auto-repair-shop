package entities;

import org.pmw.tinylog.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity(name = "szereles")
public class Repair implements hasPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "szereles_kezdete")
    private Timestamp szerelesKezdete;

    @Column(name = "szereles_vege")
    private Timestamp szerelesVege;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gepjarmu_id")
    private Car car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ugyfel_id")
    private Customer customer;

    //szereles volt
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "repair", fetch = FetchType.LAZY)
    private List<Assembly> javitasok = new ArrayList<>();


    @Column(name = "ar")
    private Integer ar;


    public Repair(){}

    public Repair(Car car, Customer customer) {

        this.car = car;
        this.customer = customer;

        this.szerelesKezdete = new Timestamp(System.currentTimeMillis());

        this.ar = 0;

    }

    public Repair(Timestamp szerelesKezdete, Timestamp szerelesVege, Car car, Customer customer, List<Assembly> javitasok, Integer ar) {
        this.szerelesKezdete = szerelesKezdete;
        this.szerelesVege = szerelesVege;
        this.car = car;
        this.customer = customer;
        this.javitasok = javitasok;
        this.ar = ar;
    }

    public Repair(Timestamp szerelesKezdete, Timestamp szerelesVege, Car car, Customer customer,
                  Integer ar) {
        this.szerelesKezdete = szerelesKezdete;
        this.szerelesVege = szerelesVege;
        this.car = car;
        this.customer = customer;
        this.ar = ar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getSzerelesKezdete() {
        return szerelesKezdete;
    }

    public void setSzerelesKezdete(Timestamp szerelesKezdete) {
        this.szerelesKezdete = szerelesKezdete;
    }

    public Timestamp getSzerelesVege() {
        return szerelesVege;
    }

    public void setSzerelesVege(Timestamp szerelesVege) {
        this.szerelesVege = szerelesVege;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public List<Assembly> getJavitasok() {

        return javitasok;
    }

    public void setJavitasok(List<Assembly> javitasok) {
        this.javitasok = javitasok;
    }
/*
    public Integer getMunkaorakSzama() {
        return munkaorakSzama;
    }

    public void setMunkaorakSzama(Integer munkaorakSzama) {
        this.munkaorakSzama = munkaorakSzama;
    }*/


    @Override
    public String toString() {
        return "Szereles{" +
                "id=" + id +
                ", szerelesKezdete=" + szerelesKezdete +
                ", szerelesVege=" + szerelesVege +
                ", gepjarmu=" + car.getId() +
                ", ugyfel=" + customer.getId() +
                ", javitasok=" + javitasok +
                ", ar=" + ar +
                '}';
    }

    public List<Object> getJavitasokIdk() {
        List<Object> javitasokIdk = new ArrayList<>();
        Logger.info(this.getJavitasok().size());
        for(Assembly assembly : this.getJavitasok()){
            javitasokIdk.add(assembly.getId());

        }
        return javitasokIdk;
    }
    public Integer aratSzamol(){

        Integer ar = new Integer(0);
        for(Assembly assembly : this.getJavitasok()){

            ar += assembly.aratSzamol();
            /*
            if(javitas instanceof FixAruJavitas) {
                Logger.info("Fix");
                ar += ((FixAruJavitas)javitas).aratSzamol();
            }else if(javitas instanceof  OradijasJavitas){
                Logger.info("Oradijas");
                ar += ((OradijasJavitas)javitas).aratSzamol();
            }*/

        }
        this.ar = ar;
        return ar;
    }

    public String getUgyfelNev() {
        return this.customer.getNev();
    }

    public String getUgyfelTelefonszam() {
    return  this.customer.getTelefonszam();
    }

    public Integer getGepjarmuAlvazszam() {
        return this.car.getAlvazszam();
    }

    public String getGepjarmuTipus() {
        return this.car.getTipus();
    }

    public String getUgyfelLakcim() {
        return this.getCustomer().getLakcim();
    }

    public Integer getGepjarmuMotorTerfogat() {
        return this.car.getMotorterfogat();
    }

    public Integer getGepjarmuEvjarat() {
        return this.car.getEvjarat();
    }

    public Integer getGepjarmuTeljesitmeny() {
        return this.car.getTeljesitmeny();
    }

    public LocalDate getGepjarmuVizsgaLejarta() {
        return this.car.getVizsgaLejarta();
    }
}
