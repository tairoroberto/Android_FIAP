package br.com.tairoroberto.fiapalarm.services;

import android.util.Log;

import java.util.TimerTask;

/**
 * Created by logonrm on 12/06/2017.
 */

public class Logger extends TimerTask {

    private static final String TAG = "Logger";
    private int count = 0;

    public Logger() {
    }

    @Override
    public void run() {
        count++;
        Log.i(TAG, "run: " + count);
    }
}
