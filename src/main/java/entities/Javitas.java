package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.DiscriminatorFormula(
        "case when munkaorak_szama is not null then 'ODJ' else 'FAJ' end"
)
public abstract class Javitas  implements araVan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "javitas", fetch = FetchType.EAGER)
    protected List<FelhasznaltAlkatresz> felhasznaltAlkatreszek = new ArrayList<>();

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
    protected Szereles szereles;

    //---------------
  /*
    private List<Alkatresz> alkatreszek;
    private List<GarancialisAlkatresz> garancialisAlkatreszek;
*/
    public Javitas() {}

    public Javitas(String leiras, List<FelhasznaltAlkatresz> felhasznaltAlkatreszek, Szereles szereles) {

        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
        this.szereles = szereles;
    }

    public Javitas( Szereles szereles){
        this.szereles = szereles;

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
    public List<FelhasznaltAlkatresz> getFelhasznaltAlkatreszek() {
        return felhasznaltAlkatreszek;
    }

    public void setFelhasznaltAlkatreszek(List<FelhasznaltAlkatresz> felhasznaltAlkatreszek) {
        this.felhasznaltAlkatreszek = felhasznaltAlkatreszek;
    }

    public Szereles getSzereles() {
        return szereles;
    }

    public void setSzereles(Szereles szereles) {
        this.szereles = szereles;
    }


    public abstract Integer aratSzamol();

    protected Integer alkatreszekAra(){

        Integer ar = new Integer(0);

        for(FelhasznaltAlkatresz alkatresz : this.getFelhasznaltAlkatreszek()){

            ar += alkatresz.getAr();

        }
        return ar;

    }

    public List<Object> getFelhasznaltAlkatreszekIdei(){

        List<Object> idk = new ArrayList<>();
        for(FelhasznaltAlkatresz felhasznaltAlkatresz: this.felhasznaltAlkatreszek){

            idk.add((Object)felhasznaltAlkatresz.getId());

        }
        return idk;
    }
}
