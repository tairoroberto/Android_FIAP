package br.com.tairoroberto.fiapalarm.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import br.com.tairoroberto.fiapalarm.MainActivity;
import br.com.tairoroberto.fiapalarm.R;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mediaPlayer = null;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mediaPlayer = MediaPlayer.create(context, R.raw.ea_games);
        mediaPlayer.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Alarme");
        builder.setContentText("Você tem um novo alarme");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = builder.build();
        notification.vibrate = new long[]{100, 200, 100, 200, 400};

        notificationManager.notify(1, notification);

        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, som);
        ringtone.play();
    }
}
