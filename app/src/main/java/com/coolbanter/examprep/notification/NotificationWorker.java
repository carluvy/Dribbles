package com.coolbanter.examprep.notification;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.coolbanter.examprep.R;
import com.coolbanter.examprep.data.DataRepository;
import com.coolbanter.examprep.data.Smiley;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationWorker extends Worker {

    public static final String WORK_NAME = "Notification Worker";
    private static final int NOTIFICATION_ID = 22;
    private static final String CHANNEL_ID = "notify-moji";
    private final NotificationManager notificationManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }

    @NonNull
    @Override
    public Result doWork() {

        DataRepository repository = DataRepository.getInstance(getApplicationContext());
        Smiley smiley = repository.getSmiley();

//        String smile = smiley.getmEmoji();

        // This PendingIntent can be used to cancel the worker
//        PendingIntent intent = WorkManager.getInstance(getApplicationContext())
//                .createCancelPendingIntent(getId());




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();


//            NotificationManager mNotificationManager =
//                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//            mNotificationManager.createNotificationChannel(notificationChannel);

            Intent resultIntent = new Intent();
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
//            stackBuilder.addNextIntentWithParentStack(resultIntent);
//
//            PendingIntent intent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent =
                    PendingIntent.getService(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)

                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setContentTitle("Today's Emoji!")
                    .setContentText(smiley.getEmoji())
                    .setSubText(smiley.getName())
                    .setContentIntent(pendingIntent)
//                    .setOngoing(false)
//                    .addAction(android.R.drawable.ic_delete, getApplicationContext().getString(R.string.cancel_notifications), intent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();

//            NotificationManager mNotificationManager =
//                    (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//            NotificationManager notificationManager = getApplicationContext(NotificationManager.class).from(getApplicationContext());
            notificationManager.notify(NOTIFICATION_ID, builder);

//            notificationManager.cancel(NOTIFICATION_ID);
        }





















        return Result.success();
    }

    private void createChannel() {

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANNEL_ID, "Emoji Notification",
                            NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.getDescription();
            notificationChannel.getAudioAttributes();

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }




}
