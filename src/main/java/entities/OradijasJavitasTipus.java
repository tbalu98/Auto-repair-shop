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
public class OradijasJavitasTipus  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "leiras")
    private String leiras;

    @Column(name = "garancia_idotartama_")
    private Integer garanciaIdotartama;

    @OneToMany(mappedBy = "oradijasJavitasTipus", fetch = FetchType.LAZY)
    private List<OradijasJavitas> javitasok = new ArrayList<>();

    public OradijasJavitasTipus(){}

    public OradijasJavitasTipus(String leiras, Integer garanciaIdotartama) {

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

    public List<OradijasJavitas> getJavitasok() {
        return javitasok;
    }

    public void setJavitasok(List<OradijasJavitas> javitasok) {
        this.javitasok = javitasok;
    }
}
