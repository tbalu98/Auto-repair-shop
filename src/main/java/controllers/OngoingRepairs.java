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

    @FXML private TableView<OngoingRepairsRepresentation> folyamatbanLevoSzerelesekTV;

    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());


    private TableManager<OngoingRepairsRepresentation> folyamatbanLevoSzerelesekManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       folyamatbanLevoSzerelesekManager = new TableManagerImpl<>(this.folyamatbanLevoSzerelesekTV);

        List<Repair> folyamatbanLevoSzerelesek = this.repairDao.folyamatbanLevoSzerelesek();

        List<OngoingRepairsRepresentation> folyamatbanLevoSzerelesNezetek =
                OngoingRepairsRepresentation.of(folyamatbanLevoSzerelesek);

        Logger.info(folyamatbanLevoSzerelesek.size());

        this.folyamatbanLevoSzerelesekManager.addEntity(folyamatbanLevoSzerelesNezetek);

    }

    public void szerelesSzerkesztesereNavigal() throws IOException {
        Logger.info( "kivalasztott szereles id-ja");
    Logger.info( this.getKivalasztottSzerelesEntity().getId());
    szerelesSzerkesztesereNavigal("SzerelesSzerkesztese", this.getKivalasztottSzerelesEntity() );
    }

    private void szerelesSzerkesztesereNavigal(String fxmlNev, Repair repair) throws IOException {
        this.changeScene(fxmlNev, repair);


    }

    private Repair getKivalasztottSzerelesEntity(){

        return this.repairDao.getById(this.folyamatbanLevoSzerelesekManager.getSelectedItem().getId());

    }

}
