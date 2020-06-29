package nezetek;

import entities.FixAruJavitas;
import entities.Javitas;
import entities.OradijasJavitas;

import java.util.ArrayList;
import java.util.List;

public class JavitasokNezet {

    public static JavitasokNezet of(Javitas javitas){
        if(javitas instanceof OradijasJavitas){
            return new JavitasokNezet((OradijasJavitas) javitas);
        }else if(javitas instanceof FixAruJavitas){
            return new JavitasokNezet((FixAruJavitas)javitas);
        }
        else {
            return null;
        }
    }

    public static List<JavitasokNezet> of(List<Javitas> javitasok){

        List<JavitasokNezet> javitasokNezetek = new ArrayList<>();

        for(Javitas javitas: javitasok){

            if(javitas instanceof OradijasJavitas){
                javitasokNezetek.add(new JavitasokNezet((OradijasJavitas) javitas));
            }
            else if(javitas instanceof FixAruJavitas){

                javitasokNezetek.add(new JavitasokNezet((FixAruJavitas)javitas));

            }

        }

        return javitasokNezetek;

    }

    private Integer id;
    private String leiras;
    private Integer ar;
    private Integer javitasGaranciaIdotartama;
    private Integer munkaorakSzama;
    private Javitas javitas;

    public JavitasokNezet(String leiras, Integer ar, Integer javitasGaranciaIdotartama) {
        this.leiras = leiras;
        this.ar = ar;
        this.javitasGaranciaIdotartama = javitasGaranciaIdotartama;
    }

    public JavitasokNezet(FixAruJavitas fixAruJavitas){
        this.javitas = fixAruJavitas;
        this.id = fixAruJavitas.getId();
        this.ar = fixAruJavitas.getFixAruJavitasTipus().getAr();
        this.leiras = fixAruJavitas.getFixAruJavitasTipus().getLeiras();
        this.javitasGaranciaIdotartama = fixAruJavitas.getFixAruJavitasTipus().getGaranciaIdotartama();


    }

    public JavitasokNezet(OradijasJavitas oradijasJavitas){
        this.javitas = oradijasJavitas;
        this.id = oradijasJavitas.getId();
        this.javitasGaranciaIdotartama = oradijasJavitas.getOradijasJavitasTipus().getGaranciaIdotartama();
        this.leiras = oradijasJavitas.getOradijasJavitasTipus().getLeiras();
        this.munkaorakSzama = oradijasJavitas.getMunkaOrakSzama();
        this.ar = oradijasJavitas.aratSzamol();

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

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getJavitasGaranciaIdotartama() {
        return javitasGaranciaIdotartama;
    }

    public void setJavitasGaranciaIdotartama(Integer javitasGaranciaIdotartama) {
        this.javitasGaranciaIdotartama = javitasGaranciaIdotartama;
    }

    public Integer getMunkaorakSzama() {
        return munkaorakSzama;
    }

    public void setMunkaorakSzama(Integer munkaorakSzama) {
        this.munkaorakSzama = munkaorakSzama;
    }

    public Javitas getJavitas() {
        return javitas;
    }

    public void setJavitas(Javitas javitas) {
        this.javitas = javitas;
    }
}
