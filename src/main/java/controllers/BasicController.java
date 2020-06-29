package controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
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
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BasicController implements Initializable{


    @FXML protected MenuBar menuBar;





    private void showUjScene(Scene ujScene){

        Stage window = (Stage)menuBar.getScene().getWindow();

        window.setScene(ujScene);
        window.show();
        Logger.info("meghivtak");


    }

    private Parent getParent(String fxmlNev) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlNev + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlNev+".fxml"));
*/
        Parent root = this.getLoader(fxmlNev).load();

        return root;

    }

    private Parent getParent(FXMLLoader loader) throws  IOException{
        return loader.load();
    }

    private FXMLLoader getLoader(String fxmlNev){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlNev + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlNev+".fxml"));

        return loader;

    }

    public void scenetValt( String fxmlNev) throws IOException{
  /*      FXMLLoader loader = new FXMLLoader(getClass()
                .getResource(fxmlNev + ".fxml"));
        loader.setLocation(FXMLLoader.getDefaultClassLoader()
                .getResource(fxmlNev+".fxml"));

        Parent root = loader.load();
*/

        Scene ujScene = new Scene(this.getParent(fxmlNev ));

        this.showUjScene(ujScene);
  /*      Stage window = (Stage)menuBar.getScene().getWindow();

        window.setScene(ujScene);
        window.show();
        Logger.info("meghivtak");
*/
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ujAblak(String fxmlNev, String title)throws  IOException{

        FXMLLoader fxmlLoader = this.getLoader(fxmlNev);
        Parent root1 = (Parent)fxmlLoader.load();
       showUjStage(root1,title);
    }

    public void showUjStage(Parent root1, String title){

        Stage stage = new Stage();

        stage.setTitle(title);
        stage.setScene(new Scene(root1));
        stage.show();


    }

    public void scenetValt(String fxmlNev, Object o) throws IOException{



        FXMLLoader loader = this.getLoader(fxmlNev);
        Logger.info(loader);
        Parent root = this.getParent(loader);

        // Scene scene = root.getScene();
        Scene scene = new Scene(root);
        Logger.info(root);
        BasicControllerWithInitData controller = loader.getController();

        controller.initData(o);



        this.showUjScene(scene);



    }

    public void ujAblak(String fxmlNev, String title, Object o)throws IOException{

        FXMLLoader loader = this.getLoader(fxmlNev);


        Parent root1 = (Parent)loader.load();
        BasicControllerWithInitData controller = loader.getController();
        controller.initData(o);
        showUjStage(root1,title);

    }

    public void ujAblak(String fxmlNev, String title, List l)throws IOException{

        FXMLLoader loader = this.getLoader(fxmlNev);


        Parent root1 = (Parent)loader.load();
        BasicControllerWithInitData controller = loader.getController();
        controller.initData(l);
        showUjStage(root1,title);

    }

    public void hibauzenetetHozLetreesMutat(String title, String header, String tanacs){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(tanacs);

        Logger.info("error");
        alert.showAndWait();
    }

    }

