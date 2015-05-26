package com.projety.view.listview;

import com.projety.app.ProjetYApplication;
import com.projety.app.R;
import com.projety.model.Evenement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Djeme Mahamat on 01/05/2015.
 */
public class EventListItemBcp {

    public static final int TYPE_GROUP_HEADER = 1;
    public static final int TYPE_GROUP_ITEM = 2;

    public static final String TODAY = "Aujourd'hui";
    public static final String TOMORROW = "Demain";

    String titre;
    String lieu;
    String horaire;
    int type ;


    public EventListItemBcp(Evenement evt){
        this.titre=evt.getTitre();
        this.lieu=evt.getLieu();
        this.horaire=evt.getHoraire();
        this.type = TYPE_GROUP_ITEM ;
    }

    public EventListItemBcp(String titre){
        this.titre=titre;
        this.type = TYPE_GROUP_HEADER;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
