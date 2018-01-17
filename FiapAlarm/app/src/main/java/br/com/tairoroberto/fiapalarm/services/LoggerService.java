package br.com.tairoroberto.fiapalarm.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class LoggerService extends Service {
    private Timer timer;
    private Logger logger;

    public LoggerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("Logger","Serviço iniciado :) ");
        timer = new Timer();
        logger = new Logger();
        timer.schedule(logger, 1000, 2000);
    }

    @Override
    public void onDestroy() {
        Log.i("Logger", "Serviço parado :) ");
        logger.cancel();
        timer.cancel();
        super.onDestroy();
    }
}
