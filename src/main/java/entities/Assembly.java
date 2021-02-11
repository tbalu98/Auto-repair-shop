package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "javitas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.DiscriminatorFormula(
        "case when munkaorak_szama is not null then 'ODJ' else 'FAJ' end"
)
public abstract class Assembly implements hasPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    //javitas volt
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "assembly", fetch = FetchType.EAGER)
    protected List<UsedPart> felhasznaltAlkatreszek = new ArrayList<>();

  /*  @ManyToMany()
    @JoinTable(
            name = "dolgozott_rajta",
            joinColumns = @JoinColumn(name = "javitas_id"),
            inverseJoinColumns = @JoinColumn(name = "szerelo_id")

    )
    protected List<Szerelo> szerelok = new ArrayList<>();
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "szereles_id")
    protected Repair repair;

    //---------------
  /*
    private List<Alkatresz> alkatreszek;
    private List<GarancialisAlkatresz> garancialisAlkatreszek;
*/
    public Assembly() {}

    public Assembly(String leiras, List<UsedPart> felhasznaltAlkatreszek, Repair repair) {

        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
        this.repair = repair;
    }

    public Assembly(Repair repair){
        this.repair = repair;

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
/*
    public List<Szerelo> getSzerelok() {
        return szerelok;
    }

    public void setSzerelok(List<Szerelo> szerelok) {
        this.szerelok = szerelok;
    }
*/
    public List<UsedPart> getFelhasznaltAlkatreszek() {
        return felhasznaltAlkatreszek;
    }

    public void setFelhasznaltAlkatreszek(List<UsedPart> felhasznaltAlkatreszek) {
        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }


    public abstract Integer aratSzamol();

    protected Integer alkatreszekAra(){

        Integer ar = new Integer(0);

        for(UsedPart alkatresz : this.getFelhasznaltAlkatreszek()){

            ar += alkatresz.getAr();

        }
        return ar;

    }

    public List<Object> getFelhasznaltAlkatreszekIdei(){

        List<Object> idk = new ArrayList<>();
        for(UsedPart usedPart : this.felhasznaltAlkatreszek){

            idk.add((Object) usedPart.getId());

        }
        return idk;
    }
}
