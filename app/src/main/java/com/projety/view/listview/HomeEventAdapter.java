package com.projety.view.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projety.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Djeme Mahamat on 05/04/2015.
 */
public class HomeEventAdapter extends ArrayAdapter<EventListItem> {

    private final Context context;
    private List<EventListItem> evtList;


    public HomeEventAdapter(Context context, ArrayList<EventListItem> evtList) {

        super(context, R.layout.listview_event_item, evtList);

        this.context = context;
        this.evtList = evtList;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = null;


        if (evtList.get(position).getType() == EventListItemBcp.TYPE_GROUP_ITEM) {

            rowView = inflater.inflate(R.layout.listview_event_item, parent, false);

            TextView titleView1 = (TextView) rowView.findViewById(R.id.listvw_item_text1);
            titleView1.setText(evtList.get(position).getEvt().getTitre());

            TextView titleView2 = (TextView) rowView.findViewById(R.id.listvw_item_text2);
            titleView2.setText(evtList.get(position).getEvt().getLieu());

            TextView titleView3 = (TextView) rowView.findViewById(R.id.listvw_item_text3);
            titleView3.setText(evtList.get(position).getEvt().getHoraire());

        } else if (evtList.get(position).getType() == EventListItemBcp.TYPE_GROUP_HEADER) {

            rowView = inflater.inflate(R.layout.listview_event_group_header, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.header);
            titleView.setText(evtList.get(position).getHeaderTitle());
        }

        // 5. return rowView
        return rowView;
    }







}