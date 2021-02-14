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


    @FXML private TextArea descriptionTA;
    @FXML private TextField guaranteeTF;
    @FXML private TextField fixPriceTF;

    @FXML
    private TableView<AssemblyTypesRepresentation> assemblyTypesTV;

    private TableManager<AssemblyTypesRepresentation> assemblyTypeTM;
    private AssemblyTypeDao assemblyTypeDao = new AssemblyTypeDao(EntityManagerCreator.getEntityManager());



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        assemblyTypeTM = new TableManagerImpl<AssemblyTypesRepresentation>(this.assemblyTypesTV);
        assemblyTypeTM.setEntitasok(AssemblyTypesRepresentation.of(this.assemblyTypeDao.findAll()));

    }

    private HourlyPricedAssemblyType oradijasJavitasTipusLetrehozasaFieldekbol(){

        return new HourlyPricedAssemblyType(this.descriptionTA.getText(), Integer.parseInt(this.guaranteeTF.getText()));

    }

    private FixPricedAssemblyType fixaruJavitasTipusLetrehozasaTextFieldekbol(){

        return new FixPricedAssemblyType(this.descriptionTA.getText(),Integer.parseInt(this.guaranteeTF.getText()),
                Integer.parseInt(this.fixPriceTF.getText()));

    }

    public void newHourlyPricedAssemblyType(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType = this.oradijasJavitasTipusLetrehozasaFieldekbol();
    this.assemblyTypeDao.persist(hourlyPricedAssemblyType);
    this.assemblyTypeTM.addEntity(new AssemblyTypesRepresentation(hourlyPricedAssemblyType));


    }

    public void newFixPricedAssemblyType(){
        FixPricedAssemblyType fixPricedAssemblyType = this.fixaruJavitasTipusLetrehozasaTextFieldekbol();
        this.assemblyTypeDao.persist(this.fixaruJavitasTipusLetrehozasaTextFieldekbol());
        this.assemblyTypeTM.addEntity(new AssemblyTypesRepresentation(fixPricedAssemblyType));

    }

    /* TODO Átnézni a javítástípus törlését. */
    public void deleteAssemblyType(){

        HourlyPricedAssemblyType hourlyPricedAssemblyType = this.assemblyTypeDao.getById(this.assemblyTypeTM.getSelectedEntity().getId());

        if(hourlyPricedAssemblyType.getJavitasok().isEmpty()){

            this.assemblyTypeDao.remove(hourlyPricedAssemblyType);
            this.assemblyTypeTM.removeSelectedEntity();
            this.assemblyTypeTM.rerfreshTable();
        }
    }

}