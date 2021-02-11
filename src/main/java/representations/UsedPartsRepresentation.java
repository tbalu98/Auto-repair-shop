package representations;

import entities.UsedPart;

import java.util.ArrayList;
import java.util.List;

public class UsedPartsRepresentation {

    public static List<UsedPartsRepresentation> of(List<UsedPart> felhasznaltAlkatreszek){

        List<UsedPartsRepresentation> felhasznaltAlkatreszekNezetek = new ArrayList<>();

        for(UsedPart usedPart : felhasznaltAlkatreszek){
            felhasznaltAlkatreszekNezetek.add(new UsedPartsRepresentation(usedPart));


        }

        return felhasznaltAlkatreszekNezetek;
    }

    private Integer id;
    private String nev;
    private Integer ar;
    private Integer garanciaIdotartama;
    private Integer cikkszam;
    private UsedPart usedPart;


    public UsedPartsRepresentation(UsedPart usedPart){

        this.usedPart = usedPart;
        this.id = usedPart.getId();
        // DEMETER TORVENYENEK MEGSERTESE
        this.nev = usedPart.getPart().getNev();
        this.ar = usedPart.getPart().getAr();
        this.garanciaIdotartama = usedPart.getPart().getGaranciaIdotartama();
        this.cikkszam = usedPart.getCikkszam();
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

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getGaranciaIdotartama() {
        return garanciaIdotartama;
    }

    public void setGaranciaIdotartama(Integer garanciaIdotartama) {
        this.garanciaIdotartama = garanciaIdotartama;
    }

    public Integer getCikkszam() {
        return cikkszam;
    }

    public void setCikkszam(Integer cikkszam) {
        this.cikkszam = cikkszam;
    }

    public UsedPart getUsedPart() {
        return usedPart;
    }

    public void setUsedPart(UsedPart usedPart) {
        this.usedPart = usedPart;
    }

    @Override
    public String toString() {
        return "FelhasznaltAlkatreszekNezet{" +
                "id=" + id +
                ", nev='" + nev + '\'' +
                ", ar=" + ar +
                ", garanciaIdotartama=" + garanciaIdotartama +
                ", cikkszam=" + cikkszam +
                ", felhasznaltAlkatresz=" + usedPart +
                '}';
    }
}
