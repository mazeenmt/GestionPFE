package org.example.gestionpfe;

import java.time.LocalDate;

public class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String email;
    private int tel;
    private String Departement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return date_naissance;
    }

    public void setDateNaissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String departement) {
        Departement = departement;
    }
}
