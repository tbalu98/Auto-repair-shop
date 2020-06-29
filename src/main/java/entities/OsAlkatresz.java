package entities;

import javax.persistence.*;

@Entity(name = "alkatresz")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.DiscriminatorFormula(
        "case when garancia_idotartama is not null then 'GA' else 'A' end"
)
public abstract class OsAlkatresz  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nev")
    private String nev;

  /*  @Column(name = "cikkszam")
    private Integer cikkszam;
*/
    @Column(name = "ar")
    private Integer ar;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "javitas_id")
    private Javitas javitas;

    public OsAlkatresz(String nev, Integer cikkszam, Integer ar, Javitas javitas) {
        this.nev = nev;
        this.cikkszam = cikkszam;
        this.ar = ar;
        this.javitas = javitas;
    }
*/
    public OsAlkatresz(){}

    public OsAlkatresz(String nev, Integer ar) {
        this.nev = nev;
        this.ar = ar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

  /*  public Integer getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(Integer cikkszam) {
        this.cikkszam = cikkszam;
    }
*/
    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    /*
    public Javitas getJavitas() {
        return javitas;
    }

    public void setJavitas(Javitas javitas) {
        this.javitas = javitas;
    }*/
}
