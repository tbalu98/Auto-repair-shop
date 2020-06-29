package otletek;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.pmw.tinylog.Logger;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TablazatController<E> {

    protected List<Field> oszlopok;

    protected List<Field> entityFieldek;

    protected E entity;

    public void setPrototype(E entity){

        this.entity = entity;

    }

    public void setOszlopok(){

        this.oszlopok = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(c -> c.getType().getSimpleName().equals("TableColumn")).collect(Collectors.toList());
        System.out.println(this.oszlopok);

    }

    public void setEntityFieldek(){


        this.entityFieldek = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(c -> !c.getName().contains("id")).collect(Collectors.toList());
        Logger.info(this.entityFieldek);
    }

    public void setOszlopokCellValueFactory(){

        System.out.println( "this.oszlopok" +this.oszlopok);
        for(Field f: this.oszlopok){

            try {

                String fieldNev = this.entityFieldek.stream()
                        .filter(c ->c .getName().contains(f.getName().replace("Oszlop", ""))).findFirst().get().getName();

                System.out.println("------");
                System.out.println(f);

                System.out.println(fieldNev);
                TableColumn tc = (TableColumn)f.get(this);

                tc.setCellValueFactory(new PropertyValueFactory<>(fieldNev));

            } catch (IllegalAccessException e) {

                e.printStackTrace();

            }

        }

    }


}
