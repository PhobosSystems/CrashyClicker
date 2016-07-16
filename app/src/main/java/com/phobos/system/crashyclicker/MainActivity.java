package com.phobos.system.crashyclicker;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


// 2016 John Balis
// public domain
//have fun
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // crash log update code
        int crashNum = loadInt("crashes");
        crashNum = crashNum + 1;
        //update crash number code
        saveInt("crashes", crashNum);
        //notification code
        notify(crashNum);

        //crash code
        int i = 1/0;

    }
    public void notify(int crashes)
    {
        NotificationCompat.Builder mBuilder;


            //sends system notification
        if(crashes == 1)
        {
            mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("New Crash Milestone!")
                            .setContentText("1 crash so far.");
        }
            else if (!primalTest(crashes)) {
                 mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.logo)
                                .setContentTitle("New Crash Milestone!")
                                .setContentText(crashes + " crashes so far.");
            }
        else
            {
                 mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.logo2)
                                .setContentTitle("New Crash: Certified Prime!")
                                .setContentText(crashes + " crashes so far.");
            }
            Intent resultIntent = new Intent(this, MainActivity.class);
            TaskStackBuilder sb = TaskStackBuilder.create(this);
            sb.addParentStack(MainActivity.class);
            sb.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    sb.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(666, mBuilder.build());
        }

    public void saveInt(String key, int value){
        SharedPreferences shared = getSharedPreferences("CrashyClicker", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public int loadInt(String key){
        SharedPreferences shared = getSharedPreferences("CrashyClicker", Activity.MODE_PRIVATE);
            return shared.getInt(key,0);
    }

    public static boolean primalTest(int number)
    {
        for (int i = 2; i <= number/2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
