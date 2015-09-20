package com.projety.view.listview.party;

import com.projety.model.Evenement;
import com.projety.model.Party;

/**
 * Created by Djeme Mahamat on 01/05/2015.
 */
public class PartyListItem {

    public static final int TYPE_GROUP_HEADER = 1;
    public static final int TYPE_GROUP_ITEM = 2;

    //public static final String TODAY = "Aujourd'hui";
    //public static final String TOMORROW = "Demain";

    public Party party;
    public int type;
    public String headerTitle;


    public PartyListItem(Party party) {

        this.type = TYPE_GROUP_ITEM;
        this.party = party;

    }

    public PartyListItem(String headerTitle) {

        this.headerTitle = headerTitle;
        this.type = TYPE_GROUP_HEADER;
    }


    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }


}
