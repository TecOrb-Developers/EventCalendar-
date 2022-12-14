package com.bookappointmentcalender.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.multidex.BuildConfig;

import com.bookappointmentcalender.ui.ActivityVerticalCaleSpecialist;
import com.bookappointmentcalender.ui.ActivityVerticalCalendar;
import com.bookappointmentcalender.ui.ActivityDashboard;


public class IntentHelper {
    public static Intent getPlayStoreIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=".concat(BuildConfig.APPLICATION_ID)));
        return intent;
    }

    public static Intent getHomeIntent(Context context) {
        return new Intent(context, ActivityDashboard.class);

    }
    public static Intent getActivityVerticalCaleSpecialist(Context context) {
        return new Intent(context, ActivityVerticalCaleSpecialist.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public static Intent getActivityVerticalCalendar(Context context) {
        return new Intent(context, ActivityVerticalCalendar.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }



}
