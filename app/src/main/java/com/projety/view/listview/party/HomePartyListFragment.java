package com.projety.view.listview.party;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.projety.activity.EventDetailsActivity;
import com.projety.api.ApiClient;
import com.projety.app.ProjetYApplication;
import com.projety.app.R;
import com.projety.model.Evenement;
import com.projety.model.Parties;
import com.projety.model.Party;
import com.projety.view.listview.EventListItem;

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
public class HomePartyListFragment extends ListFragment {

    private static final int RUNNING_LOW_ON_DATA_THRESHOLD = 10;
    private static final int ITEMS_PER_PAGE = 50;

    //ArrayList<PartyListItem> partiesListItems;

    //private ProgressBar mProgressBar;
    private boolean mIsDownloadInProgress = false;
    /* Holds the state information for this activity. */
    private ActivityState mState = new ActivityState();


    HomePartyAdapter mHmAdapter;

    private static class ActivityState {

        private int nextPage = 0;
        private ArrayList<PartyListItem> partiesItemsData = new ArrayList<PartyListItem>();

    }


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

        Log.i("----------",""+ProjetYApplication.getCache().get("toto"));

        // mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        //Bundle bundle = getArguments();
        // int eventType = bundle.getInt(ARG_EVENT_TYPE, -1);
        //ArrayList<Evenement> eventList;
        // if (eventType == 0) {
        //    eventList = getSampleDataSoirees();
        // } else {
        //    eventList = new ArrayList<Evenement>();
        // }


        //ArrayList<EventListItem> eventListItems = constructEventItemList(eventList);
        //HomeEventAdapter mHmAdapter = new HomeEventAdapter(getActivity(), eventListItems);
        //setListAdapter(mHmAdapter);


       // partiesListItems = constructPartyItemList(mState.partiesData);

        mHmAdapter = new HomePartyAdapter(getActivity(), mState.partiesItemsData);
        setListAdapter(mHmAdapter);
        getListView().setOnScrollListener(mScrollListener);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Kick off first download
        if (mState.nextPage == 0) {
            downloadData(mState.nextPage);
        }    }

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

    public ArrayList<PartyListItem> constructPartyItemList(List<Party> parties) {

        ArrayList<PartyListItem> partiesListItems = new ArrayList<PartyListItem>();

        String lastSavedDate = null;

        for (Party party : parties) {

            String evtDate = getDateTitle(party.getStartDate());

            if (lastSavedDate == null || !lastSavedDate.equals(evtDate)) {
                partiesListItems.add(new PartyListItem(evtDate));
            }

            lastSavedDate = evtDate;

            partiesListItems.add(new PartyListItem(party));

        }

        return partiesListItems;
    }


    private String getDateTitle(long mDateLong) {

        Date mDate = null;

        try {

           //Log.i("testttttt",""+mDateLong);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            String dateInString = "" + mDateLong;
            mDate = formatter.parse(dateInString);
           // Log.i("testttttt",""+mDate.toString());


        } catch (Exception e) {
            e.printStackTrace();

        }


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
            return ProjetYApplication.getInstance().getString(R.string.today);

        if (c3.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c3.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
            return ProjetYApplication.getInstance().getString(R.string.tomorrow);

        return (new SimpleDateFormat("EEEE d MMM")).format(mDate);

    }


    private String getDateTitle(String dateInString) {

        Date mDate = null;

        try {

            //2015-09-21T19:30:00+02:00
            //Log.i("testttttt",""+mDateLong);
            Log.i("testttttt",""+dateInString.toString());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            //String dateInString = "" + mDate1;
            mDate = formatter.parse(dateInString);
            Log.i("testttttt",""+mDate.toString());


        } catch (Exception e) {
            e.printStackTrace();

        }


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
            return "today" ;
                   // ProjetYApplication.getInstance().getString(R.string.today);

        if (c3.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c3.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
            return "tomorrow" ;
                    //ProjetYApplication.getInstance().getString(R.string.tomorrow);

        return (new SimpleDateFormat("EEEE d MMM")).format(mDate);

    }

    private void downloadData(final int pageNumber) {
        if (!mIsDownloadInProgress) {

            mIsDownloadInProgress = true;

            Log.i("MyActivity", "Begin download ");

            // mProgressBar.setVisibility(View.VISIBLE);

            // ApiClient.getProjetyApiClient().getParties(ITEMS_PER_PAGE, pageNumber * ITEMS_PER_PAGE, new Callback<List<Party>>() {

            ApiClient.getProjetyApiClient().getParties(ITEMS_PER_PAGE,pageNumber * ITEMS_PER_PAGE,new Callback<Parties>() {
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
            mState.partiesItemsData.addAll(constructPartyItemList(partiesData.getParties()));

            ProjetYApplication.getCache().put("toto",partiesData);
            // Tell the adapter that it needs to rerender
             mHmAdapter.notifyDataSetChanged();

            // Done loading; remove loading indicator
            //mProgressBar.setVisibility(View.GONE);

            // Keep track of what page to download next
            mState.nextPage++;
        }

        //mIsDownloadInProgress = false;
    }

    /**
     * Scroll-handler for the ListView which can auto-load the next page of data.
     */
    private AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // Nothing to do
            Log.i("***onScrollStateChanged",""+scrollState);

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            // Detect if the ListView is running low on data
            Log.i("***onScroll",""+totalItemCount);

            if (totalItemCount > 0 && totalItemCount - (visibleItemCount + firstVisibleItem) <= RUNNING_LOW_ON_DATA_THRESHOLD) {
                Log.i("***onScroll",""+mState.nextPage);

                downloadData(mState.nextPage);
            }
        }
    };


    private Date setEvtDate(int month, int day, int hour, int min) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        return cal.getTime();
    }



}
