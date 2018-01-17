package br.com.tairoroberto.fiapintent.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import br.com.tairoroberto.fiapintent.R;
import br.com.tairoroberto.fiapintent.SMSActivity;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj != null) {
                    for (Object aPdusObj : pdusObj) {

                        SmsMessage currentMessage;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            String format = intent.getStringExtra("format");
                            currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj, format);
                        } else {
                            currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                        }

                        String numeroTelefone = currentMessage.getDisplayOriginatingAddress();

                        String mensagem = currentMessage.getDisplayMessageBody();
                        Log.i("SmsReceiver", "senderNum: " + numeroTelefone + "; message: " + mensagem);

                        Intent i2 = new Intent("android.intent.action.SMSRECEBIDO")
                                .putExtra("remetente", numeroTelefone)
                                .putExtra("mensagem", mensagem);
                        context.sendBroadcast(i2);

                        showNotification(context, numeroTelefone, mensagem);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    private void showToast(Context context, String numeroTelefone, String mensagem) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "senderNum: " + numeroTelefone + ", message: " + mensagem, duration);
        toast.show();
    }

    private void showNotification(Context context, String numeroTelefone, String mensagem) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Mensagem de: " + numeroTelefone);
        mBuilder.setContentText(mensagem);
        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(context, SMSActivity.class);

        resultIntent
                .putExtra("remetente", numeroTelefone)
                .putExtra("mensagem", mensagem);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(SMSActivity.class);


        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}
