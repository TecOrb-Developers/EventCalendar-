package com.appointmentbooking.weekviewCal

import androidx.annotation.ColorInt

interface TextColorPicker {

    @ColorInt
    fun getTextColor(event: WeekViewEvent): Int

}
