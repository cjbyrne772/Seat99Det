package com.seatstir.andy.eventfocus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.JsonObject;
import com.seatstir.andy.volleylib.MakeStringRequest;
import com.seatstir.andy.volleylib.VolleyResponseListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

    public class EventFocus extends Activity {
    TixAdapter   perfAdapter;
    TixData      thisPerfPicked;
    List<TixData>  ListOfPerf;
        TextView tLongDesc;
        TextView tvenue;
        TextView tvenue_address;
        TextView tage_restrictions;
        TextView tdress_code;
        int qlimit;
        int vid;

        String jstr;
     //   String urlString = "https://www.seatstir.com/ptapp/ptlogin.php";
        String urlString = "https://www.seatstir.com/ptapp/ptperf.php";


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_focus2); // holds listview
            TextView tCurrentEvent, tVenue, tVenueAddr, tAge, tDressCode;
            String sdesc;
            int eid;
          //  int qlimit;
            int myqlimit;
            Intent myintent = getIntent();
            tCurrentEvent = (TextView) findViewById(R.id.textViewEvent);
            tLongDesc = (TextView) findViewById(R.id.textViewLongDesc);
         //   tVenue = (TextView) findViewById(R.id.textViewVenue);
         //   tVenueAddr = (TextView) findViewById(R.id.textViewVenueAddr);
         //   tAge = (TextView) findViewById(R.id.textViewAge);
            tage_restrictions = (TextView) findViewById(R.id.textViewAge);
            tdress_code = (TextView) findViewById(R.id.textViewDress);
            tvenue = (TextView) findViewById(R.id.textViewVenue);
            tvenue_address = (TextView) findViewById(R.id.textViewAddress);

            tLongDesc.setText("This is where the long description goes. It is scrollable. This is where the long description goes. It is scrollable. This is where the long description goes. It is scrollable. This is where the long description goes. It is scrollable. This is where the long description goes. It is scrollable. END ");
            tLongDesc.setMovementMethod(new ScrollingMovementMethod());


            sdesc = myintent.getStringExtra("focusstr");
            eid = myintent.getIntExtra("eid", 0);
            myqlimit = myintent.getIntExtra("qlimit", 0);
            vid = myintent.getIntExtra("vid", 0);

            qlimit = myqlimit;
            tCurrentEvent.setText(sdesc);

        ListOfPerf = new ArrayList<TixData>();
            // get the event id we need
            JSONObject json=new JSONObject();
            try {
            json.put ("eid",eid );
                json.put ("vidDB", vid ); // since we got this vid from the event table (back when
                                          // when we logged in) it is the id we use to go into
                                          // the venue table to get the street address.
                json.put ("act", "perf" );

            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }


            MakeStringRequest custr = new MakeStringRequest();
// handle volley response
            VolleyResponseListener listener = new VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(EventFocus.this, "perf" + message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String response) {
                    if (response.contains("success")){
                        //   Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
                     //   Intent i = new Intent(getApplicationContext(), EventList.class);
                     //   i.putExtra("jstr", response);  // send the entire response, EventList will parse
                     //   startActivity(i);
                        ParseAndFill(response);
                        Toast.makeText(EventFocus.this, "perf1", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(EventFocus.this, "perf2", Toast.LENGTH_SHORT).show();
                }
            };
            // form the json request that the php will parse. The php will return a
            // list of performances
            custr.MakeCustomStringRequest(EventFocus.this, urlString, listener, json.toString());

    }
  @Override
        protected void onStart() {
      super.onStart();
      Log.i("OnStart ", "eventfocus");

      }

        public void ParseAndFill(String response) {
            TextView tCurrentEvent;

            // First, get the data out of response and put it into the global
            // List Of performances
            // seems ok here   resactAdapter = new ResactAdapter(this, R.layout.custom_res, ListOfRes);  // goes to onCreate
            //tLongDesc.setText("fjhdfkshdk fhksdhfkshd kfhskdhfkshdfkhsdk fhskdhfk sdhfks jhd fksjhdd fkjshdf ksjh kjbbb kknknknk knknknknk kjnkjnknknk knknknkjnk nknkjnkjnk nknknknknkn kknknkk knknknkn kknknjkkjhkjhkh xxxxxxxxxxxxxxxxx");

            try {
                JSONObject jobjTop = new JSONObject(response);
                // first get the various eventinfo fields
                JSONArray jsPerfList = jobjTop.getJSONArray("eventinfo");
                JSONObject c = jsPerfList.getJSONObject(0);
                tLongDesc.setText( c.getString("longDesc") );
                String v = c.getString("venue");
                String va = c.getString("venue_address");
                String age = c.getString("age_restrictions");
                String dress = c.getString("dress_code");
                tvenue.setText(v);
                tvenue_address.setText(va);
                tage_restrictions.setText(age);
                tdress_code.setText(dress);


                //then get the individual performances
                jsPerfList = jobjTop.getJSONArray("event");
                ListOfPerf.clear();  // just in case some old list elements were hanging around

                int length = jsPerfList.length();
                Log.i("parse and fill", "rcount" + length);
                for (int ic = 0; ic < length; ic++) {
                    c = jsPerfList.getJSONObject(ic);
                    String d = c.getString("sdate");
                    String t = c.getString("stime");
                    int pid = c.getInt("pid");
                    int qavail = c.getInt("allocated") - c.getInt("txqty");
                  //  int q = c.getInt("qty");
                    TixData itemPlaceholder = new TixData( d+ " "+t, pid, qlimit, qavail ); // add a json constructor later new Resactdata(c)
                    ListOfPerf.add(itemPlaceholder);
                }
                for (int ic = 0; ic < 6; ic++) {

                    //  int q = c.getInt("qty");
                    TixData itemPlaceholder = new TixData( "1-1-34", 7878, qlimit, 6 ); // add a json constructor later new Resactdata(c)
                 //   ListOfPerf.add(itemPlaceholder);
                }
                //   myEvents.setAdapter(new ResactAdapter(this,testContents);

            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }


            //////////////////////////////////// end of new res filler
            //ok data is now in ListOfRes.
            perfAdapter = new TixAdapter(EventFocus.this, R.layout.tix_layout,ListOfPerf, qlimit);

            ListView possibleEvents = (ListView) findViewById(R.id.listViewPerformances);


            possibleEvents.setAdapter(perfAdapter);

            // these 3 lines now get moved out, so we can repopulate List Of Res without
            // getting a new adapter

            //  ListView possibleRes = (ListView) findViewById(R.id.listView); // goes to onCreate
            //  resactAdapter = new ResactAdapter(this, R.layout.custom_res, ListOfRes);  // goes to onCreate
            //  possibleRes.setAdapter(resactAdapter);  // is called after Parse and Fill is called


        }


    }
