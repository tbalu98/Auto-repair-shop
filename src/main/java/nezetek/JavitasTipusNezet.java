package nezetek;

import entities.FixAruJavitas;
import entities.FixAruJavitasTipus;
import entities.OradijasJavitas;
import entities.OradijasJavitasTipus;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class JavitasTipusNezet {


    public static List<JavitasTipusNezet> of(List<OradijasJavitasTipus> javitasTipusok){

        List<JavitasTipusNezet> javitasTipusokNezete = new ArrayList<>();
        for(OradijasJavitasTipus javitasTipus: javitasTipusok){

            if(javitasTipus instanceof FixAruJavitasTipus){
                javitasTipusokNezete.add(new JavitasTipusNezet((FixAruJavitasTipus) javitasTipus));
            }else if(javitasTipus instanceof OradijasJavitasTipus){
                javitasTipusokNezete.add(new JavitasTipusNezet(javitasTipus));
            }


        }
    return javitasTipusokNezete;

    }

    private Integer id;
    private String leiras;
    private Integer garanciaIdotartama;
    private Integer fixar;
    private OradijasJavitasTipus oradijasJavitasTipus;

    public JavitasTipusNezet(FixAruJavitasTipus fixAruJavitasTipus){
        this.oradijasJavitasTipus = fixAruJavitasTipus;
        this.id = fixAruJavitasTipus.getId();
        this.leiras = fixAruJavitasTipus.getLeiras();
        this.fixar = fixAruJavitasTipus.getAr();
        this.garanciaIdotartama = fixAruJavitasTipus.getGaranciaIdotartama();

    }

    public JavitasTipusNezet(OradijasJavitasTipus oradijasJavitasTipus){

        this.id = oradijasJavitasTipus.getId();
        this.leiras = oradijasJavitasTipus.getLeiras();
        this.garanciaIdotartama = oradijasJavitasTipus.getGaranciaIdotartama();

    }

    public JavitasTipusNezet(){}

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

    public Integer getFixar() {
        return fixar;
    }

    public void setFixar(Integer fixar) {
        this.fixar = fixar;
    }
}
