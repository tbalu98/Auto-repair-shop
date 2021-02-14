package entities;

import java.util.ArrayList;
import java.util.List;

public class PartRepresentation {


    public static List<PartRepresentation> of(List<Part> alkatreszek){

        List<PartRepresentation> alkatreszNezetek = new ArrayList<>();

        for(Part part : alkatreszek){

            alkatreszNezetek.add(new PartRepresentation(part));

        }
        return alkatreszNezetek;

    }

    private String alkatreszNev;

    private Integer alkatreszAr;

    private Integer alkatreszGaranciaIdotartama;

    //Uj
    private Integer alkatreszCikkszam;

    private Part part;
    public PartRepresentation(String alkatreszNev, Integer alkatreszAr, Integer alkatreszGaranciaIdotartama) {
        this.alkatreszNev = alkatreszNev;
        this.alkatreszAr = alkatreszAr;
        this.alkatreszGaranciaIdotartama = alkatreszGaranciaIdotartama;
    }

    //Uj


    public PartRepresentation(String alkatreszNev, Integer alkatreszAr, Integer alkatreszGaranciaIdotartama, Integer alkatreszCikkszam, Part part) {
        this.alkatreszNev = alkatreszNev;
        this.alkatreszAr = alkatreszAr;
        this.alkatreszGaranciaIdotartama = alkatreszGaranciaIdotartama;
        this.alkatreszCikkszam = alkatreszCikkszam;
        this.part = part;
    }

    //Modositottam
    public PartRepresentation(Part part) {
        this.alkatreszNev = part.getNev();
        this.alkatreszAr = part.getAr();
        this.alkatreszGaranciaIdotartama = part.getGaranciaIdotartama();
        this.part = part;
        //Hozzaadtam
        this.alkatreszCikkszam = part.getCikkszam();
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

        return this.part.getId();

    }

    public Part getPart() {
        return part;
    }

    //Uj
    public Integer getAlkatreszCikkszam() {
        return alkatreszCikkszam;
    }

    public void setAlkatreszCikkszam(Integer alkatreszCikkszam) {
        this.alkatreszCikkszam = alkatreszCikkszam;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
