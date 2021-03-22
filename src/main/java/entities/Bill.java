package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Bill {

    protected List<hasPrice> javitasok = new ArrayList<>();

    Integer aratSzamol(){
        Integer res;
        return this.javitasok.stream().map(c -> c.computePrice()).reduce((a, b) -> a +b).get();
    }

}
