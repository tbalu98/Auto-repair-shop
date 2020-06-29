package filters;

public class JavitasTipusFilter {

    private Integer id;
    private String leiras;
    private Integer garanciIdotartama;
    private Integer fixar;



    public JavitasTipusFilter(Integer id, String leiras, Integer garanciIdotartama, Integer fixar) {
        this.id = id;
        this.leiras = leiras;
        this.garanciIdotartama = garanciIdotartama;
        this.fixar = fixar;
    }

    public JavitasTipusFilter(){}

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

    public Integer getGaranciIdotartama() {
        return garanciIdotartama;
    }

    public void setGaranciIdotartama(Integer garanciIdotartama) {
        this.garanciIdotartama = garanciIdotartama;
    }

    public Integer getFixar() {
        return fixar;
    }

    public void setFixar(Integer fixar) {
        this.fixar = fixar;
    }
}
