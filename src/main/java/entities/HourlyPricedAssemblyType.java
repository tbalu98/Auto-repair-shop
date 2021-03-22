package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "javitas_tipus")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.DiscriminatorFormula(
        "case when fix_ar is null then 'ODJT' else 'FAJT' end"
)
@DiscriminatorValue("ODJT")
public class HourlyPricedAssemblyType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "leiras")
    private String decription;

    @Column(name = "garancia_idotartama_")
    private Integer guarantee;

    @OneToMany(mappedBy = "hourlyPricedAssemblyType", fetch = FetchType.LAZY)
    private List<HourlyPricedAssembly> assemblies = new ArrayList<>();

    public HourlyPricedAssemblyType(){}

    public HourlyPricedAssemblyType(String decription, Integer guarantee) {

        this.decription = decription;
        this.guarantee = guarantee;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public List<HourlyPricedAssembly> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(List<HourlyPricedAssembly> assemblies) {
        this.assemblies = assemblies;
    }
}
