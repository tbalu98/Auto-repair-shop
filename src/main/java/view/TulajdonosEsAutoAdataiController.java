package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


public class TulajdonosEsAutoAdataiController {
    @FXML private TextField Nev;
    @FXML private TextField Lakcim;
    @FXML private TextField Jogositvanyszam;
    @FXML private TextField Automarka;
    @FXML private TextField Rendszam;
    @FXML private TextArea Problema;

    public void szerelesFelvetelePushed(){
        String jogositvanyszamText = Jogositvanyszam.getText();
        //TulajdonosManager.getInstance().addTulajdonosokhoz(Nev.getText(),Lakcim.getText(),jogositvanyszamText);
        //Logger.info(DataStore.getTulajdonosok());
        //GepjarmuManager.getInstance().addGepjarmuvekhez(Automarka.getText(),Rendszam.getText(),jogositvanyszamText);
       // SzerelesManager.getInstance().addSzerelesekhez(Rendszam.getText(),jogositvanyszamText);

        Nev.clear();
        Jogositvanyszam.clear();
        Lakcim.clear();

    }
    public void folyamatbanLevoSzerelesekPushed(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/FolyamatbanLevoSzerelesek.fxml").toUri().toURL();
        Parent tableViewParent = FXMLLoader.load(url);
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void statisztikaPushed(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/view/LezartSzerelesek.fxml").toUri().toURL();
        Parent tableViewParent = FXMLLoader.load(url);
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
