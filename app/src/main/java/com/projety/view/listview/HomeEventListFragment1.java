package com.projety.view.listview;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projety.app.R;
import com.projety.model.Evenement;
import com.projety.model.Soiree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Djeme Mahamat on 22/03/2015.
 */
public class HomeEventListFragment1 extends ListFragment {

    public static final String ARG_OBJECT= "object";
    public static final int NB_MAX_ELT_GROUP=6 ;
    private HashMap<String, ArrayList<Evenement>> eventsMapList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.listfragment_home, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
        final String[] items = getResources().getStringArray(R.array.dance_array);
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        setListAdapter(aa);
         **/

        /**
        eventsMapList = setDataEvent();
        ArrayList<Model> mlistModels = groupEventConverter(eventsMapList);
        HomeEventAdapter aa=new HomeEventAdapter(getActivity(), mlistModels);
        setListAdapter(aa);
         **/

    }




    private HashMap<String, ArrayList<Evenement>> setDataEvent(){


        HashMap<String, ArrayList<Evenement>> mapList=new HashMap<String, ArrayList<Evenement>>();

        /**
        ArrayList<Evenement> evtEvt = new ArrayList<Evenement>();
        evtEvt.add(new Evenement("Paris Jazz Roots"));
        evtEvt.add(new Evenement("Swinging Paris"));
        mapList.put("Evenements",evtEvt);

        ArrayList<Evenement> evtStage = new ArrayList<Evenement>();
        evtStage.add(new Stage("Stage Lindy Mélanie et Thomas"));
        evtStage.add(new Stage("Stage Lindy BrotherSwing"));
        evtStage.add(new Stage("Stage Lindy ChatonSwing"));
        evtStage.add(new Stage("Stage Lindy SourisSwing"));
        evtStage.add(new Stage("Stage Lindy Djimé"));
        mapList.put("Stages",evtStage);
 **/

        ArrayList<Evenement> evtSoiree = new ArrayList<Evenement>();
        evtSoiree.add(new Soiree("Soirée Temple du swing"));
        evtSoiree.add(new Soiree("Soirée Swingmania"));
        evtSoiree.add(new Soiree("Soirée  ChatonSwing"));
        evtSoiree.add(new Soiree("Soirée ShakeThatSwing"));
        evtSoiree.add(new Soiree("Soirée SwingitSister"));
        evtSoiree.add(new Soiree("Soirée SwingitSister 2"));
        evtSoiree.add(new Soiree("Soirée SwingitSister 3"));
        mapList.put("Aujourd'hui",evtSoiree);

        ArrayList<Evenement> evtSoiree2 = new ArrayList<Evenement>();
        evtSoiree2.add(new Soiree("Soirée Temple du swing"));
        evtSoiree2.add(new Soiree("Soirée Swingmania"));
        evtSoiree2.add(new Soiree("Soirée  ChatonSwing"));
        mapList.put("Demain",evtSoiree2);

        ArrayList<Evenement> evtSoiree3 = new ArrayList<Evenement>();
        evtSoiree3.add(new Soiree("Soirée Temple du swing"));
        evtSoiree3.add(new Soiree("Soirée Swingmania"));
        evtSoiree3.add(new Soiree("Soirée  ChatonSwing"));
        mapList.put("30 acril",evtSoiree3);

        return mapList;
    }


    public ArrayList<Model> groupEventConverter(Map<String,ArrayList<Evenement>> eventsMapList){

        ArrayList<Model> mModels=new ArrayList<Model>();

         for(String st:eventsMapList.keySet()){
            mModels.add(new Model(st, Model.TYPE_GROUP_HEADER));
                int cpt=0;
             for(Evenement evt:eventsMapList.get(st)){
                mModels.add(new Model(evt.getLibelle(), Model.TYPE_GROUP_ITEM));
                 cpt ++;
                 if(cpt==NB_MAX_ELT_GROUP+1) {
                     mModels.add(new Model("Tous les "+st+ " ", Model.TYPE_GROUP_FOOTER));
                     break;
                 }
             }

         }

        return mModels;
    }


    public class Model {
        private String title;
        private int type ;

        public static final int TYPE_GROUP_HEADER = 1;
        public static final int TYPE_GROUP_ITEM = 2;
        public static final int TYPE_GROUP_FOOTER = 3;

        public  Model(String title, int type) {
            this.title = title;
            this.type = type;
        }


        public int getType(){
            return type;
        }

        public String getTitle(){
            return title;
        }



    }







}
