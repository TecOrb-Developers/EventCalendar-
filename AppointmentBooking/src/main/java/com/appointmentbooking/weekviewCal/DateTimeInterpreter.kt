package com.appointmentbooking.weekviewCal

import java.util.*

interface DateTimeInterpreter {
    fun getFormattedWeekDayTitle(date: Calendar): String
    fun getFormattedTimeOfDay(hour: Int, minutes: Int): String
}
