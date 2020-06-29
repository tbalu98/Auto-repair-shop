package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("FAJ")
public class FixAruJavitas extends Javitas {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "javitas_tipus_id")
    private FixAruJavitasTipus fixAruJavitasTipus;


    public FixAruJavitas(){}

    public FixAruJavitas(Szereles szereles, FixAruJavitasTipus fixAruJavitasTipus) {
        super(szereles);
        this.fixAruJavitasTipus = fixAruJavitasTipus;
    }

    /* TODO Demeter törvénye*/
    @Override
    public Integer aratSzamol() {
         return this.alkatreszekAra() + this.getFixAruJavitasTipus().getAr();
    }



    public FixAruJavitas(FixAruJavitasTipus fixAruJavitasTipus) {
        this.fixAruJavitasTipus = fixAruJavitasTipus;
    }



    public FixAruJavitasTipus getFixAruJavitasTipus() {
        return fixAruJavitasTipus;
    }

    public void setFixAruJavitasTipus(FixAruJavitasTipus fixAruJavitasTipus) {
        this.fixAruJavitasTipus = fixAruJavitasTipus;
    }
}
