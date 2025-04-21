package org.example.gestionpfe;

import java.util.LinkedList;

public class Jury {
    private int id;
    private Enseignant president;
    private Enseignant rapporteur;
    private Enseignant examinateur;
    private LinkedList<Encadreur_Professionnel> invites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enseignant getPresident() {
        return president;
    }

    public void setPresident(Enseignant president) {
        this.president = president;
    }

    public Enseignant getRapporteur() {
        return rapporteur;
    }

    public void setRapporteur(Enseignant rapporteur) {
        this.rapporteur = rapporteur;
    }

    public Enseignant getExaminateur() {
        return examinateur;
    }

    public void setExaminateur(Enseignant examinateur) {
        this.examinateur = examinateur;
    }

    public LinkedList<Encadreur_Professionnel> getInvites() {
        return invites;
    }

    public void setInvites(LinkedList<Encadreur_Professionnel> invites) {
        this.invites = invites;
    }
}
