package com.bookappointmentcalender.helper;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.application.App;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormat {
    public static String orderDateFormat(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Date changeDateFormate(String serverdate) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("MMM dd yyyy, HH:mm", Locale.ENGLISH);
        Date d = null;
        try {
            d = sdf.parse(serverdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Date changeDateFormateTwo(String serverdate) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("MMM dd yyyy, HH:mm", Locale.ENGLISH);
        Date d = null;
        try {
            d = sdf.parse(serverdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("ddfsdf", "changeDateFormateTwo: "+d);
        return d;
    }

    public static String orderDateFormatEdit(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderTimeFormatEdit(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static int getDateOnly(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("d");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnly: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }
    public static int getMonthOnly(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("MM");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnly: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }

    public static int getDateOnlySpecialist(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("d");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnlyD: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }


    public static int getDateOnlyDash(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatOutput = new SimpleDateFormat("d");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnlyD: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }
    public static int getMonthOnlySpecialist(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("MM");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnlyM: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }
    public static int getYearOnlySpecialist(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("YYYY");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("fdf", "getDateOnlyy: "+Integer.parseInt(formatOutput.format(date)));
        if (date != null) return Integer.parseInt(formatOutput.format(date));
        else return 0;
    }
    public static String getBlocketDateFormat(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String getBlocketDateFormatDash(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) return formatOutput.format(date);
        else return "";
    }


    public static String getAmountFormator(Double input){
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(getLocalFromISO("EUR"));
        return currencyInstance.format(input).replace("€","");

    }

    private static Locale getLocalFromISO(String iso4217code){
        Locale toReturn = null;
        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).
                    getCurrency().getCurrencyCode();
            if (iso4217code.equals(code)) {
                toReturn = locale;
                break;
            }
        }
        return toReturn;
    }

    public static int orderTimeFormat(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd MMMM yyyy", Locale.UK);
        SimpleDateFormat formatOutput = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = formatInput.parse(input);
            Log.d("sdasd", "orderTimeFormat: "+date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if (date != null) return date.getDay();
        else return 0;
    }

    public static String orderDateTimeFormatChat(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.UK);
        SimpleDateFormat formatOutput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatInput.parse(input);
        }catch(ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderDateFormatTwo(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd MMM");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderDateFormatSendToserver(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat formatOutput = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderDateFormatSendToShowTwoNew(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd-MM-yyyy");
        formatInput.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e){
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static Date orderDate(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK);
        formatInput.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = null;
        try {
            date = formatInput.parse(input);
        }catch (ParseException e){
            e.printStackTrace();
        }
        if (date != null) return date;
        else return date;
    }
    public static Date orderDateC(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.getDefault());
        //formatInput.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return date;
        else return date;
    }

    public static Date orderDateCCalendar(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        //formatInput.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = null;
        try {
            date = formatInput.parse(input);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return date;
        else return date;
    }

    public static Date orderDateCCalendarDash(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        //formatInput.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = null;
        try {
            date = formatInput.parse(input);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return date;
        else return date;
    }
    public static String orderDateFormatSendToShow(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderDateFormatSendToShowThree(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd MMMM yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String orderDateFormatSendToShowTwo(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK);
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    public static String commanDateFormat(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }
    public static String getCurrentDateWithTime() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("yyyy-MM-dd'T'HH:mm:ss", cal).toString();
        return date;
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("hh:mm a", cal).toString();
        return date;
    }
    public static String getCurrentDate() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("yyyy-MM-dd", cal).toString();
        return date;
    }

    public static String getCurrentDateDash() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    public static String getCurrentDateDash(int viewTypeCale) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("dd", cal).toString();
        cal.add(Calendar.DAY_OF_YEAR, viewTypeCale-1);
        String date2 = android.text.format.DateFormat.format("dd MMMM, yyyy", cal).toString();
        return date.concat("  -  ").concat(date2);
    }

    public static String getCurrentDateDashSingleNoSpecialist(int viewTypeCale) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("dd", cal).toString();
        cal.add(Calendar.DAY_OF_YEAR, viewTypeCale-1);
        String date2 = android.text.format.DateFormat.format("dd MMMM, yyyy", cal).toString();
        return date2;
    }

    public static String getCurrentDateDashSeven() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = android.text.format.DateFormat.format("dd", cal).toString();
        cal.add(Calendar.DAY_OF_YEAR, 6);
        String date2 = android.text.format.DateFormat.format("dd MMMM, yyyy", cal).toString();
        return date.concat("  -  ").concat(date2);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String orderDateFormatToShowA(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("EEEE,dd MMM YYYY");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String orderDateFormatToShowB(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("EEEE,dd MMMM YYYY");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String orderDateFormatGetOnlyDay(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOutput = new SimpleDateFormat("EEEE");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String orderDateFormatGetOnlyDayTwo(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("MMM dd yyyy, HH:mm");
        SimpleDateFormat formatOutput = new SimpleDateFormat("EEEE");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String orderDateFormatGetOnlyDaySample(String input){
        SimpleDateFormat formatInput = new SimpleDateFormat("MMM dd yyyy, HH:mm");
        SimpleDateFormat formatOutput = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatInput.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) return formatOutput.format(date);
        else return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static  String getDuration(Date d1, Date d2) {
        Duration diff = Duration.between(d1.toInstant(), d2.toInstant());
        long days = diff.toDays();
        diff = diff.minusDays(days);
        long hours = diff.toHours();
        diff = diff.minusHours(hours);
        long minutes = diff.toMinutes();
        diff = diff.minusMinutes(minutes);
        long seconds = diff.toMillis();

        StringBuilder formattedDiff = new StringBuilder();
        if(days!=0 ){
            if(days==1){
                formattedDiff.append(days + "d ".concat(App.getInstance().getString(R.string.txt_ago)));

            }else {
                formattedDiff.append(days + "d ".concat(App.getInstance().getString(R.string.txt_ago)));
            }
            // return formattedDiff.toString();
        }else if(hours!=0){
            if(hours==1){
                formattedDiff.append(hours + "h ".concat(App.getInstance().getString(R.string.txt_ago)));

            }else{
                formattedDiff.append(hours + "h ".concat(App.getInstance().getString(R.string.txt_ago)));
            }
            //return formattedDiff.toString();
        }else {
            if (minutes == 1) {
                formattedDiff.append(minutes + "m ".concat(App.getInstance().getString(R.string.txt_ago)));
            } else {
                if (minutes ==0){
                    formattedDiff.append(1 + "m ".concat(App.getInstance().getString(R.string.txt_ago)));
                }else {
                    formattedDiff.append(minutes + "m ".concat(App.getInstance().getString(R.string.txt_ago)));
                }

            }
        }
        return formattedDiff.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static  String getDaysDuration(Date d1, Date d2) {
        Duration diff = Duration.between(d1.toInstant(), d2.toInstant());
        long days = diff.toDays();
        diff = diff.minusDays(days);
        long hours = diff.toHours();
        diff = diff.minusHours(hours);
        long minutes = diff.toMinutes();
        diff = diff.minusMinutes(minutes);
        long seconds = diff.toMillis();

        StringBuilder formattedDiff = new StringBuilder();
        if(days!=0 ){
            if(days==1){

                formattedDiff.append(days + " ".concat(App.getInstance().getString(R.string.txt_day_ago)));
            }else {
                formattedDiff.append(days + " ".concat(App.getInstance().getString(R.string.txt_days_ago)));
            }
            // return formattedDiff.toString();
        }else if(hours!=0){
            if(hours==1){
                formattedDiff.append(hours + " ".concat(App.getInstance().getString(R.string.txt_hour_ago)));

            }else{
                formattedDiff.append(hours + " ".concat(App.getInstance().getString(R.string.txt_hours_ago)));
            }
            //return formattedDiff.toString();
        }else {
            if (minutes == 1) {
                formattedDiff.append(minutes + " ".concat(App.getInstance().getString(R.string.txt_mint_ago)));
            } else {
                formattedDiff.append(minutes + " ".concat(App.getInstance().getString(R.string.txt_mints_ago)));
            }
        }
        return formattedDiff.toString();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static  String getDaysDurationCalKot(Date d1, Date d2) {
        Duration diff = Duration.between(d1.toInstant(), d2.toInstant());
        long days = diff.toDays();
        diff = diff.minusDays(days);
        long hours = diff.toHours();
        diff = diff.minusHours(hours);
        long minutes = diff.toMinutes();
        diff = diff.minusMinutes(minutes);
        long seconds = diff.toMillis();

        StringBuilder formattedDiff = new StringBuilder();
        if(days!=0 ){
            if(days==1){
                formattedDiff.append(days);
            }else {
                formattedDiff.append(days);
            }
            // return formattedDiff.toString();
        }else if(hours!=0){
            if(hours==1){
                formattedDiff.append(hours);

            }else{
                formattedDiff.append(hours);
            }
            //return formattedDiff.toString();
        }else {
            if (minutes == 1) {
                formattedDiff.append(minutes);
            } else {
                formattedDiff.append(minutes);
            }
        }
        return formattedDiff.toString();

    }

}
