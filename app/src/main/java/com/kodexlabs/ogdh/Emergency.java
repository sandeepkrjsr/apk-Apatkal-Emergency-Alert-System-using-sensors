package com.kodexlabs.ogdh;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by 1505560 on 28-Oct-17.
 */

public class Emergency extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);

        final TextView timing = (TextView) findViewById( R.id.timing );
        new CountDownTimer(30000, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                timing.setText(""+String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                MediaPlayer player = MediaPlayer.create(getBaseContext(), Settings.System.DEFAULT_RINGTONE_URI);
                player.start();

                Camera camera = Camera.open();
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                camera.startPreview();

                String messageToSend = "Emergency";
                String number = "8235365517";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
            }
        }.start();

        Button callnow = (Button) findViewById(R.id.callnow);
        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSend = "Emergency";
                String number = "8235365517";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSend = "False Alarm";
                String number = "8235365517";
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
            }
        });
    }
}