package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "alkatresz")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nev")
    private String name;

    @Column(name = "ar")
    private Integer price;

    @Column(name = "garancia_idotartama")
    private Integer guarantee;

    @Column(name ="cikkszam")
    private Integer partCode;
    //persistbol merge
    //alkatresz volt
    @OneToMany(/*cascade = CascadeType.MERGE, */mappedBy = "part", fetch = FetchType.LAZY)
    private List<UsedPart> usedParts = new ArrayList<>();

    public Part() {}

    public Part(String name, Integer price, Integer guarantee) {
        this.name = name;
        this.price = price;
        this.guarantee = guarantee;
    }


    public Part(String name, Integer price, Integer guarantee, Integer partCode) {
        this.name = name;
        this.price = price;
        this.guarantee = guarantee;
        this.partCode = partCode;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public List<UsedPart> getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(List<UsedPart> usedParts) {
        this.usedParts = usedParts;
    }

    public Integer getPartCode() {
        return partCode;
    }

    public void setPartCode(Integer partCode) {
        this.partCode = partCode;
    }
}
