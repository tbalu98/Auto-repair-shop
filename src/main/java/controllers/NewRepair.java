package controllers;

import daos.*;
import entities.Car;
import entities.CarParameter;
import entities.Customer;
import entities.Repair;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import representations.AutoRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRepair extends AutoRepairshopBasicController {

    private CustomerDao customerDao = new CustomerDao(EntityManagerCreator.getEntityManager());
    private AutoDao autoDao = new AutoDao(EntityManagerCreator.getEntityManager());
    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());
    private AutoParameterDao autoParameterDao = new AutoParameterDao(EntityManagerCreator.getEntityManager());

    //@FXML private TextField nevTextField;
    @FXML private TextField telefonszamTF;
    @FXML private TextField lakcimTF;
    @FXML private TextField nevTF;

    @FXML private TextField tipusTF;
    @FXML private TextField motorTerfogataTF;
    @FXML private TextField teljesitmenyTF;

    @FXML private TextField evjaratTF;
    @FXML private DatePicker vizsgaLejartaDP;
    @FXML private TextField alvazszamTF;

    @FXML private TableView<Customer> ugyfelTV;
    @FXML private TableView<CarParameter> gepjarmuparameterTV;
    @FXML private TableView<AutoRepresentation> teljesGepjarmuTV;


    private TableManager<Customer> ugyfelTM;
    private TableManager<CarParameter> gepjarmuparameterTM;
    private TableManager<AutoRepresentation> teljesGepjarmuNezetTM;


    //öröklött

   // @FXML private MenuBar menuBar;

    private Customer customer;
    private Car car;
    private Repair repair;
    private CarParameter carParameter;
    private String nincsElegAdatHibaTitle = "";
    private String nincsElegAdatHibaHeader = "";
    private String nincsElegAdatContentText = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.ugyfelTM = new TableManagerImpl<>(this.ugyfelTV);
        this.gepjarmuparameterTM = new TableManagerImpl<>(this.gepjarmuparameterTV);
        this.teljesGepjarmuNezetTM = new TableManagerImpl<>(this.teljesGepjarmuTV);


    }

    private Customer ujUgyfelletrehozasa(){

        Customer customer =  new Customer(this.nevTF.getText(), this.telefonszamTF.getText(),this.lakcimTF.getText());
        //this.ugyfel = ugyfel;

        return customer;

    }

    private void ujUgyfelMentese(){

        Customer customer = this.ujUgyfelletrehozasa();

       //kivett persist
       // this.ugyfelDao.persist(ugyfel);
        this.customer = customer;

    }

    public void ujUgyfeletFelveszPushed(){

        Logger.info(this.ugyfelTFekKitolteseHelyes());
        if(!this.ugyfelTFekKitolteseHelyes()){
            Logger.info("hiba");
            this.showErrorMessage("Hiba történt","Az ügyfél mezők hibásan vannak kitöltve", "Tanács...");
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Hiba történt");
         alert.setHeaderText("Az ügyfél mezők hibásan vannak kitöltve!");
         alert.setContentText("Tanács...");

         Logger.info("error");
         alert.showAndWait();*/
        }
        else {
            Logger.info("elseben");
            ujUgyfelMentese();
        }
    }
