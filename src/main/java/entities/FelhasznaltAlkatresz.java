package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "felhasznalt_alkatresz")
public class FelhasznaltAlkatresz implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //nem volt semmijen cascade
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "alkatresz_id")
    private Alkatresz alkatresz;

  /*  @Column(name = "cikkszam")
    private Integer cikkszam;
*/
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "javitas_id")
    private Javitas javitas;

    public FelhasznaltAlkatresz(){}

  /*  public FelhasznaltAlkatresz(Integer cikkszam, Alkatresz alkatresz, Javitas javitas) {
       // this.cikkszam = cikkszam;
        this.alkatresz = alkatresz;
        this.javitas = javitas;
    }
*/
    public FelhasznaltAlkatresz(Alkatresz alkatresz, Javitas javitas){

        this.alkatresz = alkatresz;
        this.javitas = javitas;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alkatresz getAlkatresz() {
        return alkatresz;
    }

    public void setAlkatresz(Alkatresz alkatresz) {
        this.alkatresz = alkatresz;
    }

    public Integer getCikkszam() {
        return this.alkatresz.getCikkszam();
    }

    public void setCikkszam(Integer cikkszam) {
        this.alkatresz.setCikkszam(cikkszam);
    }

    public Javitas getJavitas() {
        return javitas;
    }

    public void setJavitas(Javitas javitas) {
        this.javitas = javitas;
    }

    public Integer getAr() {
        return this.alkatresz.getAr();
    }

    @Override
    public String toString() {
        return "FelhasznaltAlkatresz{" +
                "id=" + id +
                ", alkatresz=" + alkatresz.getNev() +
                ", javitas=" + javitas.getId() +
                '}';
    }
}
