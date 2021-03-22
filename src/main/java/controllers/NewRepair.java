package controllers;

import daos.*;
import entities.Car;
import entities.CarParameter;
import entities.Customer;
import entities.Repair;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import representations.CarRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRepair extends CarRepairshopBasicController {

    private CustomerDao customerDao = new CustomerDao(EntityManagerCreator.getEntityManager());
    private CarDao carDao = new CarDao(EntityManagerCreator.getEntityManager());
    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());
    private CarParameterDao carParameterDao = new CarParameterDao(EntityManagerCreator.getEntityManager());

    //@FXML private TextField nevTextField;
    @FXML private TextField telephoneNumberTF;
    @FXML private TextField addressTF;
    @FXML private TextField nameTF;

    @FXML private TextField typeTF;
    @FXML private TextField volumeOfEngineTF;
    @FXML private TextField powerTF;

    @FXML private TextField yearTF;
    @FXML private DatePicker expirationDataDP;
    @FXML private TextField vehicleIdentificationNumberTF;

    @FXML private TableView<Customer> customerTV;
    @FXML private TableView<CarParameter> carParameterTV;
    @FXML private TableView<CarRepresentation> carRepresentationTV;


    private TableManager<Customer> customerTM;
    private TableManager<CarParameter> carParameterTM;
    private TableManager<CarRepresentation> carRepTM;


    //öröklött

   // @FXML private MenuBar menuBar;

    private Customer customer;
    private Car car;
    private Repair repair;
    private CarParameter carParameter;
    private String notEnoughDataTitle = "";
    private String notEnoughDataHeader = "";
    private String notEnoughDataContentText = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.customerTM = new TableManagerImpl<>(this.customerTV);
        this.carParameterTM = new TableManagerImpl<>(this.carParameterTV);
        this.carRepTM = new TableManagerImpl<>(this.carRepresentationTV);


    }

    private Customer createNewCustomer(){

        Customer customer =  new Customer(this.nameTF.getText(), this.telephoneNumberTF.getText(),this.addressTF.getText());
        //this.ugyfel = ugyfel;

        return customer;

    }

    private void saveNewCustomer(){

        Customer customer = this.createNewCustomer();

       //kivett persist
       // this.ugyfelDao.persist(ugyfel);
        this.customer = customer;

    }

    public void addNewCustomerPushed(){

        Logger.info(this.customerTFsFilledCorrect());
        if(!this.customerTFsFilledCorrect()){
            Logger.info("hiba");
            this.showErrorMessage("ERROR","The text fields for customer's data was not filled correctly", "advice...");
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Hiba történt");
         alert.setHeaderText("Az ügyfél mezők hibásan vannak kitöltve!");
         alert.setContentText("Tanács...");

         Logger.info("error");
         alert.showAndWait();*/
        }
        else {
            Logger.info("elseben");
            saveNewCustomer();
        }
    }
