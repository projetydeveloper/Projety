package com.projety.model;

import java.util.Date;

/**
 * Created by Djeme Mahamat on 05/04/2015.
 */
public class Evenement {

    String libelle ;
    String titre;
    String organisateur;
    String description;
    String date;
    String horaire;
    String lieu;
    String addresse ;
    String prix;
    Date dateDebut;
    Date dateFin;
    String photo;

    public Evenement(String libelle){
        this.libelle=libelle;
    }


    public Evenement(String titre, String lieu, Date dateDebut, Date dateFin){
        this.titre=titre;
        this.lieu=lieu;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
    }

    public Evenement(String titre, String organisateur, String prix, String lieu, String address, Date dateDebut, Date dateFin,
                  String desc, String photo) {
        this.titre=titre;
        this.lieu=lieu;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.organisateur=organisateur;
        this.prix=prix;
        this.addresse=address;
        this.photo =photo ;
        this.description=desc;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
