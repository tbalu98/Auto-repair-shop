package entities;

import javax.persistence.*;

/**
 * Contains the technical parameters of the car.
 *
 * */

@Entity(name = "gepjarmuparameter")
public class CarParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipus")
    private String type;

    @Column(name = "motorterfogat")
    private Integer engineVolume;

    @Column(name = "teljesitmeny")
    private Integer power;

/*
    @OneToMany(mappedBy = "gepjarmuparameter", fetch = FetchType.LAZY)
    private List<Gepjarmu> gepjarmuvek = new ArrayList<>();
*/
    public CarParameter(){}

    public CarParameter(String type, Integer engineVolume, Integer power) {
        this.type = type;
        this.engineVolume = engineVolume;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Integer engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
/*
    public List<Gepjarmu> getGepjarmuvek() {
        return gepjarmuvek;
    }

    public void setGepjarmuvek(List<Gepjarmu> gepjarmuvek) {
        this.gepjarmuvek = gepjarmuvek;
    }
*/

    @Override
    public String toString() {
        return "CarParameter{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", engineVolume=" + engineVolume +
                ", power=" + power +
                '}';
    }
}
