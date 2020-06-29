package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "gepjarmuparameter")
public class Gepjarmuparameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipus")
    private String tipus;

    @Column(name = "motorterfogat")
    private Integer motorterfogat;

    @Column(name = "teljesitmeny")
    private Integer teljesitmeny;

/*
    @OneToMany(mappedBy = "gepjarmuparameter", fetch = FetchType.LAZY)
    private List<Gepjarmu> gepjarmuvek = new ArrayList<>();
*/
    public Gepjarmuparameter(){}

    public Gepjarmuparameter(String tipus, Integer motorterfogat, Integer teljesitmeny) {
        this.tipus = tipus;
        this.motorterfogat = motorterfogat;
        this.teljesitmeny = teljesitmeny;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Integer getMotorterfogat() {
        return motorterfogat;
    }

    public void setMotorterfogat(Integer motorterfogat) {
        this.motorterfogat = motorterfogat;
    }

    public Integer getTeljesitmeny() {
        return teljesitmeny;
    }

    public void setTeljesitmeny(Integer teljesitmeny) {
        this.teljesitmeny = teljesitmeny;
    }
/*
    public List<Gepjarmu> getGepjarmuvek() {
        return gepjarmuvek;
    }

    public void setGepjarmuvek(List<Gepjarmu> gepjarmuvek) {
        this.gepjarmuvek = gepjarmuvek;
    }
*/
    @Override
    public String toString() {
        return "Gepjarmuparameter{" +
                "id=" + id +
                ", tipus='" + tipus + '\'' +
                ", motorterfogat=" + motorterfogat +
                ", teljesitmeny=" + teljesitmeny +
                '}';
    }
}
