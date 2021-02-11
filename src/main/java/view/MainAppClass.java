package view;

import daos.EntityManagerCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;
import utils.AutoCompleteTextField;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.applet.Applet;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainAppClass extends Application {

    private void setUp(){
        EntityManagerCreator.emf = Persistence.createEntityManagerFactory("test");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUp();

        //Parent root =  FXMLLoader.load(FXMLLoader.getDefaultClassLoader().getResource("TulajdonosokListaja.fxml"));


        Parent root =  FXMLLoader.load(FXMLLoader.getDefaultClassLoader().getResource("UjSzerelesFelvetele.fxml"));

       // Parent root =  FXMLLoader.load(FXMLLoader.getDefaultClassLoader().getResource("oroklodesestablaproba.fxml"));



        //URL url = Paths.get("src/main/java/view/TulajdonosokListaja.fxml").toUri().toURL();
        //Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        //---
        /*AutoCompleteTextField autoCompleteTextField = new AutoCompleteTextField();

        autoCompleteTextField.getEntries().addAll(Arrays.asList("AA", "AB", "AC","BCA"));


        HBox hbox = new HBox(autoCompleteTextField);
        VBox vBox = new VBox();
        AnchorPane anchorPane = new AnchorPane(vBox,hbox);
        anchorPane.get
        vBox.getChildren().add(new Button("push"));
        Scene scene = new Scene(anchorPane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();*/
       //---
        primaryStage.setTitle("Auto repair shop");
        primaryStage.setScene(new Scene(root, 1920, 1024));
        primaryStage.show();
    }

    public static void main(String[] args){

        launch(args);

    }

}
