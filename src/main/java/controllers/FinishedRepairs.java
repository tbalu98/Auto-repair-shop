package controllers;

import daos.EntityManagerCreator;
import daos.RepairDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import representations.FinishedRepairsRepresentation;
import org.pmw.tinylog.Logger;
import utils.TableManagerImpl;
import utils.TableManager;


import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FinishedRepairs extends AutoRepairshopBasicController {

    @FXML private TableView<FinishedRepairsRepresentation> lezartSzerelesekTV;
    @FXML private DatePicker tolDP;
    @FXML private DatePicker igDP;
    @FXML private Label bevetelL;
    private TableManager<FinishedRepairsRepresentation> lezartSzerelesekTM;
    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        Logger.info("Lezárt szerelések.");

        lezartSzerelesekTM = new TableManagerImpl<>(this.lezartSzerelesekTV);
        Timestamp tol = Timestamp.valueOf(LocalDate.of(LocalDate.now().getYear(),1,1 ).atStartOfDay());
        Timestamp ig = new Timestamp(System.currentTimeMillis());

        this.lezartSzerelesekTM.setEntitasok(FinishedRepairsRepresentation.of(this.repairDao.getLezartSzerelesek(tol, ig)));
        this.setBevetelL();
    }

    public void keres(){

        if(tolDP.getValue()!=null && igDP.getValue()!=null){

            Timestamp tol = Timestamp.valueOf(tolDP.getValue().atStartOfDay());
            Timestamp ig = Timestamp.valueOf(igDP.getValue().atStartOfDay());
            Logger.info("keres");
            this.lezartSzerelesekTM.setEntitasok(FinishedRepairsRepresentation.of(this.repairDao.getLezartSzerelesek(tol, ig)));
            this.setBevetelL();

        }

    }

    private void setBevetelL(){

        List<FinishedRepairsRepresentation> lezartSzerelesNezetek = this.lezartSzerelesekTM.getJelenlegiEntitasok();

        if(lezartSzerelesNezetek.size()>0) {
            lezartSzerelesNezetek.stream().map(c -> ((FinishedRepairsRepresentation) c).getAr())
                    .reduce((a, b) -> (Integer) a + (Integer) b).ifPresent(c -> bevetelL.setText(c.toString() + " Ft"));
        }else {
            bevetelL.setText("0 Ft");
        }
    }


    public void megtekintesPushed()throws IOException {

        this.changeScene("SzerelesMegtekintese",this.lezartSzerelesekTM.getSelectedEntity().getRepair());

    }
}