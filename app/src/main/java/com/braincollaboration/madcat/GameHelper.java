package com.braincollaboration.madcat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GameHelper {

    /**
     * @param score
     * @return time in millis for level, according to game score.
     */
    public static int getTimeForLevel(int score) {

        if (score <= 5 && score >= 0) {
            return 4700;
        } else if (score <= 10 && score > 5) {
            return 4500;
        } else if (score <= 15 && score > 10) {
            return 4200;
        } else if (score <= 20 && score > 15) {
            return 3700;
        } else if (score <= 25 && score > 20) {
            return 3200;
        } else if (score <= 30 && score > 25) {
            return 2700;
        } else if (score <= 35 && score > 30) {
            return 2200;
        } else if (score <= 40 && score > 35) {
            return 2000;
        } else if (score <= 45 && score > 40) {
            return 1500;
        } else if (score <= 50 && score > 45) {
            return 1500;
        } else if (score <= 55 && score > 50) {
            return 1400;
        } else if (score <= 60 && score > 55) {
            return 1350;
        } else if (score <= 65 && score > 60) {
            return 1300;
        } else if (score <= 70 && score > 65) {
            return 1250;
        }
        return 1000;
    }

    public static void saveBestScore(Context context, int gameDifficaltyKey, int score){
        switch(gameDifficaltyKey){
            case 1:
                PreferenceUtil.putInt(context, context.getString(R.string.prefkey_gamediff_easy), score);
                break;
            case 2:
                PreferenceUtil.putInt(context, context.getString(R.string.prefkey_gamediff_medium), score);
                break;
            case 3:
                PreferenceUtil.putInt(context, context.getString(R.string.prefkey_gamediff_hard), score);
                break;
        }
    }

    public static int loadBestScore(Context context, int gameDifficaltyKey){
        switch(gameDifficaltyKey){
            case 1:
                return PreferenceUtil.getInt(context, context.getString(R.string.prefkey_gamediff_easy), 0);
            case 2:
                return PreferenceUtil.getInt(context, context.getString(R.string.prefkey_gamediff_medium), 0);
            case 3:
                return PreferenceUtil.getInt(context, context.getString(R.string.prefkey_gamediff_hard), 0);
            default:
                return 0;
        }
    }

    public static void shareScore(Context context, String body){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(sharingIntent, null));
    }

    public static boolean haveNetworkConnection(Context mContext) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
