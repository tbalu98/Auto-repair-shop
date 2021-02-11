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

    @FXML protected TableView felhasznaltAlkatreszekTV;
    @FXML protected Label nevL;
    @FXML protected Label lakcimL;
    @FXML protected Label telefonszamL;
    @FXML protected Label tipusL;
    @FXML protected Label motorTerfogataL;
    @FXML protected Label teljesitmenyL;
    @FXML protected Label evjaratL;
    @FXML protected Label vizsgaLejartaL;

    protected TableManager<UsedPartsRepresentation> felahasznaltAlkatreszekTM;
    protected TableManager<AssembliesRepresentation> javitasokTM ;

    

    @Override
    public void initData(Object o) {
        Repair repair = (Repair)o;
        //Hibernate.initialize(szereles);

        this.repair = repair;

        Logger.info(this.repair.getJavitasok());

        this.setLableek();

        //this.javitasokTM.setEntitasok(JavitasokNezet.of(this.javitasDao.findAll(this.szereles.getJavitasokIdk())));
        //Logger.info(JavitasokNezet.of(this.szereles.getJavitasok()));
        List<AssembliesRepresentation> javitasokNezetek = AssembliesRepresentation.of(this.repair.getJavitasok());
        this.javitasokTM.setEntitasok(AssembliesRepresentation.of(this.repair.getJavitasok()));

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
  protected void setLableek() {

      this.nevL.setText(this.repair.getUgyfelNev());
      this.lakcimL.setText(this.repair.getUgyfelLakcim());
      this.telefonszamL.setText(this.repair.getUgyfelTelefonszam());

      this.tipusL.setText(this.repair.getGepjarmuTipus());
      this.motorTerfogataL.setText(this.repair.getGepjarmuMotorTerfogat().toString());
      this.teljesitmenyL.setText(this.repair.getGepjarmuTeljesitmeny().toString());

      this.evjaratL.setText(this.repair.getGepjarmuEvjarat().toString());
      this.vizsgaLejartaL.setText(this.repair.getGepjarmuVizsgaLejarta().toString());

      this.motorTerfogataL.setText(this.repair.getCar().getGepjarmuParameter().getMotorterfogat().toString());
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.felahasznaltAlkatreszekTM = new TableManagerImpl<>(this.felhasznaltAlkatreszekTV);
        this.javitasokTM = new TableManagerImpl<>(this.javitasokTV);

    }

    public void felhasznaltAlkatreszeinekMegjelenitesePushed(){

        //Javitas javitas = this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());
        Assembly assembly = this.javitasokTM.getSelectedEntity().getAssembly();
        Logger.info(assembly.getId());
        Logger.info(assembly.getFelhasznaltAlkatreszek().size());
        for(UsedPart usedPart : assembly.getFelhasznaltAlkatreszek()){
            Logger.info(usedPart.toString());
        }

        for(UsedPartsRepresentation usedPartsRepresentation : UsedPartsRepresentation.of(assembly.getFelhasznaltAlkatreszek())){

            Logger.info(usedPartsRepresentation.toString());

        }

        this.felahasznaltAlkatreszekTM.setEntitasok(UsedPartsRepresentation.of(assembly.getFelhasznaltAlkatreszek()));

    }

}