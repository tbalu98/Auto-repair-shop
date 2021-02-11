package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

import java.io.IOException;

/**
 * This class represents the basic functions that almost every controller with received initializing data inherits
 * except pop-up windows.
 * On every scene you can switch to other scenes like to the Ongoing repairs scene, New repair, New assembly type
 * and finished repairs.
 */
public abstract class AutoRepairShopBasicControllerWithInitData extends BasicControllerWithInitData {
    @FXML
    protected MenuBar menuBar;

    public void ongoingRepairs(ActionEvent event) throws IOException {

        this.changeScene("FolyamatbanLevoSzerelesek");

    }

    public void newRepairs() throws IOException {

        this.changeScene("UjSzerelesFelvetele");

    }


    public void newAssemblyType()throws IOException{


        newWindow("UjJavitasTipus", "New assembly type");


    }
    public void finishedRepairs()throws IOException{

        this.changeScene("LezartSzerelesek");

    }

}