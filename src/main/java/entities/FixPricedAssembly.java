package entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FAJ")
public class FixPricedAssembly extends Assembly {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "javitas_tipus_id")
    private FixPricedAssemblyType fixPricedAssemblyType;


    public FixPricedAssembly(){}

    public FixPricedAssembly(Repair repair, FixPricedAssemblyType fixPricedAssemblyType) {
        super(repair);
        this.fixPricedAssemblyType = fixPricedAssemblyType;
    }


    @Override
    public Integer computePrice() {
         return this.priceofParts() + this.getFixPricedAssemblyType().getPrice();
    }



    public FixPricedAssembly(FixPricedAssemblyType fixPricedAssemblyType) {
        this.fixPricedAssemblyType = fixPricedAssemblyType;
    }



    public FixPricedAssemblyType getFixPricedAssemblyType() {
        return fixPricedAssemblyType;
    }

    public void setFixPricedAssemblyType(FixPricedAssemblyType fixPricedAssemblyType) {
        this.fixPricedAssemblyType = fixPricedAssemblyType;
    }
}