/* TODO string null  "" helyett */
    private boolean ugyfelTFekKitolteseHelyes() {
        Logger.info("+"+this.nevTF.getText()+"+");
            return !this.nevTF.getText().equals("") && !this.lakcimTF.getText().equals("") && !this.telefonszamTF.getText().equals("");
    }


    public void ujGepjarmuPushed(){

        if(this.carParameter !=null) {
            this.car = new Car(this.carParameter, Integer.parseInt(this.alvazszamTF.getText()),
                    this.vizsgaLejartaDP.getValue(), Integer.parseInt(this.evjaratTF.getText()));
        }
        else{

            this.nincsKivalasztottGepjarmuparameter();

        }


    }

    private void nincsKivalasztottGepjarmuparameter() {


    }

    /*TODO beletenni ezt a kódrészletet a dolgozatomba*/
    public void szerelesInditasaPushed(){

        Logger.info("Szerelés indítása pushed------");
        this.feltetelesenSzerelestMent();
        this.ujSzereleshezElokeszul();

    }

    private void feltetelesenSzerelestMent() {
        if(this.vanRogzitettAdatSzerelesInditasahoz()){

            this.szerelesEsEntitasinakMentese();

        }
        else{
            this.nincsElegAdatASzerelesInditasahozHiba();
        }

    }


    private void nincsElegAdatASzerelesInditasahozHiba() {
        this.showErrorMessage(this.nincsElegAdatHibaTitle, this.nincsElegAdatHibaHeader, this.nincsElegAdatContentText);
        Logger.info("valami hiányzik");
    }

    private boolean vanRogzitettAdatSzerelesInditasahoz(){

        return this.car !=null && this.customer != null;

    }

    private void ujSzereleshezElokeszul() {

        this.regiSzerelesAdataitTorol();
        this.textFieldekTartalmatTorol();
        this.tablakTartalmatTorol();
    }

    private void tablakTartalmatTorol() {
        this.gepjarmuparameterTM.removeAll();
        this.teljesGepjarmuNezetTM.removeAll();
        this.ugyfelTM.removeAll();
    }

    private void textFieldekTartalmatTorol() {

        this.motorTerfogataTF.setText("");
        this.teljesitmenyTF.setText("");
        this.tipusTF.setText("");
        this.alvazszamTF.setText("");
        this.evjaratTF.setText("");
        this.vizsgaLejartaDP.setChronology(null);

        this.lakcimTF.setText("");
        this.nevTF.setText("");
        this.telefonszamTF.setText("");

    }

    private void regiSzerelesAdataitTorol() {

        this.carParameter = null;
        this.car = null;
        this.customer = null;

    }

    private void szerelesEsEntitasinakMentese(){

        this.szereleshezTartozoEntitasokMentese();
        this.szerelesMentese();
    }

    /* TODO ellenőrizni mert új függvény*/
    private void szereleshezTartozoEntitasokMentese() {

        this.customerDao.saveOrUpdate(this.customer);
        this.autoParameterDao.saveOrUpdate(this.carParameter);
        this.autoDao.saveOrUpdate(this.car);

    }

    private void szerelesMentese() {
        Repair repair = new Repair(this.car,this.customer);
        this.repairDao.persist(repair);

    }



    public void ugyfeltKeresPushed(){


        Logger.info(this.ugyfeletLetrehoz());
        this.ugyfelTM.setEntitasok(this.customerDao.find(this.ugyfeletLetrehoz()));


    }



    private Customer ugyfeletLetrehoz() {

        return new Customer(this.nevTF.getText(),this.telefonszamTF.getText(),this.lakcimTF.getText());

    }

    public void ujGepjarmuparameterFelvetelPushed(){

        this.carParameter = new CarParameter(this.tipusTF.getText(),
                Integer.parseInt(this.motorTerfogataTF.getText()),Integer.parseInt(this.teljesitmenyTF.getText()));

    }


    public void gepjarmuParameterreKeresPushed(){
        Logger.info(this.gepjarmuvetLetrehoz());
        this.gepjarmuparameterTM.setEntitasok(this.autoParameterDao.find(this.gepjarmuparametertLetrehoz()));

    }

    private CarParameter gepjarmuparametertLetrehoz() {
        return new CarParameter(this.tipusTF.getText(),this.motorTerfogataTF.getText().equals("")?null:Integer.parseInt(this.motorTerfogataTF.getText()),
                this.teljesitmenyTF.getText().equals("")?null:Integer.parseInt(this.telefonszamTF.getText()));
    }

    public void gepjarmureKeresPushed(){


        this.teljesGepjarmuNezetTM.setEntitasok(AutoRepresentation.of(this.autoDao.find(this.gepjarmuvetLetrehoz())));

    }

    private Car gepjarmuvetLetrehoz() {

        return new Car(null,!this.alvazszamTF.getText().equals("")?Integer.parseInt(this.alvazszamTF.getText()):null,this.vizsgaLejartaDP.getValue(),
                this.evjaratTF.getText().equals("")?null:Integer.parseInt(this.evjaratTF.getText()));

    }

    public void gepjarmuParametertKivalasztPushed(){
        this.carParameter = this.gepjarmuparameterTM.getSelectedEntity();
        this.gepjarmuparameterTFekKitoltese();
    }

    private void gepjarmuparameterTFekKitoltese() {
        if(this.carParameter !=null){
            this.tipusTF.setText(this.carParameter.getTipus());
            this.motorTerfogataTF.setText(this.carParameter.getMotorterfogat().toString());
            this.teljesitmenyTF.setText(this.carParameter.getTeljesitmeny().toString());
        }
    }

    public void ugyfeletKivalasztPushed(){
        this.customer = this.ugyfelTM.getSelectedEntity();
        this.ugyfelTFekKitoltese();
    }

    private void ugyfelTFekKitoltese() {
        if(this.customer != null){
            this.nevTF.setText(this.customer.getNev());
            this.telefonszamTF.setText(this.customer.getTelefonszam());
            this.lakcimTF.setText(this.customer.getLakcim());
        }
    }


    public void gepjarmuvetKivalasztPushed(){
        //demeter törvényének megsértése
        this.carParameter = this.teljesGepjarmuNezetTM.getSelectedEntity().getCar().getGepjarmuParameter();
        this.car = this.teljesGepjarmuNezetTM.getSelectedEntity().getCar();
        this.gepjarmuparameterTFekKitoltese();
        this.gepjarmuTFekKitoltese();
    }

    private void gepjarmuTFekKitoltese() {
        if(this.car !=null) {
            this.alvazszamTF.setText(this.car.getAlvazszam().toString());
            this.evjaratTF.setText(this.car.getEvjarat().toString());
            this.vizsgaLejartaDP.setValue(this.car.getVizsgaLejarta());
        }
    }


}