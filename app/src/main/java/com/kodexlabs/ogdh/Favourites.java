package com.kodexlabs.ogdh;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 1505560 on 11-Jan-18.
 */

public class Favourites extends Activity {

    private static SharedPreferences preferences;
    private String prefName = "MyPref";
    private static final String CONTACT1 = "";
    private static final String CONTACT2 = "";
    private static final String CONTACT3 = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        preferences = getSharedPreferences(prefName, MODE_PRIVATE);

        final EditText contact1 = (EditText)findViewById(R.id.contact1);
        final EditText contact2 = (EditText)findViewById(R.id.contact2);
        final EditText contact3 = (EditText)findViewById(R.id.contact3);

        Button favourites = (Button)findViewById(R.id.favourites);
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(CONTACT1, ""+contact1.getText().toString());
                editor.putString(CONTACT2, ""+contact2.getText().toString());
                editor.putString(CONTACT3, ""+contact3.getText().toString());
                editor.commit();

                Intent intent = new Intent(getBaseContext(), Mainmenu.class);
                startActivity(intent);
            }
        });
    }
}
