package com.projety.model;

import java.util.Date;

/**
 * Created by Djeme Mahamat on 05/04/2015.
 */
public class Stage extends  Evenement{

    public Stage(String libelle){
        super(libelle);
    }

    public Stage(String titre, String organisateur, Date dateDebut, Date dateFin) {
        super(titre, organisateur, dateDebut, dateFin);
    }
}
