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
    private Integer vehicleIdentificationNumber;

    @Column(name = "vizsga_lejarta")
    private LocalDate expirationDate;

    @Column(name =  "evjarat")
    private Integer year;

    //gepjarmu volt
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Repair> repairs = new ArrayList<>();

    public Car(){
        Logger.info("Noarg gepjarmu");
    }



    public Car(CarParameter gepjarmuParameter, Integer vehicleIdentificationNumber, LocalDate expirationDate, Integer year) {
        this.gepjarmuParameter = gepjarmuParameter;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.expirationDate = expirationDate;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public CarParameter getGepjarmuParameter() {
        return gepjarmuParameter;
    }

    public void setGepjarmuParameter(CarParameter gepjarmuParameter) {
        this.gepjarmuParameter = gepjarmuParameter;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(Integer vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }

    public String getType() {
        return this.gepjarmuParameter.getType();
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", gepjarmuParameter=" + gepjarmuParameter +
                ", vehicleIdentificationNumber=" + vehicleIdentificationNumber +
                ", expirationDate=" + expirationDate +
                ", year=" + year +
                ", repairs=" + repairs.size() +
                '}';
    }

    public Integer getEngineVolume() {

        return this.gepjarmuParameter.getEngineVolume();

    }


    public Integer getPower() {

        return this.gepjarmuParameter.getPower();
    }
}

