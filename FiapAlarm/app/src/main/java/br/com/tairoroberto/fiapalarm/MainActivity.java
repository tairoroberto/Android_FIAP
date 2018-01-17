package br.com.tairoroberto.fiapalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.tairoroberto.fiapalarm.receivers.AlarmReceiver;
import br.com.tairoroberto.fiapalarm.services.LoggerService;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAlarm = (EditText) findViewById(R.id.editAlarm);
    }

    public void dispararAlarme(View view) {
        if (TextUtils.isEmpty(editTextAlarm.getText().toString())){
            Toast.makeText(this, "Insira um valor para o alarme :)", Toast.LENGTH_SHORT).show();
            return;
        }

        int i = Integer.parseInt(editTextAlarm.getText().toString());
        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (i * 1000),
                pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + "seconds", Toast.LENGTH_LONG).show();
    }

    public void iniciarServico(View view) {
        Intent intent = new Intent(this, LoggerService.class);
        startService(intent);
    }

    public void pararServico(View view) {
        Intent intent = new Intent(this, LoggerService.class);
        stopService(intent);
    }
}
