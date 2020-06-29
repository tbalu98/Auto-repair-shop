package utils;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.pmw.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


public class TableManagerImpl<E> implements TableManager<E> {

    protected TableView tabla;

    public TableManagerImpl(TableView tabla)  {


        this.tabla = tabla;

        this.setTableColumnokPropValue();

    }

    @Override
    public void setEntitasok(List entities) {

            Logger.info("Íme a tábla: " + this.tabla.getItems());
            this.tabla.setItems(FXCollections.observableArrayList(entities));

    }

    @Override
    public List<E> getJelenlegiEntitasok() {
        List<E> res = new ArrayList<E>();
        for(Object o: this.tabla.getItems()){
            E e = (E) o;
            res.add(e);
        }
        return (List<E>)res;
    }

    @Override
    public void setTabla(TableView<E> tabla) {
        this.tabla = tabla;
    }

    @Override
    public E getSelectedItem() {
        return (E)this.tabla.getSelectionModel().getSelectedItem();
    }

    @Override
    public void addEntity(E entity) {

        this.tabla.getItems().add(entity);
    }

    @Override
    public void addEntity(List<E> entities) {

        this.tabla.setItems(FXCollections.observableArrayList(entities));
        Logger.info("----"+this.tabla.getItems());
    }

    @Override
    public E getSelectedEntity() {

        return (E)this.tabla.getSelectionModel().getSelectedItem();

    }

    @Override
    public void setEntities(List<E> entities) {

        this.tabla.setItems(FXCollections.observableArrayList(entities));

    }

    @Override
    public void removeSelectedEntity() {

        this.tabla.getItems().remove(this.getSelectedEntity());

    }

    @Override
    public void setSelectedEntity(E entity) {
        this.getJelenlegiEntitasok().set(this.tabla.getItems().indexOf(this.getSelectedItem()),entity);

    }

    @Override
    public void rerfreshTable() {
        this.tabla.refresh();
    }

    @Override
    public void removeAll() {
        this.tabla.getItems().removeAll(this.tabla.getItems());
    }


    private void setTableColumnokPropValue(){

        Logger.info(this.tabla.getColumns());
        for(Object tc: this.tabla.getColumns()){

            Logger.info(((TableColumn) tc).getId()
                    .replace("Oszlop",""));

            ((TableColumn) tc).setCellValueFactory(
                    new PropertyValueFactory<>(((TableColumn) tc).getId()
                            .replace("Oszlop","")));

        }

    }


}

  /*          ((TableColumn) tc).setCellValueFactory(
                    new PropertyValueFactory<>(((TableColumn) tc).getId()
        .toLowerCase().replace("oszlop","")));
*/