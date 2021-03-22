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
    private Timestamp startOfRepair;

    @Column(name = "szereles_vege")
    private Timestamp endOfRepair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gepjarmu_id")
    private Car car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ugyfel_id")
    private Customer customer;

    //szereles volt
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "repair", fetch = FetchType.LAZY)
    private List<Assembly> assemblies = new ArrayList<>();


    @Column(name = "ar")
    private Integer price;


    public Repair(){}

    public Repair(Car car, Customer customer) {

        this.car = car;
        this.customer = customer;

        this.startOfRepair = new Timestamp(System.currentTimeMillis());

        this.price = 0;

    }

    public Repair(Timestamp startOfRepair, Timestamp endOfRepair, Car car, Customer customer, List<Assembly> assemblies, Integer price) {
        this.startOfRepair = startOfRepair;
        this.endOfRepair = endOfRepair;
        this.car = car;
        this.customer = customer;
        this.assemblies = assemblies;
        this.price = price;
    }

    public Repair(Timestamp startOfRepair, Timestamp endOfRepair, Car car, Customer customer,
                  Integer price) {
        this.startOfRepair = startOfRepair;
        this.endOfRepair = endOfRepair;
        this.car = car;
        this.customer = customer;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStartOfRepair() {
        return startOfRepair;
    }

    public void setStartOfRepair(Timestamp startOfRepair) {
        this.startOfRepair = startOfRepair;
    }

    public Timestamp getEndOfRepair() {
        return endOfRepair;
    }

    public void setEndOfRepair(Timestamp endOfRepair) {
        this.endOfRepair = endOfRepair;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Assembly> getAssemblies() {

        return assemblies;
    }

    public void setAssemblies(List<Assembly> assemblies) {
        this.assemblies = assemblies;
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
        return "Repair{" +
                "id=" + id +
                ", startOfRepair=" + startOfRepair +
                ", endOfRepair=" + endOfRepair +
                ", car=" + car +
                ", customer=" + customer +
                ", assemblies=" + assemblies.size() +
                ", price=" + price +
                '}';
    }

    public List<Object> getJavitasokIdk() {
        List<Object> javitasokIdk = new ArrayList<>();
        Logger.info(this.getAssemblies().size());
        for(Assembly assembly : this.getAssemblies()){
            javitasokIdk.add(assembly.getId());

        }
        return javitasokIdk;
    }
    public Integer computePrice(){

        Integer ar = new Integer(0);
        for(Assembly assembly : this.getAssemblies()){

            ar += assembly.computePrice();
            /*
            if(javitas instanceof FixAruJavitas) {
                Logger.info("Fix");
                ar += ((FixAruJavitas)javitas).aratSzamol();
            }else if(javitas instanceof  OradijasJavitas){
                Logger.info("Oradijas");
                ar += ((OradijasJavitas)javitas).aratSzamol();
            }*/

        }
        this.price = ar;
        return ar;
    }

    public String getCustomerName() {
        return this.customer.getName();
    }

    public String getCustomerTelephoneNumber() {
    return  this.customer.getTelephoneNumber();
    }

    public Integer getVehicleIdentificationNumber() {
        return this.car.getVehicleIdentificationNumber();
    }

    public String getCarType() {
        return this.car.getType();
    }

    public String getCustomerAddress() {
        return this.getCustomer().getAddress();
    }

    public Integer getVolumeOfEngine() {
        return this.car.getEngineVolume();
    }

    public Integer getYearOfCar() {
        return this.car.getYear();
    }

    public Integer getCarPower() {
        return this.car.getPower();
    }

    public LocalDate getExpirationDate() {
        return this.car.getExpirationDate();
    }
}
