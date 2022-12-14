package com.bookappointmentcalender.application;

import android.app.Application;
import android.content.Context;
import android.location.Geocoder;

import androidx.multidex.MultiDex;

import java.util.Locale;

public class App extends Application {
    private Context appContext;
    private static App app;
    public static App getInstance(){
        return app;
    }
    private Geocoder geocoder;

    @Override
    public void onCreate() {
        super.onCreate();
        geocoder = new Geocoder(this, Locale.ENGLISH);
        app = this;
        appContext = this;
        MultiDex.install(this);
    }
    public Geocoder getGeoCoder() {
        return geocoder;
    }
}
