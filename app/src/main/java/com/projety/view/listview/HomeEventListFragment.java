package com.projety.view.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.projety.activity.EventDetailsActivity;
import com.projety.api.ApiClient;
import com.projety.app.R;
import com.projety.model.Evenement;
import com.projety.model.Parties;
import com.projety.model.Party;
import com.projety.model.Soiree;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Djeme Mahamat on 22/03/2015.
 */
public class HomeEventListFragment extends ListFragment {

    public static final String ARG_EVENT_TYPE = "EVENT_TYPE";
    private static final int RUNNING_LOW_ON_DATA_THRESHOLD = 5;
    private static final int ITEMS_PER_PAGE = 10;
    ArrayList<EventListItem> eventListItems;
    //private ProgressBar mProgressBar;
    private boolean mIsDownloadInProgress = false;
    /* Holds the state information for this activity. */
    private ActivityState mState = new ActivityState();


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


        if (getActivity().getLastCustomNonConfigurationInstance() instanceof ActivityState) {
            mState = (ActivityState) getActivity().getLastCustomNonConfigurationInstance();
        }

       // mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        Bundle bundle = getArguments();
        int eventType = bundle.getInt(ARG_EVENT_TYPE, -1);
        ArrayList<Evenement> eventList;
        if (eventType == 0) {
            eventList = getSampleDataSoirees();
        } else {
            eventList = new ArrayList<Evenement>();
        }


        //ArrayList<EventListItem> eventListItems = constructEventItemList(eventList);
        //HomeEventAdapter mHmAdapter = new HomeEventAdapter(getActivity(), eventListItems);
        //setListAdapter(mHmAdapter);


       // downloadData(1);

        eventListItems = constructEventItemList(eventList);
        HomeEventAdapter mHmAdapter = new HomeEventAdapter(getActivity(), eventListItems);
        setListAdapter(mHmAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        EventListItem EvtItem = (EventListItem) l.getItemAtPosition(position);

        Intent intent = new Intent(getActivity(), EventDetailsActivity.class);

        Bundle objBundle = new Bundle();
        objBundle.putString("title", EvtItem.getEvt().getTitre());
        objBundle.putString("organisateur", "BY " + EvtItem.getEvt().getOrganisateur());
        String[] evtDate = formatEvtDate(EvtItem.getEvt());
        objBundle.putString("date", evtDate[0]);
        objBundle.putString("hour", evtDate[1]);
        objBundle.putString("place", EvtItem.getEvt().getLieu());
        objBundle.putString("adress", EvtItem.getEvt().getAddresse());
        objBundle.putString("price", EvtItem.getEvt().getPrix());
        objBundle.putString("desc", EvtItem.getEvt().getDescription());
        intent.putExtra("event_item", objBundle);

        startActivity(intent);

    }

    public String[] formatEvtDate(Evenement evt) {


        Date dateDeb = evt.getDateDebut();
        Date dateFin = evt.getDateFin();
        String[] evtDate = new String[2];

        if (dateDeb == null || dateFin == null) return null;

        String strDateDeb = (new SimpleDateFormat("EEEE d MMM")).format(dateDeb);
        String strDateFin = (new SimpleDateFormat("EEEE d MMM")).format(dateFin);

        if (strDateDeb.equals(strDateFin)) evtDate[0] = strDateDeb;
        else {
            evtDate[0] = strDateDeb + " - " + strDateFin;
        }

        evtDate[1] = (new SimpleDateFormat("d MMM h:m")).format(dateDeb) + " - " + (new SimpleDateFormat("d MMM h:m")).format(dateFin);

        return evtDate;
    }

    public ArrayList<EventListItem> constructEventItemList(ArrayList<Evenement> eventList) {

        ArrayList<EventListItem> eventListItems = new ArrayList<EventListItem>();

        String lastSavedDate = null;

        for (Evenement evt : eventList) {

            String evtDate = getDateTitle(evt.getDateDebut());

            if (lastSavedDate == null || !lastSavedDate.equals(evtDate)) {
                eventListItems.add(new EventListItem(evtDate));
            }

            lastSavedDate = evtDate;
            evt.setHoraire(evtDate);
            eventListItems.add(new EventListItem(evt));

        }

        return eventListItems;
    }


