package entities;


import javax.persistence.*;

@Entity(name = "ugyfel")
public class Ugyfel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "telefonszam")
    private String telefonszam;

    @Column(name = "nev")
    private String nev;

    @Column(name = "lakcim")
    private String lakcim;

    public Ugyfel( String nev, String telefonszam, String lakcim) {
        this.telefonszam = telefonszam;
        this.nev = nev;
        this.lakcim = lakcim;
    }

    public Ugyfel(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    @Override
    public String toString() {
        return "Ugyfel{" +
                "id=" + id +
                ", telefonszam='" + telefonszam + '\'' +
                ", nev='" + nev + '\'' +
                ", lakcim='" + lakcim + '\'' +
                '}';
    }
}