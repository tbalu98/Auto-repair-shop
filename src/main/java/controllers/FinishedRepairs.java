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

public class FinishedRepairs extends CarRepairshopBasicController {

    @FXML private TableView<FinishedRepairsRepresentation> lezartSzerelesekTV;
    @FXML private DatePicker fromDP;
    @FXML private DatePicker toDP;
    @FXML private Label incomeL;
    private TableManager<FinishedRepairsRepresentation> finishedRepairsTM;
    private RepairDao repairDao = new RepairDao(EntityManagerCreator.getEntityManager());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        Logger.info("Lezárt szerelések.");

        finishedRepairsTM = new TableManagerImpl<>(this.lezartSzerelesekTV);
        Timestamp from = Timestamp.valueOf(LocalDate.of(LocalDate.now().getYear(),1,1 ).atStartOfDay());
        Timestamp to = new Timestamp(System.currentTimeMillis());

        this.finishedRepairsTM.setEntitasok(FinishedRepairsRepresentation.of(this.repairDao.getLezartSzerelesek(from, to)));
        this.setIncomeL();
    }

    public void find(){

        if(fromDP.getValue()!=null && toDP.getValue()!=null){

            Timestamp from = Timestamp.valueOf(fromDP.getValue().atStartOfDay());
            Timestamp to = Timestamp.valueOf(toDP.getValue().atStartOfDay());
            Logger.info("keres");
            this.finishedRepairsTM.setEntitasok(FinishedRepairsRepresentation.of(this.repairDao.getLezartSzerelesek(from, to)));
            this.setIncomeL();

        }

    }

    private void setIncomeL(){

        List<FinishedRepairsRepresentation> lezartSzerelesNezetek = this.finishedRepairsTM.getJelenlegiEntitasok();

        if(lezartSzerelesNezetek.size()>0) {
            lezartSzerelesNezetek.stream().map(c -> ((FinishedRepairsRepresentation) c).getAr())
                    .reduce((a, b) -> (Integer) a + (Integer) b).ifPresent(c -> incomeL.setText(c.toString() + " Ft"));
        }else {
            incomeL.setText("0 Ft");
        }
    }


    public void viewRepairPushed()throws IOException {

        this.changeScene("SzerelesMegtekintese",this.finishedRepairsTM.getSelectedEntity().getRepair());

    }
}