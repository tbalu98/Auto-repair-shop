package utils;


import javafx.scene.control.TableView;

import java.util.List;

public interface TableManager<E> {




    void setEntitasok(List<E> entities);

    List getJelenlegiEntitasok();

    void setTabla(TableView<E> tabla);

    E getSelectedItem();

    void addEntity(E entity);

    void addEntity(List<E> entities);

    E getSelectedEntity();

    void setEntities(List<E> entities);

    void removeSelectedEntity();

    void setSelectedEntity(E entity);

    void rerfreshTable();

    void removeAll();
}
