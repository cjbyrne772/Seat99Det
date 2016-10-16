package com.seatstir.andy.eventfocus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by fred on 9/1/2015.
 */
class TixAdapter extends ArrayAdapter<TixData> {

    Context context;

    public TixAdapter(Context context, int resourceId,
                         List<TixData> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtEvent;
        TextView txtVenue;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        TixData tixData = getItem(position);
        LayoutInflater fasInflater = LayoutInflater.from(getContext());
        View customView = fasInflater.inflate(R.layout.tix_layout, parent, false); // xml for item
        NumberPicker PickTix;

        SimpleDateFormat formatter = new SimpleDateFormat("M-dd-yyyy");
        String dateInString = "10-20-2015";
        TextView perfDate = (TextView) customView.findViewById(R.id.textDate);
        PickTix = (NumberPicker) customView.findViewById(R.id.numberPicker);
        PickTix.setMaxValue(4);
        perfDate.setText(dateInString);
        Button button = (Button) customView.findViewById(R.id.button);
        final int iperfID = tixData.getperfID();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Toast.makeText(getContext(), "Button perf " + iperfID, Toast.LENGTH_SHORT).show();
            }
        });
        return customView;
    }
}
