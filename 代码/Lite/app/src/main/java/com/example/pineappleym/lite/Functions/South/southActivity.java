package com.example.pineappleym.lite.Functions.South;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pineappleym.lite.R;

public class southActivity extends AppCompatActivity {
    private ImageView arrow;
    private TextView rotate;
    private Sensor accelerometer;
    private Sensor magnetic;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_south);
        initView();
        initEvent();
        initData();
    }
    private void initData(){
        // 传感器管理者
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    private void initEvent() {
        // 传感器事件监听器
        sensorEventListener = new SensorEventListener() {
            //当值发生改变的时候调用
            float[] accelerometerValues = new float[3];
            float[] maneticValues = new float[3];
            private float lastDegree;
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                    accelerometerValues = sensorEvent.values.clone();
                }else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                    maneticValues = sensorEvent.values.clone();
                }
                float[] R = new float[9];
                float[] values = new float[3];
                SensorManager.getRotationMatrix(R,null,accelerometerValues,maneticValues);
                SensorManager.getOrientation(R,values);
                float rotateDegree = -(float) Math.toDegrees(values[0]);
                rotate.setText("旋转角度：" + Integer.toString((int)rotateDegree));
                if (Math.abs(rotateDegree - lastDegree) > 1){
                    RotateAnimation animation = new RotateAnimation(lastDegree,rotateDegree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    animation.setFillAfter(true);
                    arrow.startAnimation(animation);
                    lastDegree = rotateDegree;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    void initView() {
        arrow = findViewById(R.id.arrow);
        rotate = findViewById(R.id.rotate);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }
}
