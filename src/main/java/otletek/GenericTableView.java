package otletek;

import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenericTableView<E> {

    private List<E> adatok;
    private TableView<E> tablazat;
    private List<Field> oszlopokTipusai;
    private E e;

    public GenericTableView(E e){
        this.adatok = new ArrayList<>();
        //this.tablazat = new TableView<>();
        this.oszlopokTipusai = new ArrayList<>();
        Object o = new Object();

       // E e = ((E) o);
        System.out.println(e.getClass());
        //this.tablazat = tablazat;
        Field[] mezok = e.getClass().getDeclaredFields();

            List l = new ArrayList();
        for(Field mezo: mezok){
            if(mezo.getName()!="id" && !mezo.getType().isInstance(l)) {

                this.oszlopokTipusai.add(mezo);

            }
        }
    }

    public List<E> getAdatok() {
        return adatok;
    }

    public void setAdatok(List<E> adatok) {
        this.adatok = adatok;
    }

    public TableView<E> getTablazat() {
        return tablazat;
    }

    public void setTablazat(TableView<E> tablazat) {
        this.tablazat = tablazat;
    }

    public List<Field> getOszlopokTipusai() {
        return oszlopokTipusai;
    }

    public void setOszlopokTipusai(List<Field> oszlopokTipusai) {
        this.oszlopokTipusai = oszlopokTipusai;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
