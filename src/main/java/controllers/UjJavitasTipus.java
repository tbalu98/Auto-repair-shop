package controllers;

import daos.EntityManagerCreator;
import daos.JavitasTipusDao;
import entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nezetek.JavitasTipusNezet;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class UjJavitasTipus extends BasicController{


    @FXML private TextArea leirasTA;
    @FXML private TextField garanciaIdotartamaTF;
    @FXML private TextField fixArTF;

    @FXML
    private TableView<JavitasTipusNezet> javitasTipusokTV;

    private TableManager<JavitasTipusNezet> javitasTipusokTM;
    private JavitasTipusDao javitasTipusDao = new JavitasTipusDao(EntityManagerCreator.getEntityManager());



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        javitasTipusokTM = new TableManagerImpl<JavitasTipusNezet>(this.javitasTipusokTV);
        javitasTipusokTM.setEntitasok(JavitasTipusNezet.of(this.javitasTipusDao.findAll()));

    }

    private OradijasJavitasTipus oradijasJavitasTipusLetrehozasaFieldekbol(){

        return new OradijasJavitasTipus(this.leirasTA.getText(), Integer.parseInt(this.garanciaIdotartamaTF.getText()));

    }

    private FixAruJavitasTipus fixaruJavitasTipusLetrehozasaTextFieldekbol(){

        return new FixAruJavitasTipus(this.leirasTA.getText(),Integer.parseInt(this.garanciaIdotartamaTF.getText()),
                Integer.parseInt(this.fixArTF.getText()));

    }

    public void ujOradijasJavitastipus(){

        OradijasJavitasTipus oradijasJavitasTipus = this.oradijasJavitasTipusLetrehozasaFieldekbol();
    this.javitasTipusDao.persist(oradijasJavitasTipus);
    this.javitasTipusokTM.addEntity(new JavitasTipusNezet(oradijasJavitasTipus));


    }

    public void ujFixAruJavitasTipus(){
        FixAruJavitasTipus fixAruJavitasTipus = this.fixaruJavitasTipusLetrehozasaTextFieldekbol();
        this.javitasTipusDao.persist(this.fixaruJavitasTipusLetrehozasaTextFieldekbol());
        this.javitasTipusokTM.addEntity(new JavitasTipusNezet(fixAruJavitasTipus));

    }

    /* TODO Átnézni a javítástípus törlését. */
    public void javitasTipusTorlese(){

        OradijasJavitasTipus oradijasJavitasTipus = this.javitasTipusDao.getById(this.javitasTipusokTM.getSelectedEntity().getId());

        if(oradijasJavitasTipus.getJavitasok().isEmpty()){

            this.javitasTipusDao.remove(oradijasJavitasTipus);
            this.javitasTipusokTM.removeSelectedEntity();
            this.javitasTipusokTM.rerfreshTable();
        }
    }

}