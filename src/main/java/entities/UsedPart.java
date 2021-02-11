package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "felhasznalt_alkatresz")
public class UsedPart implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //nem volt semmijen cascade
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "alkatresz_id")
    private Part part;

  /*  @Column(name = "cikkszam")
    private Integer cikkszam;
*/
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "javitas_id")
    private Assembly assembly;

    public UsedPart(){}

  /*  public FelhasznaltAlkatresz(Integer cikkszam, Alkatresz alkatresz, Javitas javitas) {
       // this.cikkszam = cikkszam;
        this.alkatresz = alkatresz;
        this.javitas = javitas;
    }
*/
    public UsedPart(Part part, Assembly assembly){

        this.part = part;
        this.assembly = assembly;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getCikkszam() {
        return this.part.getCikkszam();
    }

    public void setCikkszam(Integer cikkszam) {
        this.part.setCikkszam(cikkszam);
    }

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }

    public Integer getAr() {
        return this.part.getAr();
    }

    @Override
    public String toString() {
        return "FelhasznaltAlkatresz{" +
                "id=" + id +
                ", alkatresz=" + part.getNev() +
                ", javitas=" + assembly.getId() +
                '}';
    }
}
