package com.kodexlabs.ogdh;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Service implements SensorEventListener{

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private float vibrateThreshold = 0;

    public Vibrator v;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(),"Running...",Toast.LENGTH_SHORT).show();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // Accelerometer Available

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            // Accelerometer Not Available
        }
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        return START_STICKY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        displayCurrentValues();
        displayMaxValues();

        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        // if the change is below 2, it is just plain noise
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if ((deltaZ > vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
            v.vibrate(50);
        }
    }

    public void displayCurrentValues() {
        if ( (deltaX>25) || (deltaY>25) || (deltaZ>25) )
            FirebaseDatabase(Float.toString(deltaX),Float.toString(deltaY),Float.toString(deltaZ));
    }

    public void displayMaxValues() {
        if (deltaY > 15){
            EmergencyAlert();
        }
    }

    private void EmergencyAlert() {
        /*Intent intent = new Intent(this, Emergency.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        onBind(intent);
        startActivity(intent);*/
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        i.setComponent(new ComponentName(getApplicationContext().getPackageName(), Splash.class.getName()));
    }

    public void FirebaseDatabase(String x,String y, String z){
        Long tsLong = System.currentTimeMillis();
        String t = tsLong.toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
        DatabaseReference newnodes = databaseReference.push();
        newnodes.child("x").setValue(x);
        newnodes.child("y").setValue(y);
        newnodes.child("z").setValue(z);
        newnodes.child("t").setValue(t);
    }

    @Override
    public IBinder onBind(Intent intent) {
        startActivity(intent);
        return null;
    }
}
