package com.example.admin.w4d4ex02;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

/**
 * Created by Admin on 8/10/2017.
 */

public class BoundService extends Service{
    private static final String TAG= BoundService.class.getSimpleName()+"_TAG";
    private IBinder binder=new MyBinder();
    private Chronometer chronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: CREATED");
        chronometer=  new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: BINDING");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: REWIND");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: DETACHING");
        return true;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: TERMINATED");
        chronometer.stop();
    }
    public String getTimeStamp(){
        long elapsedMillis= SystemClock.elapsedRealtime()-chronometer.getBase();
        return String.valueOf(elapsedMillis);
    }
    public class MyBinder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }
    }
}
