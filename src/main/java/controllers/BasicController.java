package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The BasicController class represents the most important functions of a controller with a menu bar.
 * The functions are responsible for changing to another scene, showing error message and creating a new window.
 */

public abstract class BasicController implements Initializable{


    @FXML protected MenuBar menuBar;





    private void showNewScene(Scene newScene){

        Stage window = (Stage)menuBar.getScene().getWindow();

        window.setScene(newScene);
        window.show();
        //Logger.info("meghivtak");


    }

    private Parent getParent(String fxmlName) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlName + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlName+".fxml"));
*/
        Parent root = this.getLoader(fxmlName).load();

        return root;

    }

    private Parent getParent(FXMLLoader loader) throws  IOException{
        return loader.load();
    }

    private FXMLLoader getLoader(String fxmlName){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlName + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlName+".fxml"));

        return loader;

    }

    public void changeScene(String fxmlName) throws IOException{
  /*      FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlName + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlName+".fxml"));

        Parent root = loader.load();
*/

        Scene ujScene = new Scene(this.getParent(fxmlName ));

        this.showNewScene(ujScene);
  /*      Stage window = (Stage)menuBar.getScene().getWindow();

        window.setScene(ujScene);
        window.show();
        Logger.info("meghivtak");
*/
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void newWindow(String fxmlName, String title)throws  IOException{

        FXMLLoader fxmlLoader = this.getLoader(fxmlName);
        Parent root = (Parent)fxmlLoader.load();
       showNewStage(root,title);
    }

    public void showNewStage(Parent root, String title){

        Stage stage = new Stage();

        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();


    }

    public void changeScene(String fxmlName, Object o) throws IOException{



        FXMLLoader loader = this.getLoader(fxmlName);
        Logger.info(loader);
        Parent root = this.getParent(loader);

        // Scene scene = root.getScene();
        Scene scene = new Scene(root);
        Logger.info(root);
        BasicControllerWithInitData controller = loader.getController();

        controller.initData(o);



        this.showNewScene(scene);



    }

    public void newWindow(String fxmlName, String title, Object o)throws IOException{

        FXMLLoader loader = this.getLoader(fxmlName);


        Parent root = (Parent)loader.load();
        BasicControllerWithInitData controller = loader.getController();
        controller.initData(o);
        showNewStage(root,title);

    }

    public void newWindow(String fxmlNev, String title, List list)throws IOException{

        FXMLLoader loader = this.getLoader(fxmlNev);


        Parent root = (Parent)loader.load();
        BasicControllerWithInitData controller = loader.getController();
        controller.initData(list);
        showNewStage(root,title);

    }

    public void showErrorMessage(String title, String header, String advice){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(advice);

        //Logger.info("error");
        alert.showAndWait();
    }

    }

