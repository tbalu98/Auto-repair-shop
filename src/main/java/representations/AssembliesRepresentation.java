package representations;

import entities.Assembly;
import entities.FixPricedAssembly;
import entities.HourlyPricedAssembly;

import java.util.ArrayList;
import java.util.List;

public class AssembliesRepresentation {

    public static AssembliesRepresentation of(Assembly assembly){
        if(assembly instanceof HourlyPricedAssembly){
            return new AssembliesRepresentation((HourlyPricedAssembly) assembly);
        }else if(assembly instanceof FixPricedAssembly){
            return new AssembliesRepresentation((FixPricedAssembly) assembly);
        }
        else {
            return null;
        }
    }

    public static List<AssembliesRepresentation> of(List<Assembly> javitasok){

        List<AssembliesRepresentation> javitasokNezetek = new ArrayList<>();

        for(Assembly assembly : javitasok){

            if(assembly instanceof HourlyPricedAssembly){
                javitasokNezetek.add(new AssembliesRepresentation((HourlyPricedAssembly) assembly));
            }
            else if(assembly instanceof FixPricedAssembly){

                javitasokNezetek.add(new AssembliesRepresentation((FixPricedAssembly) assembly));

            }

        }

        return javitasokNezetek;

    }

    private Integer id;
    private String leiras;
    private Integer ar;
    private Integer javitasGaranciaIdotartama;
    private Integer munkaorakSzama;
    private Assembly assembly;

    public AssembliesRepresentation(String leiras, Integer ar, Integer javitasGaranciaIdotartama) {
        this.leiras = leiras;
        this.ar = ar;
        this.javitasGaranciaIdotartama = javitasGaranciaIdotartama;
    }

    public AssembliesRepresentation(FixPricedAssembly fixPricedAssembly){
        this.assembly = fixPricedAssembly;
        this.id = fixPricedAssembly.getId();
        this.ar = fixPricedAssembly.getFixPricedAssemblyType().getAr();
        this.leiras = fixPricedAssembly.getFixPricedAssemblyType().getLeiras();
        this.javitasGaranciaIdotartama = fixPricedAssembly.getFixPricedAssemblyType().getGaranciaIdotartama();


    }

    public AssembliesRepresentation(HourlyPricedAssembly hourlyPricedAssembly){
        this.assembly = hourlyPricedAssembly;
        this.id = hourlyPricedAssembly.getId();
        this.javitasGaranciaIdotartama = hourlyPricedAssembly.getHourlyPricedAssemblyType().getGaranciaIdotartama();
        this.leiras = hourlyPricedAssembly.getHourlyPricedAssemblyType().getLeiras();
        this.munkaorakSzama = hourlyPricedAssembly.getMunkaOrakSzama();
        this.ar = hourlyPricedAssembly.aratSzamol();

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

    public Assembly getAssembly() {
        return assembly;
    }

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }
}
