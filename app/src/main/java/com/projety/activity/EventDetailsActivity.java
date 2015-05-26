package com.projety.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projety.app.R;

public class EventDetailsActivity extends ActionBarActivity {

    Toolbar toolbar;
    ImageView evt_img, like_icon, calendar_icon, place_icon, price_icon ;
    TextView nb_like_txt, title_txt, owner_txt, date_txt, hour_txt, place_txt, adress_txt, price_txt, desc_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.evt_details_toolbar);
        setSupportActionBar(toolbar);

        evt_img = (ImageView)findViewById(R.id.evt_details_picture);
        like_icon= (ImageView)findViewById(R.id.evt_details_pic_like);
        calendar_icon = (ImageView)findViewById(R.id.evt_details_pic_calendar);
        place_icon = (ImageView)findViewById(R.id.evt_details_pic_place);
        price_icon = (ImageView)findViewById(R.id.evt_details_pic_price);

        nb_like_txt = (TextView)findViewById(R.id.evt_details_nb_like);
        title_txt = (TextView)findViewById(R.id.evt_details_title);
        owner_txt = (TextView)findViewById(R.id.evt_details_owner);
        date_txt= (TextView)findViewById(R.id.evt_details_info_date);
        hour_txt = (TextView)findViewById(R.id.evt_details_info_hour);
        place_txt = (TextView)findViewById(R.id.evt_details_info_place);
        adress_txt = (TextView)findViewById(R.id.evt_details_info_address);
        price_txt  = (TextView)findViewById(R.id.evt_details_info_price);
        desc_txt = (TextView)findViewById(R.id.evt_details_desc);

        Toast.makeText(this, "toto" , Toast.LENGTH_SHORT).show();


        Bundle evtBundle = getIntent().getBundleExtra("event_item");
        title_txt.setText(evtBundle.getString("title"));
        owner_txt.setText(evtBundle.getString("organisateur"));
        date_txt.setText(evtBundle.getString("date"));
        hour_txt.setText(evtBundle.getString("hour"));
        place_txt.setText(evtBundle.getString("place"));
        adress_txt.setText(evtBundle.getString("adress"));
        price_txt.setText(evtBundle.getString("price"));
        desc_txt.setText(evtBundle.getString("desc"));







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
