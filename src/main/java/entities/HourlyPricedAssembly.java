package entities;

import javax.persistence.*;


@Entity
@DiscriminatorValue("ODJ")
public class HourlyPricedAssembly extends Assembly {

    private static Integer oradij = 2000;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "javitas_tipus_id")
    private HourlyPricedAssemblyType hourlyPricedAssemblyType;

    @Column(name = "munkaorak_szama")
    private Integer munkaOrakSzama;

    public HourlyPricedAssembly(){}

    public HourlyPricedAssembly(Repair repair, HourlyPricedAssemblyType hourlyPricedAssemblyType, Integer munkaOrakSzama) {
        this.repair = repair;
        this.hourlyPricedAssemblyType = hourlyPricedAssemblyType;
        this.munkaOrakSzama = munkaOrakSzama;
    }

    @Override
    public Integer aratSzamol() {

        Integer ar = this.alkatreszekAra();
        ar += this.munkaKoltsege();
        return ar;
    }

    private Integer munkaKoltsege(){
        if(munkaOrakSzama!=null)
        return this.munkaOrakSzama* HourlyPricedAssembly.oradij;
        else
            return 0;
    }

    public HourlyPricedAssemblyType getHourlyPricedAssemblyType() {
        return hourlyPricedAssemblyType;
    }

    public void setHourlyPricedAssemblyType(HourlyPricedAssemblyType hourlyPricedAssemblyType) {
        this.hourlyPricedAssemblyType = hourlyPricedAssemblyType;
    }

    public Integer getMunkaOrakSzama() {
        return munkaOrakSzama;
    }

    public void setMunkaOrakSzama(Integer munkaOrakSzama) {
        this.munkaOrakSzama = munkaOrakSzama;
    }

    public static void setOradij(Integer ujOraDij){
        oradij = ujOraDij;
    }

    @Override
    public String toString() {
        return "OradijasJavitas{" +
                "oradijasJavitasTipus=" + hourlyPricedAssemblyType +
                ", munkaOrakSzama=" + munkaOrakSzama +
                ", id=" + id +
                ", felhasznaltAlkatreszek=" + felhasznaltAlkatreszek +
                '}';
    }
}
