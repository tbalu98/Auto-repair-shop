package entities;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public abstract class Szamla {

    protected List<araVan> javitasok = new ArrayList<>();

    Integer aratSzamol(){
        Integer res;
        return this.javitasok.stream().map(c -> c.aratSzamol()).reduce((a,b) -> a +b).get();
    }

}
