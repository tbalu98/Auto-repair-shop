package entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;


@Entity
@DiscriminatorValue("ODJ")
public class OradijasJavitas extends Javitas {

    private static Integer oradij = 2000;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "javitas_tipus_id")
    private OradijasJavitasTipus oradijasJavitasTipus;

    @Column(name = "munkaorak_szama")
    private Integer munkaOrakSzama;

    public OradijasJavitas(){}

    public OradijasJavitas(Szereles szereles, OradijasJavitasTipus oradijasJavitasTipus, Integer munkaOrakSzama) {
        this.szereles = szereles;
        this.oradijasJavitasTipus = oradijasJavitasTipus;
        this.munkaOrakSzama = munkaOrakSzama;
    }

    @Override
    public Integer aratSzamol() {

        Integer ar = this.alkatreszekAra();
        ar += this.munkaKoltsege();
        return ar;
    }

    private Integer munkaKoltsege(){
        if(munkaOrakSzama!=null)
        return this.munkaOrakSzama*OradijasJavitas.oradij;
        else
            return 0;
    }

    public OradijasJavitasTipus getOradijasJavitasTipus() {
        return oradijasJavitasTipus;
    }

    public void setOradijasJavitasTipus(OradijasJavitasTipus oradijasJavitasTipus) {
        this.oradijasJavitasTipus = oradijasJavitasTipus;
    }

    public Integer getMunkaOrakSzama() {
        return munkaOrakSzama;
    }

    public void setMunkaOrakSzama(Integer munkaOrakSzama) {
        this.munkaOrakSzama = munkaOrakSzama;
    }

    public static void setOradij(Integer ujOraDij){
        oradij = ujOraDij;
    }

    @Override
    public String toString() {
        return "OradijasJavitas{" +
                "oradijasJavitasTipus=" + oradijasJavitasTipus +
                ", munkaOrakSzama=" + munkaOrakSzama +
                ", id=" + id +
                ", felhasznaltAlkatreszek=" + felhasznaltAlkatreszek +
                '}';
    }
}
