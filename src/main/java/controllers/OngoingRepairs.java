package controllers;

import daos.EntityManagerCreator;
import daos.RepairDao;
import entities.Repair;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import representations.OngoingRepairsRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OngoingRepairs extends AutoRepairshopBasicController {

    @FXML private TableView<OngoingRepairsRepresentation> ongoingRepairsTV;

    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());


    private TableManager<OngoingRepairsRepresentation> onGoingRepairsTM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       onGoingRepairsTM = new TableManagerImpl<>(this.ongoingRepairsTV);

        List<Repair> ongoingRepairs = this.repairDao.folyamatbanLevoSzerelesek();

        List<OngoingRepairsRepresentation> ongoingRepairsRep =
                OngoingRepairsRepresentation.of(ongoingRepairs);

        Logger.info(ongoingRepairs.size());

        this.onGoingRepairsTM.addEntity(ongoingRepairsRep);

    }

    public void navToEditingRepair() throws IOException {
        Logger.info( "kivalasztott szereles id-ja");
    Logger.info( this.getChosenRepairEntity().getId());
    navToEditingRepair("SzerelesSzerkesztese", this.getChosenRepairEntity() );
    }

    private void navToEditingRepair(String fxmlNev, Repair repair) throws IOException {
        this.changeScene(fxmlNev, repair);


    }

    private Repair getChosenRepairEntity(){

        return this.repairDao.getById(this.onGoingRepairsTM.getSelectedItem().getId());

    }

}
