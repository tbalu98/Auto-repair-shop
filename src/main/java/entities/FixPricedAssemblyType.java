package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FAJT")
public class FixPricedAssemblyType extends HourlyPricedAssemblyType {

    @Column(name = "fix_ar")
    private Integer fixAr;
    
    public FixPricedAssemblyType(){}

    public FixPricedAssemblyType(String description, Integer guarantee, Integer fixedPrice) {
        super(description,guarantee);
        this.fixAr = fixedPrice;
    }

    public Integer getPrice() {
        return fixAr;
    }

    public void setAr(Integer price) {
        this.fixAr = price;
    }
}
