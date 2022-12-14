package com.appointmentbooking.weekviewCal

import java.util.*

interface WeekDaySubtitleInterpreter {
    fun getFormattedWeekDaySubtitle(date: Calendar): String
}
