package com.kodexlabs.ogdh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 1505560 on 29-Oct-17.
 */

public class Nearestdata extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearestdata);

        Bundle bundle = getIntent().getExtras();
        String clicked = bundle.getString("clicked");

        TextView getinfo = (TextView)findViewById(R.id.getinfo);
        Button call = (Button)findViewById(R.id.call);

        if (clicked.compareTo("Hospital")==0){
            getinfo.setText("Nearest "+clicked+"s");
            call.setText("Call Ambulance");
        }else if (clicked.compareTo("Blood Bank")==0){
            getinfo.setText("Nearest "+clicked+"s");
            call.setText("Call "+clicked);
        }else if (clicked.compareTo("Police Station")==0){
            getinfo.setText("Nearest "+clicked+"s");
            call.setText("Call "+clicked);
        }else if (clicked.compareTo("Fire Brigade")==0){
            getinfo.setText("Nearest "+clicked+"s");
            call.setText("Call "+clicked);
        }
    }
}
