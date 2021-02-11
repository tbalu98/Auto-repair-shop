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
    private String leiras;

    @Column(name = "garancia_idotartama_")
    private Integer garanciaIdotartama;

    @OneToMany(mappedBy = "hourlyPricedAssemblyType", fetch = FetchType.LAZY)
    private List<HourlyPricedAssembly> javitasok = new ArrayList<>();

    public HourlyPricedAssemblyType(){}

    public HourlyPricedAssemblyType(String leiras, Integer garanciaIdotartama) {

        this.leiras = leiras;
        this.garanciaIdotartama = garanciaIdotartama;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public Integer getGaranciaIdotartama() {
        return garanciaIdotartama;
    }

    public void setGaranciaIdotartama(Integer garanciaIdotartama) {
        this.garanciaIdotartama = garanciaIdotartama;
    }

    public List<HourlyPricedAssembly> getJavitasok() {
        return javitasok;
    }

    public void setJavitasok(List<HourlyPricedAssembly> javitasok) {
        this.javitasok = javitasok;
    }
}
