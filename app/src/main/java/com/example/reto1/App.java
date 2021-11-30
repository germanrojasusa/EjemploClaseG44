package com.example.reto1;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CANAL_ID_1 = "canal1";
    public static final String CANAL_ID_2 = "canal2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationsChannels();
    }
    //Inicialización de canales de notificaciones para poder utilizarlos en las Activities
    private void createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal1 = new NotificationChannel(
                    CANAL_ID_1,
                    "Canal 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal1.setDescription("Este es el canal 1");

            NotificationChannel canal2 = new NotificationChannel(
                    CANAL_ID_2,
                    "Canal 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            canal2.setDescription("Este es el canal 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal1);
            manager.createNotificationChannel(canal2);


        }
    }
}
