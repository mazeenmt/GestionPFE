package org.example.gestionpfe;

import java.time.LocalDate;

public class Planification {
    private LocalDate date;
    private int heure;
    private String salle;
    public Planification(LocalDate date, int heure, String salle) {
        this.date = date;
        this.heure = heure;
        this.salle = salle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
