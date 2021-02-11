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

    public FixPricedAssemblyType(String leiras, Integer garanciaIdotartama, Integer fixAr) {
        super(leiras,garanciaIdotartama);
        this.fixAr = fixAr;
    }

    public Integer getAr() {
        return fixAr;
    }

    public void setAr(Integer ar) {
        this.fixAr = ar;
    }
}
