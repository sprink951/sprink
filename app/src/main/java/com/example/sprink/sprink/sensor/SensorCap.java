package com.example.sprink.sprink.sensor;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

public class  SensorCap{


    private Context context;
    private SensorManager sensorManager;
    private List<Sensor> l_sensors;
    public double mA0 ;
    public double mA1;
    public double mA2;
    public boolean movtion;
    public SensorCap(Context ctx){
        this.context = ctx;
    }

    public void SensorLoad(){
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        l_sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(int i = 0;i < l_sensors.size();i++)
        {
            if (l_sensors.get(i).getType() == Sensor.TYPE_ACCELEROMETER) {
                sensorManager.registerListener(myAccEventListener,
                        l_sensors.get(i), SensorManager.SENSOR_DELAY_FASTEST);
            }
        }

    }

    public void SensorUnload(){
        sensorManager.unregisterListener(myAccEventListener);
    }

    public SensorEventListener myAccEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    final double alpha=0.8;
                    final double ret;
                    final  int  move = 0;
                    mA0 = alpha*mA0 +(1-alpha)*sensorEvent.values[0];
                    mA1 = alpha*mA1 +(1-alpha)*sensorEvent.values[1];
                    mA2 = alpha*mA2 +(1-alpha)*sensorEvent.values[2];

                    ret = Math.sqrt(mA0*mA0 + mA1*mA1 + mA2*mA2);

                   // liner_acc_0 =  sensorEvent.values[0] - mA0;
                   // liner_acc_1 =  sensorEvent.values[1] - mA1;
                  //  liner_acc_2 =  sensorEvent.values[2] - mA2;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}