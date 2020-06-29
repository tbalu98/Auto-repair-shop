package entities;

import java.time.LocalDate;

public class BefejezendoSzereles {
    private String Rendszam;
    private LocalDate SzerelesKezdete;

    public BefejezendoSzereles(String rendszam, LocalDate szerelesKezdete) {
        Rendszam = rendszam;
        SzerelesKezdete = szerelesKezdete;
    }

    public String getRendszam() {
        return Rendszam;
    }

    public void setRendszam(String rendszam) {
        Rendszam = rendszam;
    }

    public LocalDate getSzerelesKezdete() {
        return SzerelesKezdete;
    }

    public void setSzerelesKezdete(LocalDate szerelesKezdete) {
        SzerelesKezdete = szerelesKezdete;
    }
}
