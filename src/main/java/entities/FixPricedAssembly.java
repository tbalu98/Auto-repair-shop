package entities;

import javax.persistence.*;

/**
 * This class represents assemblies that has fixed price. The technical specification of the
 * assembly is a {@FixedPriceAssemblyType}.
 *
 * */


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


    /**
     * Adds the price of the parts used in this ficed priced assembly and the fixed price of
     * the assembly.
     * @return Integer price of the {@FixedPricedAssembly}
     * */

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
