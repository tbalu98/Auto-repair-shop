package entities;


import javax.persistence.*;

@Entity(name = "ugyfel")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "telefonszam")
    private String telephoneNumber;

    @Column(name = "nev")
    private String name;

    @Column(name = "lakcim")
    private String address;

    public Customer(String name, String telephoneNumber, String address) {
        this.telephoneNumber = telephoneNumber;
        this.name = name;
        this.address = address;
    }

    public Customer(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}