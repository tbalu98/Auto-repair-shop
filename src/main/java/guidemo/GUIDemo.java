package guidemo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Currency;

public class GUIDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = Paths.get("./src/main/java/guidemo/FXMLDocument.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
       // SzerelesBefejezese sz = new BefejezettSzereles();
        launch(args);
        System.out.println(Currency.getAvailableCurrencies());

        //System.out.println(LocalDate.now());
    }
}
