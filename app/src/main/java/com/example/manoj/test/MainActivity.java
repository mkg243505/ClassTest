package com.example.manoj.test;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mPressure,mlight,mtemp,mhumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mlight=mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mtemp=mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mhumid=mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float pressure = sensorEvent.values[0];
        TextView text = (TextView) findViewById(R.id.textView2);
        float light = sensorEvent.values[0];
        TextView text1 = (TextView) findViewById(R.id.textView6);
        Sensor sensor=sensorEvent.sensor;
        float temp = sensorEvent.values[0];
        TextView text2 = (TextView) findViewById(R.id.textView4);
        float humid = sensorEvent.values[0];
        TextView text3 = (TextView) findViewById(R.id.textView8);
        if(sensor.getType()==Sensor.TYPE_PRESSURE) {

            text.setText("pressure" + pressure);
        }
        if(sensor.getType()==Sensor.TYPE_LIGHT) {

            text1.setText("Light" + light);
        }
        if(sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE) {

            text2.setText("Temp" + temp);
        }
        if(sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY) {

            text3.setText("Humidity" + humid);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mPressure,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mtemp,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mlight,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mhumid,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
