package guidemo;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Person {

    private SimpleStringProperty firstName,lastName, location;
    private LocalDate birthday;

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public Person(SimpleStringProperty firstName, SimpleStringProperty lastName, SimpleStringProperty location, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.birthday = birthday;
    }
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
    }



    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

