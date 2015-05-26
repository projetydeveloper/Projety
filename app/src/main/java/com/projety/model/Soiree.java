package com.projety.model;

import java.util.Date;

/**
 * Created by Djeme Mahamat on 05/04/2015.
 */
public class Soiree extends Evenement {

    public Soiree(String libelle) {
        super(libelle);
    }


    public Soiree(String titre, String lieu, Date dateDebut, Date dateFin) {
        super(titre, lieu, dateDebut, dateFin);
    }

    public Soiree(String titre, String organisateur, String prix, String lieu, String address, Date dateDebut, Date dateFin, String desc,
    String photo) {
        super(titre, organisateur, prix, lieu, address, dateDebut, dateFin, desc, photo);
    }


}
