package org.example.gestionpfe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class PFE {
    private int id;
    private StringProperty idProperty;
    private String sujet;
    private String type;
    private int etudiant1;
    private int etudiant2;
    private int encadreurPedagogique;
    private int president;
    private int rapporteur;
    private int examinateur;
    private Date date;
    private String heure;
    private String salle;
    private String valide;
    private String note;
    //private String resultat;

    public PFE(int id, String sujet, String type, int etudiant1, int etudiant2, int encadreurPedagogique, int president, int rapporteur, int examinateur, Date date, String heure, String salle, String note, String valide) {
        this.id = id;
        //this.idProperty = idProperty;
        this.sujet = sujet;
        this.type = type;
        this.etudiant1 = etudiant1;
        this.etudiant2 = etudiant2;
        this.encadreurPedagogique = encadreurPedagogique;
        this.president = president;
        this.rapporteur = rapporteur;
        this.examinateur = examinateur;
        this.date = date;
        this.heure = heure;
        this.salle = salle;
        this.valide = valide;
        this.note = note;
        //this.resultat = resultat;
    }

    //this.id = String.valueOf(new SimpleStringProperty(id));
    public PFE(){}

    public int getId() {
        return id;
    }
    public StringProperty idProperty() {
        return (new SimpleStringProperty(Integer.toString(this.id)));
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEtudiant1() {
        return etudiant1;
    }

    public void setEtudiant1(int etudiant1) {
        this.etudiant1 = etudiant1;
    }

    public int getEtudiant2() {
        return etudiant2;
    }

    public void setEtudiant2(int etudiant2) {
        this.etudiant2 = etudiant2;
    }

    public int getEncadreurPedagogique() {
        return encadreurPedagogique;
    }

    public void setEncadreurPedagogique(int encadreurPedagogique) {
        this.encadreurPedagogique = encadreurPedagogique;
    }

    public String getIdProperty() {
        return idProperty.get();
    }

    public void setIdProperty(String idProperty) {
        this.idProperty.set(idProperty);
    }

    public int getPresident() {
        return president;
    }

    public void setPresident(int president) {
        this.president = president;
    }

    public int getRapporteur() {
        return rapporteur;
    }

    public void setRapporteur(int rapporteur) {
        this.rapporteur = rapporteur;
    }

    public int getExaminateur() {
        return examinateur;
    }

    public void setExaminateur(int examinateur) {
        this.examinateur = examinateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /*public String getResultat() {
        return resultat;
    }*/
}
