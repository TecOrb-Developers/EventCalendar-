package com.appointmentbooking.weekviewNames;
import java.util.Calendar;


public interface DateTimeInterpreter {
     String interpretDate(Calendar date);
     String interpretTime(int hour, int minutes);
}
