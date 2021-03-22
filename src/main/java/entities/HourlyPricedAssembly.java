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
    private Integer numofWorkingHours;

    public HourlyPricedAssembly(){}

    public HourlyPricedAssembly(Repair repair, HourlyPricedAssemblyType hourlyPricedAssemblyType, Integer numofWorkingHours) {
        this.repair = repair;
        this.hourlyPricedAssemblyType = hourlyPricedAssemblyType;
        this.numofWorkingHours = numofWorkingHours;
    }

    @Override
    public Integer computePrice() {

        Integer price = this.priceofParts();
        price += this.priceOfWork();
        return price;
    }

    private Integer priceOfWork(){
        if(numofWorkingHours !=null)
        return this.numofWorkingHours * HourlyPricedAssembly.oradij;
        else
            return 0;
    }

    public HourlyPricedAssemblyType getHourlyPricedAssemblyType() {
        return hourlyPricedAssemblyType;
    }

    public void setHourlyPricedAssemblyType(HourlyPricedAssemblyType hourlyPricedAssemblyType) {
        this.hourlyPricedAssemblyType = hourlyPricedAssemblyType;
    }

    public Integer getNumofWorkingHours() {
        return numofWorkingHours;
    }

    public void setNumofWorkingHours(Integer numofWorkingHours) {
        this.numofWorkingHours = numofWorkingHours;
    }

    public static void setOradij(Integer ujOraDij){
        oradij = ujOraDij;
    }

    @Override
    public String toString() {
        return "HourlyPricedAssembly{" +
                "hourlyPricedAssemblyType=" + hourlyPricedAssemblyType +
                ", numofWorkingHours=" + numofWorkingHours +
                ", id=" + id +
                ", usedParts=" + usedParts.size() +
                ", repair=" + repair +
                '}';
    }
}
