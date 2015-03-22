package org.fish.navi.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class GpsManager {

    public static final String LOCATION_ACTION = "org.fish.navi.service.GpsManager.LOCATION_ACTION";

    private static GpsManager instance;
    private Context context;
    private LocationManager locationManager;

    private GpsManager(Context context){
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public static GpsManager instance(Context context){
        if (instance == null){
            instance = new GpsManager(context);
        }

        return instance;
    }

    public void startLocationUpdates() {
        Log.d("GpsManager", "Start location updates");

        PendingIntent intent = getLocationPendingIntent(true);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, intent);
    }

    public void stopLocationUpdates(){
        PendingIntent intent = getLocationPendingIntent(false);

        if (intent != null) {
            Log.d("GpsManager", "Stop location updates");
            locationManager.removeUpdates(intent);
            intent.cancel();
        }
    }

    public boolean isStarted(){
        return getLocationPendingIntent(false) != null;
    }

    private PendingIntent getLocationPendingIntent(boolean create){
        Intent broadcast = new Intent(LOCATION_ACTION);
        int flags = create ? 0 : PendingIntent.FLAG_NO_CREATE;

        return PendingIntent.getBroadcast(context, 0, broadcast, flags);
    }
}