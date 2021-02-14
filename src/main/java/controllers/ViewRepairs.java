package controllers;

import entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import representations.UsedPartsRepresentation;
import representations.AssembliesRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRepairs extends AutoRepairShopBasicControllerWithInitData {


    protected Repair repair;

    @FXML protected TableView javitasokTV;

    //@FXML private TableView felhasznaltAlkatreszekTV;

    @FXML protected TableView usedPartsTV;
    @FXML protected Label nameL;
    @FXML protected Label addressL;
    @FXML protected Label telephoneNumberL;
    @FXML protected Label typeL;
    @FXML protected Label volumeOfEngineL;
    @FXML protected Label powerL;
    @FXML protected Label yearL;
    @FXML protected Label expirationDateL;

    protected TableManager<UsedPartsRepresentation> usedPartsRepTM;
    protected TableManager<AssembliesRepresentation> assembliesRepTM;

    

    @Override
    public void initData(Object o) {
        Repair repair = (Repair)o;
        //Hibernate.initialize(szereles);

        this.repair = repair;

        Logger.info(this.repair.getJavitasok());

        this.setLabels();

        //this.javitasokTM.setEntitasok(JavitasokNezet.of(this.javitasDao.findAll(this.szereles.getJavitasokIdk())));
        //Logger.info(JavitasokNezet.of(this.szereles.getJavitasok()));
        List<AssembliesRepresentation> javitasokNezetek = AssembliesRepresentation.of(this.repair.getJavitasok());
        this.assembliesRepTM.setEntitasok(AssembliesRepresentation.of(this.repair.getJavitasok()));

    }

  /*  protected void setLableek() {

        this.nevL.setText(this.szereles.getUgyfelNev());
        this.lakcimL.setText(this.szereles.getUgyfelLakcim());
        this.telefonszamL.setText(this.szereles.getUgyfelTelefonszam());

        this.tipusL.setText(this.szereles.getGepjarmuTipus());
        this.motorTerfogataL.setText(this.szereles.getGepjarmuMotorTerfogat().toString());
        this.teljesitmenyL.setText(this.szereles.getGepjarmuTeljesitmeny().toString());

        this.evjaratL.setText(this.szereles.getGepjarmuEvjarat().toString());
        this.vizsgaLejartaL.setText(this.szereles.getGepjarmuVizsgaLejarta().toString());

    }
*/
  protected void setLabels() {

      this.nameL.setText(this.repair.getUgyfelNev());
      this.addressL.setText(this.repair.getUgyfelLakcim());
      this.telephoneNumberL.setText(this.repair.getUgyfelTelefonszam());

      this.typeL.setText(this.repair.getGepjarmuTipus());
      this.volumeOfEngineL.setText(this.repair.getGepjarmuMotorTerfogat().toString());
      this.powerL.setText(this.repair.getGepjarmuTeljesitmeny().toString());

      this.yearL.setText(this.repair.getGepjarmuEvjarat().toString());
      this.expirationDateL.setText(this.repair.getGepjarmuVizsgaLejarta().toString());

      this.volumeOfEngineL.setText(this.repair.getCar().getGepjarmuParameter().getMotorterfogat().toString());
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.usedPartsRepTM = new TableManagerImpl<>(this.usedPartsTV);
        this.assembliesRepTM = new TableManagerImpl<>(this.javitasokTV);

    }

    public void displayUsedPartsPushed(){

        //Javitas javitas = this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());
        Assembly assembly = this.assembliesRepTM.getSelectedEntity().getAssembly();
        Logger.info(assembly.getId());
        Logger.info(assembly.getFelhasznaltAlkatreszek().size());
        for(UsedPart usedPart : assembly.getFelhasznaltAlkatreszek()){
            Logger.info(usedPart.toString());
        }

        for(UsedPartsRepresentation usedPartsRepresentation : UsedPartsRepresentation.of(assembly.getFelhasznaltAlkatreszek())){

            Logger.info(usedPartsRepresentation.toString());

        }

        this.usedPartsRepTM.setEntitasok(UsedPartsRepresentation.of(assembly.getFelhasznaltAlkatreszek()));

    }

}