package com.projety.view.listview;

import com.projety.model.Evenement;

/**
 * Created by Djeme Mahamat on 01/05/2015.
 */
public class EventListItem {

    public static final int TYPE_GROUP_HEADER = 1;
    public static final int TYPE_GROUP_ITEM = 2;

    public static final String TODAY = "Aujourd'hui";
    public static final String TOMORROW = "Demain";

    public Evenement evt;
    public int type;
    public String headerTitle;


    public EventListItem(Evenement evt) {

        this.type = TYPE_GROUP_ITEM;
        this.evt = evt;

    }

    public EventListItem(String headerTitle) {

        this.headerTitle = headerTitle;
        this.type = TYPE_GROUP_HEADER;
    }


    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public Evenement getEvt() {
        return evt;
    }

    public void setEvt(Evenement evt) {
        this.evt = evt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
