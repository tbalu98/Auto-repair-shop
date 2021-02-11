package controllers;

import daos.EntityManagerCreator;
import daos.AssemblyTypeDao;
import entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import representations.AssemblyTypesRepresentation;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRepairType extends BasicController{


    @FXML private TextArea leirasTA;
    @FXML private TextField garanciaIdotartamaTF;
    @FXML private TextField fixArTF;

    @FXML
    private TableView<AssemblyTypesRepresentation> javitasTipusokTV;

    private TableManager<AssemblyTypesRepresentation> javitasTipusokTM;
    private AssemblyTypeDao assemblyTypeDao = new AssemblyTypeDao(EntityManagerCreator.getEntityManager());



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        javitasTipusokTM = new TableManagerImpl<AssemblyTypesRepresentation>(this.javitasTipusokTV);
        javitasTipusokTM.setEntitasok(AssemblyTypesRepresentation.of(this.assemblyTypeDao.findAll()));

    }

    private HourlyPricedAssemblyType oradijasJavitasTipusLetrehozasaFieldekbol(){

        return new HourlyPricedAssemblyType(this.leirasTA.getText(), Integer.parseInt(this.garanciaIdotartamaTF.getText()));

    }

    private FixPricedAssemblyType fixaruJavitasTipusLetrehozasaTextFieldekbol(){

        return new FixPricedAssemblyType(this.leirasTA.getText(),Integer.parseInt(this.garanciaIdotartamaTF.getText()),
                Integer.parseInt(this.fixArTF.getText()));

    }

    public void ujOradijasJavitastipus(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType = this.oradijasJavitasTipusLetrehozasaFieldekbol();
    this.assemblyTypeDao.persist(hourlyPricedAssemblyType);
    this.javitasTipusokTM.addEntity(new AssemblyTypesRepresentation(hourlyPricedAssemblyType));


    }

    public void ujFixAruJavitasTipus(){
        FixPricedAssemblyType fixPricedAssemblyType = this.fixaruJavitasTipusLetrehozasaTextFieldekbol();
        this.assemblyTypeDao.persist(this.fixaruJavitasTipusLetrehozasaTextFieldekbol());
        this.javitasTipusokTM.addEntity(new AssemblyTypesRepresentation(fixPricedAssemblyType));

    }

    /* TODO Átnézni a javítástípus törlését. */
    public void javitasTipusTorlese(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType = this.assemblyTypeDao.getById(this.javitasTipusokTM.getSelectedEntity().getId());

        if(hourlyPricedAssemblyType.getJavitasok().isEmpty()){

            this.assemblyTypeDao.remove(hourlyPricedAssemblyType);
            this.javitasTipusokTM.removeSelectedEntity();
            this.javitasTipusokTM.rerfreshTable();
        }
    }

}