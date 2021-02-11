package representations;

import entities.FixPricedAssemblyType;
import entities.HourlyPricedAssemblyType;

import java.util.ArrayList;
import java.util.List;

public class AssemblyTypesRepresentation {


    public static List<AssemblyTypesRepresentation> of(List<HourlyPricedAssemblyType> javitasTipusok){

        List<AssemblyTypesRepresentation> javitasTipusokNezete = new ArrayList<>();
        for(HourlyPricedAssemblyType javitasTipus: javitasTipusok){

            if(javitasTipus instanceof FixPricedAssemblyType){
                javitasTipusokNezete.add(new AssemblyTypesRepresentation((FixPricedAssemblyType) javitasTipus));
            }else if(javitasTipus instanceof HourlyPricedAssemblyType){
                javitasTipusokNezete.add(new AssemblyTypesRepresentation(javitasTipus));
            }


        }
    return javitasTipusokNezete;

    }

    private Integer id;
    private String leiras;
    private Integer garanciaIdotartama;
    private Integer fixar;
    private HourlyPricedAssemblyType hourlyPricedAssemblyType;

    public AssemblyTypesRepresentation(FixPricedAssemblyType fixPricedAssemblyType){
        this.hourlyPricedAssemblyType = fixPricedAssemblyType;
        this.id = fixPricedAssemblyType.getId();
        this.leiras = fixPricedAssemblyType.getLeiras();
        this.fixar = fixPricedAssemblyType.getAr();
        this.garanciaIdotartama = fixPricedAssemblyType.getGaranciaIdotartama();

    }

    public AssemblyTypesRepresentation(HourlyPricedAssemblyType hourlyPricedAssemblyType){

        this.id = hourlyPricedAssemblyType.getId();
        this.leiras = hourlyPricedAssemblyType.getLeiras();
        this.garanciaIdotartama = hourlyPricedAssemblyType.getGaranciaIdotartama();

    }

    public AssemblyTypesRepresentation(){}

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