    private String getDateTitle(Date mDate) {

        if (mDate == null) return null;

        // Today
        Calendar c1 = Calendar.getInstance();

        //Tomorrow
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.DAY_OF_YEAR, 1);

        //date
        Calendar c2 = Calendar.getInstance();
        c2.setTime(mDate);

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
            return "Aujourd'hui";
        //ProjetYApplication.getInstance().getString(R.string.today);

        if (c3.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c3.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
            return "Demain";
        //ProjetYApplication.getInstance().getString(R.string.tomorrow);

        return (new SimpleDateFormat("EEEE d MMM")).format(mDate);

    }

    private void downloadData(final int pageNumber) {
        if (!mIsDownloadInProgress) {
            mIsDownloadInProgress = true;

            Log.i("MyActivity", "Begin download ");

           // mProgressBar.setVisibility(View.VISIBLE);

            // ApiClient.getProjetyApiClient().getParties(ITEMS_PER_PAGE, pageNumber * ITEMS_PER_PAGE, new Callback<List<Party>>() {


            ApiClient.getProjetyApiClient().getParties(ITEMS_PER_PAGE, pageNumber * ITEMS_PER_PAGE,new Callback<Parties>() {
                @Override
                public void success(Parties partiesData, Response response) {
                    consumeApiData(partiesData);
                    Log.i("MyActivity", "yes download " + partiesData);

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    consumeApiData(null);
                    Log.i("MyActivity", "not cool download " + retrofitError);
                    retrofitError.printStackTrace();

                }
            });
        }
        Log.i("MyActivity", "close download ");

    }

    private void consumeApiData(Parties partiesData) {
        if (partiesData != null) {
            // Add the found streams to our array to render
            mState.partiesData.addAll(partiesData.getParties());

            // Tell the adapter that it needs to rerender
            //  mAdapter.notifyDataSetChanged();

            // Done loading; remove loading indicator
            //mProgressBar.setVisibility(View.GONE);

            // Keep track of what page to download next
            mState.nextPage++;
        }

        mIsDownloadInProgress = false;
    }

    private ArrayList<Evenement> getSampleDataSoirees() {

        ArrayList<Evenement> evtSoiree = new ArrayList<Evenement>();

        evtSoiree.add(new Soiree("Grand Bal swing au Cabaret Sauvage",
                "BrotherSwing", "15 $", "La belleviloise ", "20 rue Boyer 75020",
                setEvtDate(01, 01, 19, 30), setEvtDate(01, 02, 23, 30),
                "C'est avec un grand plaisir que le grand bal swing revient au Cabaret Sauvage !\n" +
                        "Après l'édition d'octobre 2014, back in 2015 ...\n" +
                        "Initiation à la danse, dj sets et live band avec cette toute nouvelle formation Sister Sadie !!\n" +
                        "\n" +
                        "SISTER SADIE\n" +
                        "Le nouvel ensemble dansant de la place parisienne ! Sister Sadie joue le swing... Avec une rythmique qui fait s'envoler et une chanteuse (Sara Longo) au groove d'enfer! Celle-ci est accompagnée de Clément Prioul au piano, Camille Passeri à la trompette, Maurizio Congiu à la contrebasse ainsi que Baptiste Castets à batterie.\n" +
                        "\n" +
                        "S'en suivra après minuit, une soirée electroswing..\n" +
                        "\n" +
                        "Swing au Cabaret sauvage, c'est maintenant !\n" +
                        "15€",
                null));

        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise ",
                setEvtDate(04, 02, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 10, 19, 30), setEvtDate(05, 10, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 11, 19, 30), setEvtDate(05, 11, 23, 30)));
        evtSoiree.add(new Soiree("Le Grand Bal Swing de La Bellevilloise", "La belleviloise Menilmontant",
                setEvtDate(05, 13, 19, 30), setEvtDate(05, 12, 23, 30)));

        return evtSoiree;
    }

    private Date setEvtDate(int month, int day, int hour, int min) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        return cal.getTime();
    }

    private static class ActivityState {
        private int nextPage = 0;

        private List<Party> partiesData = new ArrayList<Party>();
    }


}
