package guidemo;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import org.pmw.tinylog.Logger;

public class ExampleOfTableView implements Initializable {
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person,String> firstNameColumn;
    @FXML private TableColumn<Person,String> lastNameColumn;
    @FXML private TableColumn<Person, LocalDate> birthdayColumn;

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker birthdayDatePicker;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
            birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person,LocalDate>("birthday"));

            tableView.setItems(getPeople());

            tableView.setEditable(true);
            firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void deleteButtonPushed(){
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();
        Logger.info(selectedRows);
        for(Person person: selectedRows){
            Logger.info("Törlok"+person.toString());
            allPeople.remove(person);
        }
    }
    public void newPersonButtonPushed(){
        Person newPerson = new Person(firstNameTextField.getText()
                ,lastNameTextField.getText(),birthdayDatePicker.getValue());
        tableView.getItems().add(newPerson);
    }
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/java/guidemo/FXMLDocument.fxml").toUri().toURL();
        Parent tableViewParent = FXMLLoader.load(url);
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public ObservableList<Person> getPeople(){
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra",LocalDate.of(1915, Month.DECEMBER,12)));
        people.add(new Person("Rebecca","Fergusson",LocalDate.of(1986, Month.JULY,21)));
        people.add(new Person("Mr.","T",LocalDate.of(1952, Month.MAY,21)));

        return people;
    }
    public void changeFirstNameCellEvent(TableColumn.CellEditEvent editedCell){
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(editedCell.getNewValue().toString());
    }

    public void changeLastNameCellEvent(TableColumn.CellEditEvent editedCell){
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(editedCell.getNewValue().toString());
    }

}
