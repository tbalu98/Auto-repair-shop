package entities;

import java.util.ArrayList;
import java.util.List;

public class AlkatreszNezet {


    public static List<AlkatreszNezet> of(List<Alkatresz> alkatreszek){

        List<AlkatreszNezet> alkatreszNezetek = new ArrayList<>();

        for(Alkatresz alkatresz: alkatreszek){

            alkatreszNezetek.add(new AlkatreszNezet(alkatresz));

        }
        return alkatreszNezetek;

    }

    private String alkatreszNev;

    private Integer alkatreszAr;

    private Integer alkatreszGaranciaIdotartama;

    private Alkatresz alkatresz;
    public AlkatreszNezet(String alkatreszNev, Integer alkatreszAr, Integer alkatreszGaranciaIdotartama) {
        this.alkatreszNev = alkatreszNev;
        this.alkatreszAr = alkatreszAr;
        this.alkatreszGaranciaIdotartama = alkatreszGaranciaIdotartama;
    }

    public AlkatreszNezet(Alkatresz alkatresz) {
        this.alkatreszNev = alkatresz.getNev();
        this.alkatreszAr = alkatresz.getAr();
        this.alkatreszGaranciaIdotartama = alkatresz.getGaranciaIdotartama();
        this.alkatresz = alkatresz;
    }

    public String getAlkatreszNev() {
        return alkatreszNev;
    }

    public void setAlkatreszNev(String alkatreszNev) {
        this.alkatreszNev = alkatreszNev;
    }

    public Integer getAlkatreszAr() {
        return alkatreszAr;
    }

    public void setAlkatreszAr(Integer alkatreszAr) {
        this.alkatreszAr = alkatreszAr;
    }

    public Integer getAlkatreszGaranciaIdotartama() {
        return alkatreszGaranciaIdotartama;
    }

    public void setAlkatreszGaranciaIdotartama(Integer alkatreszGaranciaIdotartama) {
        this.alkatreszGaranciaIdotartama = alkatreszGaranciaIdotartama;
    }

    public Integer getId(){

        return this.alkatresz.getId();

    }

    public Alkatresz getAlkatresz() {
        return alkatresz;
    }
}
