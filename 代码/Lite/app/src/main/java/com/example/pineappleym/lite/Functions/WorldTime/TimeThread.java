package com.example.pineappleym.lite.Functions.WorldTime;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeThread extends Thread{
    public TextView Time;
    public String area;
    public int delay;
    private int msgKey = 18;

    public TimeThread(TextView Time,String area,int delay) {
        this.Time = Time;
        this.area = area;
        this.delay = delay;
    }

    public void setArea(String area) {
        this.area = area;
    }


    @Override
    public void run() {
        do {
            try {
                Message msg = new Message();
                msg.what = msgKey;
                mHandler.sendMessage(msg);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 18:
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    sdf.setTimeZone(TimeZone.getTimeZone(area));
                    String date = sdf.format(new Date());
                    Time.setText(date);
                    break;
                default:
                    break;
            }
        }
    };
}
