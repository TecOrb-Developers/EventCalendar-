package com.appointmentbooking.weekviewNames;


import androidx.annotation.ColorInt;

public interface TextColorPicker {
    @ColorInt
    int getTextColor(WeekViewEvent event);

}
