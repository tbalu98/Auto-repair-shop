package controllers;

import entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import nezetek.FelhasznaltAlkatreszekNezet;
import nezetek.JavitasokNezet;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SzerelesMegtekintese extends GepjarmuszereloBasicControllerWithInitData{


    protected Szereles szereles;

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

    protected TableManager<FelhasznaltAlkatreszekNezet> felahasznaltAlkatreszekTM;
    protected TableManager<JavitasokNezet> javitasokTM ;

    

    @Override
    public void initData(Object o) {
        Szereles szereles = (Szereles)o;
        //Hibernate.initialize(szereles);

        this.szereles = szereles;

        Logger.info(this.szereles.getJavitasok());

        this.setLableek();

        //this.javitasokTM.setEntitasok(JavitasokNezet.of(this.javitasDao.findAll(this.szereles.getJavitasokIdk())));
        //Logger.info(JavitasokNezet.of(this.szereles.getJavitasok()));
        List<JavitasokNezet> javitasokNezetek = JavitasokNezet.of(this.szereles.getJavitasok());
        this.javitasokTM.setEntitasok(JavitasokNezet.of(this.szereles.getJavitasok()));

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

      this.nevL.setText(this.szereles.getUgyfelNev());
      this.lakcimL.setText(this.szereles.getUgyfelLakcim());
      this.telefonszamL.setText(this.szereles.getUgyfelTelefonszam());

      this.tipusL.setText(this.szereles.getGepjarmuTipus());
      this.motorTerfogataL.setText(this.szereles.getGepjarmuMotorTerfogat().toString());
      this.teljesitmenyL.setText(this.szereles.getGepjarmuTeljesitmeny().toString());

      this.evjaratL.setText(this.szereles.getGepjarmuEvjarat().toString());
      this.vizsgaLejartaL.setText(this.szereles.getGepjarmuVizsgaLejarta().toString());

      this.motorTerfogataL.setText(this.szereles.getGepjarmu().getGepjarmuparameter().getMotorterfogat().toString());
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.felahasznaltAlkatreszekTM = new TableManagerImpl<>(this.felhasznaltAlkatreszekTV);
        this.javitasokTM = new TableManagerImpl<>(this.javitasokTV);

    }

    public void felhasznaltAlkatreszeinekMegjelenitesePushed(){

        //Javitas javitas = this.javitasDao.getById(this.javitasokTM.getSelectedEntity().getId());
        Javitas javitas = this.javitasokTM.getSelectedEntity().getJavitas();
        Logger.info(javitas.getId());
        Logger.info(javitas.getFelhasznaltAlkatreszek().size());
        for(FelhasznaltAlkatresz felhasznaltAlkatresz: javitas.getFelhasznaltAlkatreszek()){
            Logger.info(felhasznaltAlkatresz.toString());
        }

        for(FelhasznaltAlkatreszekNezet felhasznaltAlkatreszekNezet: FelhasznaltAlkatreszekNezet.of(javitas.getFelhasznaltAlkatreszek())){

            Logger.info(felhasznaltAlkatreszekNezet.toString());

        }

        this.felahasznaltAlkatreszekTM.setEntitasok(FelhasznaltAlkatreszekNezet.of(javitas.getFelhasznaltAlkatreszek()));

    }

}