package br.com.tairoroberto.fiapboundservices.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {

    private static final String TAG = "BoundService";
    private IBinder mBinder = new MyBinder();
    private Chronometer chronometer;

    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        chronometer.stop();
    }

    public String getTimeStamp() {
        long elpseRealTime = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (elpseRealTime / 3600000);
        int minutes = (int) (elpseRealTime - hours * 3600000) / 60000;
        int seconds = (int) (elpseRealTime - hours * 3600000 - minutes * 600000) / 1000;
        int miliseconds = (int) (elpseRealTime - hours * 3600000 - minutes * 600000 - seconds * 1000);

        return hours + ":" + minutes + ":" + seconds + ":" + miliseconds;
    }

    public class MyBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }
}
