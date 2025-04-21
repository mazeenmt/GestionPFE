package org.example.gestionpfe;
import java.time.LocalDate;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String email;
    private int tel;
    private String cycle;
    private String specialite;
    private int niveau;

    public Etudiant() {

    }

    public Etudiant(int id, String nom, String prenom, LocalDate date_naissance, String email, int tel, String cycle, String specialite, int niveau) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.tel = tel;
        this.cycle = cycle;
        this.specialite = specialite;
        this.niveau = niveau;
    }

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

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