/* TODO string null  "" helyett */
    private boolean customerTFsFilledCorrect() {
        Logger.info("+"+this.nameTF.getText()+"+");
            return !this.nameTF.getText().equals("") && !this.addressTF.getText().equals("") && !this.telephoneNumberTF.getText().equals("");
    }


    public void newCarPushed(){

        if(this.carParameter !=null) {
            this.car = new Car(this.carParameter, Integer.parseInt(this.vehicleIdentificationNumberTF.getText()),
                    this.expirationDataDP.getValue(), Integer.parseInt(this.yearTF.getText()));
        }
        else{

            this.noChosenCarParameter();

        }


    }

    private void noChosenCarParameter() {


    }

    /*TODO beletenni ezt a kódrészletet a dolgozatomba*/
    public void startRepairPushed(){

        Logger.info("Szerelés indítása pushed------");
        this.saveRepair();
        this.prepareForNewRepair();

    }

    //feltételesen szerelést ment
    private void saveRepair() {
        if(this.enoughDataForSavingRepair()){

            this.saveRepairAndItsEntities();

        }
        else{
            this.notEnoughDataForSavingRepair();
        }

    }


    private void notEnoughDataForSavingRepair() {
        this.showErrorMessage(this.notEnoughDataTitle, this.notEnoughDataHeader, this.notEnoughDataContentText);
        Logger.info("valami hiányzik");
    }

    private boolean enoughDataForSavingRepair(){

        return this.car !=null && this.customer != null;

    }

    private void prepareForNewRepair() {

        this.deleteDataOfRepair();
        this.deleteContentOfTextFields();
        this.deleteContentOfTables();
    }

    private void deleteContentOfTables() {
        this.carParameterTM.removeAll();
        this.carRepTM.removeAll();
        this.customerTM.removeAll();
    }

    private void deleteContentOfTextFields() {

        this.volumeOfEngineTF.setText("");
        this.powerTF.setText("");
        this.typeTF.setText("");
        this.vehicleIdentificationNumberTF.setText("");
        this.yearTF.setText("");
        this.expirationDataDP.setChronology(null);

        this.addressTF.setText("");
        this.nameTF.setText("");
        this.telephoneNumberTF.setText("");

    }

    private void deleteDataOfRepair() {

        this.carParameter = null;
        this.car = null;
        this.customer = null;

    }

    private void saveRepairAndItsEntities(){

        this.saveRepairsEntity();
        this.saveRepairToDB();
    }

    /* TODO ellenőrizni mert új függvény*/
    private void saveRepairsEntity() {

        this.customerDao.saveOrUpdate(this.customer);
        this.carParameterDao.saveOrUpdate(this.carParameter);
        this.carDao.saveOrUpdate(this.car);

    }

    private void saveRepairToDB() {
        Repair repair = new Repair(this.car,this.customer);
        this.repairDao.persist(repair);

    }



    public void findCustomerPushed(){


        Logger.info(this.createCustomer());
        this.customerTM.setEntitasok(this.customerDao.find(this.createCustomer()));


    }



    private Customer createCustomer() {

        return new Customer(this.nameTF.getText(),this.telephoneNumberTF.getText(),this.addressTF.getText());

    }

    public void newCarParameterPushed(){

        this.carParameter = new CarParameter(this.typeTF.getText(),
                Integer.parseInt(this.volumeOfEngineTF.getText()),Integer.parseInt(this.powerTF.getText()));

    }


    public void findCarParameterPushed(){
        Logger.info(this.createCar());
        this.carParameterTM.setEntitasok(this.carParameterDao.find(this.createCarParameter()));

    }

    private CarParameter createCarParameter() {
        return new CarParameter(this.typeTF.getText(),this.volumeOfEngineTF.getText().equals("")?null:Integer.parseInt(this.volumeOfEngineTF.getText()),
                this.powerTF.getText().equals("")?null:Integer.parseInt(this.telephoneNumberTF.getText()));
    }

    public void findCarPushed(){


        this.carRepTM.setEntitasok(CarRepresentation.of(this.carDao.find(this.createCar())));

    }

    private Car createCar() {

        return new Car(null,!this.vehicleIdentificationNumberTF.getText().equals("")?Integer.parseInt(this.vehicleIdentificationNumberTF.getText()):null,this.expirationDataDP.getValue(),
                this.yearTF.getText().equals("")?null:Integer.parseInt(this.yearTF.getText()));

    }

    public void choseCarParameterPushed(){
        this.carParameter = this.carParameterTM.getSelectedEntity();
        this.setCarParameterTFs();
    }

    private void setCarParameterTFs() {
        if(this.carParameter !=null){
            this.typeTF.setText(this.carParameter.getType());
            this.volumeOfEngineTF.setText(this.carParameter.getEngineVolume().toString());
            this.powerTF.setText(this.carParameter.getPower().toString());
        }
    }

    public void choseCustomerPushed(){
        this.customer = this.customerTM.getSelectedEntity();
        this.setCustomerTFs();
    }

    private void setCustomerTFs() {
        if(this.customer != null){
            this.nameTF.setText(this.customer.getName());
            this.telephoneNumberTF.setText(this.customer.getTelephoneNumber());
            this.addressTF.setText(this.customer.getAddress());
        }
    }


    public void choseCarPushed(){
        //demeter törvényének megsértése
        this.carParameter = this.carRepTM.getSelectedEntity().getCar().getGepjarmuParameter();
        this.car = this.carRepTM.getSelectedEntity().getCar();
        this.setCarParameterTFs();
        this.setCarTFs();
    }

    private void setCarTFs() {
        if(this.car !=null) {
            this.vehicleIdentificationNumberTF.setText(this.car.getVehicleIdentificationNumber().toString());
            this.yearTF.setText(this.car.getYear().toString());
            this.expirationDataDP.setValue(this.car.getExpirationDate());
        }
    }


}