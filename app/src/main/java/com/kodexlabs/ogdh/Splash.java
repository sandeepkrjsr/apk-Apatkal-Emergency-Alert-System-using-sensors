package com.kodexlabs.ogdh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 1505560 on 29-Oct-17.
 */

public class Splash extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Button dashboard = (Button)findViewById(R.id.dashboard);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText aadhar = (EditText)findViewById(R.id.aadhar);
                String getAadhar =  aadhar.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
                DatabaseReference newnodes = databaseReference.push();
                newnodes.setValue(getAadhar);

                Intent intent = new Intent(getBaseContext(), Favourites.class);
                startActivity(intent);
            }
        });
    }
}
