package com.kodexlabs.ogdh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 1505560 on 29-Oct-17.
 */

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button hospital = (Button)findViewById(R.id.hospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Nearestdata.class);
                intent.putExtra("clicked", "Hospital");
                startActivity(intent);
            }
        });

        Button bloodbank = (Button)findViewById(R.id.bloodbank);
        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Nearestdata.class);
                intent.putExtra("clicked", "Blood Bank");
                startActivity(intent);
            }
        });

        Button policestation = (Button)findViewById(R.id.policestation);
        policestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Nearestdata.class);
                intent.putExtra("clicked", "Police Station");
                startActivity(intent);
            }
        });

        Button firebrigade = (Button)findViewById(R.id.firebrigade);
        firebrigade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Nearestdata.class);
                intent.putExtra("clicked", "Fire Brigade");
                startActivity(intent);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Hello",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void DialogBox() {
        /*final CharSequence choices[] = new CharSequence[] {"Hospitals","Blood Banks","Police Stations","Fire Brigade"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
        builder.setTitle("Select Options");
        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), Nearestdata.class);
                intent.putExtra("clicked", choices[which]);
                startActivity(intent);
            }
        });
        builder.create().show();*/
    }
}
