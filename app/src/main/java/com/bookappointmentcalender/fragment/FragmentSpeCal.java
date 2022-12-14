package com.bookappointmentcalender.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.base.BaseFragmentV4;
import com.bookappointmentcalender.databinding.FragmentSpeCalBinding;
import com.bookappointmentcalender.helper.DateFormat;
import com.bookappointmentcalender.helper.IntentHelper;
import com.appointmentbooking.model.GetAllSpecialistModel;
import com.bookappointmentcalender.model.MultipleSpecialistEvent;
import com.bookappointmentcalender.model.ProfileModel;
import com.bookappointmentcalender.model.SpecialistBookingModel;
import com.bookappointmentcalender.ui.ActivityDashboard;
import com.appointmentbooking.weekviewNames.DateTimeInterpreter;
import com.appointmentbooking.weekviewNames.MonthLoader;
import com.appointmentbooking.weekviewNames.WeekView;
import com.appointmentbooking.weekviewNames.WeekViewEvent;


public class FragmentSpeCal extends BaseFragmentV4 implements
        WeekView.EventClickListener,
        MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener,
        WeekView.EmptyViewLongPressListener,
        WeekView.EmptyViewClickListener,
        WeekView.AddEventClickListener,
        WeekView.DropListener{
    private static final String TAG = "FragmentDay";
    private FragmentSpeCalBinding binding;
    private int viewPosition;
    private GetAllSpecialistModel allSpecialistListModela;
    private Context context;
    private ActivityDashboard activityDashboard;
    private String specialistName;
    private  ProfileModel profileModel;
    private SpecialistBookingModel specialistBookingModel;
    private String todaySelectedDate ,currentDate ,selectedDate;
    private boolean isScrolled = true;
    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    public FragmentSpeCal(int viewPosition) {
        this.viewPosition = viewPosition;
    }
    public int specilistCounter = 0;
     Calendar c;
    public FragmentSpeCal(GetAllSpecialistModel allSpecialistListModel) {
        this.allSpecialistListModela = allSpecialistListModel;
    }

    public FragmentSpeCal(String specialistName, int position) {
        this.viewPosition = position;
        this.specialistName = specialistName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_spe_cal, container, false);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        activityDashboard = (ActivityDashboard)getActivity();
        binding.weekView.setHeader(allSpecialistListModela);
        callApiGetUserDetial();
        if(specialistName != null && !specialistName.equalsIgnoreCase("")) {
            Calendar calendar = Calendar.getInstance();
            binding.weekView.setMinDate(calendar);
            binding.weekView.setMaxDate(calendar);
        }
        if (allSpecialistListModela != null) {
            if (allSpecialistListModela.getResult().size() == 1) {
                viewPosition = 1;
                binding.weekView.setHorizontalScrollBarEnabled(false);
                Calendar calendar = Calendar.getInstance();
                binding.weekView.setMinDate(calendar);
                binding.weekView.setMaxDate(calendar);
            }else if(allSpecialistListModela.getResult().size() == 2) {
                viewPosition = 2;
                binding.weekView.setHorizontalScrollBarEnabled(false);
                Calendar calendar = Calendar.getInstance();
                binding.weekView.setMinDate(calendar);
                Calendar maxDate = Calendar.getInstance();
                maxDate.add(Calendar.MONTH, 1);
                maxDate.set(Calendar.DAY_OF_MONTH, 1);
                Log.d("nextDate", "onCreate: " + maxDate +"\n"+allSpecialistListModela.getResult().size());
                binding.weekView.setMaxDate(maxDate);
            }else {
                binding.weekView.setHorizontalScrollBarEnabled(false);
                viewPosition = 3;
                int specialistSize = allSpecialistListModela.getResult().size();
                Calendar calendar = Calendar.getInstance();
                binding.weekView.setMinDate(calendar);
                Calendar maxDate = Calendar.getInstance();
                maxDate.add(Calendar.DATE, specialistSize - 1);
                Log.d("nextDate_date", "onCreate: " + maxDate);
                binding.weekView.setMaxDate(maxDate);
            }
        }
        binding.weekView.setOnEventClickListener(this);
        binding.weekView.setMonthChangeListener(this);
        binding.weekView.setEventLongPressListener(this);
        binding.weekView.setEmptyViewLongPressListener(this);

        // Set EmptyView Click Listener
        binding.weekView.setEmptyViewClickListener(this);
        // Set AddEvent Click Listener
        binding.weekView.setAddEventClickListener(this);
        // Set Drag and Drop Listener
        binding.weekView.setDropListener(this);
        binding.weekView.setNowLineColor(context.getResources().getColor(R.color.colorPinkGirl));
        binding.weekView.setShowNowLine(true);
        binding.weekView.setNowLineThickness(1);
        setupDateTimeInterpreter(false);
        binding.weekView.setNumberOfVisibleDays(viewPosition);
        binding.weekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        binding.weekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        binding.weekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
        initViews();

        binding.viewDate.setOnClickListener(v->{
             startActivityForResult(IntentHelper.getActivityVerticalCaleSpecialist(context),400);
        });

    }
    private void callApiGetUserDetial() {
       //A Api.getWithVersionTwo(Constants.URL_USER_PROFILE, this, false);
    }
    private void initViews() {
        c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        final SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        final String[] formattedDate = {df.format(c.getTime())};
        todaySelectedDate = df1.format(c.getTimeInMillis());  // date for sending backend
        currentDate = df.format(c.getTimeInMillis());  // date for showing purpose
        Log.d("sfsgdf", "initViews: " + currentDate);
        binding.viewDate.setText(formattedDate[0]);
        selectedDate = binding.viewDate.getText().toString();

        binding.imgLeftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String now = df.format(c.getTimeInMillis());
                if (!currentDate.equalsIgnoreCase(now)) {
                    c.add(Calendar.DATE, -1);
                    formattedDate[0] = df.format(c.getTime());
                    Log.v("PREVIOUS DATE : ", formattedDate[0]);
                   // callApiGetSpecilaistboking(df1.format(c.getTime()));
                    todaySelectedDate =df1.format(c.getTime());
                    binding.viewDate.setText(formattedDate[0]);
                    selectedDate = binding.viewDate.getText().toString();
                    if (specialistName != null && !specialistName.equalsIgnoreCase("")) {
                        Calendar calendar = Calendar.getInstance();
                        binding.weekView.setMinDate(c);
                        binding.weekView.setMaxDate(c);
                    }
                }
            }
        });
        binding.imgRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.add(Calendar.DATE, 1);
                formattedDate[0] = df.format(c.getTime());
                Log.v("NEXT DATE : ", formattedDate[0]);
                binding.viewDate.setText(formattedDate[0]);
                //callApiGetSpecilaistboking(df1.format(c.getTime()));
                todaySelectedDate =df1.format(c.getTime());
                selectedDate = binding.viewDate.getText().toString();
                if (specialistName != null && !specialistName.equalsIgnoreCase("")) {
                    Calendar calendar = Calendar.getInstance();
                    Log.d(TAG, "onClick: "+c);
                    binding.weekView.setMaxDate(c);
                    binding.weekView.setMinDate(c);
                }
            }
        });
    }
    private void setupDateTimeInterpreter(final boolean shortDate) {
        binding.weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                if (allSpecialistListModela !=null && allSpecialistListModela.getResult() !=null){
                    SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                    String weekday = weekdayNameFormat.format(date.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("d", Locale.getDefault());
                    if(shortDate)
                        weekday = String.valueOf(weekday.charAt(0));
                    Log.d("dafafsdgsdgsdg", "interpretDate: "+weekday.toUpperCase() + format.format(date.getTime()));
                    return  format.format(date.getTime());
                }else{
                    return specialistName;
                }
            }
            @Override
            public String interpretTime(int hour, int minutes) {
                if (hour == 0) {
                    return "0 : 00";
                }else{
                    return String.valueOf(hour).concat(" : 00");
                }
            }
        });
    }
    @Override
    public void onTaskSuccess(Object responseObj) {
        super.onTaskSuccess(responseObj);
         if (responseObj instanceof ProfileModel) {
            profileModel = (ProfileModel) responseObj;
        } else if (responseObj instanceof SpecialistBookingModel){
             specialistBookingModel =(SpecialistBookingModel)responseObj;
            this.events.clear();
            if(specialistBookingModel.getResult().getSlot() !=null &&
                    specialistBookingModel.getResult().getSlot().size()>0){
                 for (int i=0; i<specialistBookingModel.getResult().getSlot().size(); i++){
                     this.events.add(specialistBookingModel.getResult().getSlot().get(i).toWeekViewEvent());
                 }
             }
             if (specialistBookingModel.getResult().getBreakTime() !=null){
                 this.events.add(specialistBookingModel.getResult().getBreakTime().toWeekViewBlockEventSpe());
             }
            getWeekView().notifyDatasetChanged();
        }else if (responseObj instanceof MultipleSpecialistEvent){
            MultipleSpecialistEvent multipleSpecialistEvent =(MultipleSpecialistEvent)responseObj;
            this.events.clear();
           // CalenderBookingModel calenderBookingModel = (CalenderBookingModel)responseObj;
            for (int j=0; j<allSpecialistListModela.getResult().size(); j++){
                for (int i=0; i<multipleSpecialistEvent.getResult().size(); i++){
                    if(allSpecialistListModela.getResult().get(j).get_id()
                            .equalsIgnoreCase(multipleSpecialistEvent.getResult().get(i).getSpecId())){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
                        Calendar calendar = new GregorianCalendar();
                        calendar.add(Calendar.DATE, j);
                        String dasdd = format.format(calendar.getTime());
                        Log.d("fhfhsgd", "onTaskSuccess: "+dasdd +"\n"+j);
                        for (int k =0; k<multipleSpecialistEvent.getResult().get(i).getSlot().size(); k++){
                            //if (multipleSpecialistEvent.getResult().get(i).getSlot().get(k).getDate().equalsIgnoreCase(dasdd)){
                                multipleSpecialistEvent.getResult().get(i).getSlot().get(k).setDate(dasdd);
                            //}
                        }
                    }
                }
            }
            for (int i=0; i<multipleSpecialistEvent.getResult().size(); i++){
                for (int j =0; j<multipleSpecialistEvent.getResult().get(i).getSlot().size(); j++){
                   // Toast.makeText(context, "Two", Toast.LENGTH_SHORT).show();
                    this.events.add(multipleSpecialistEvent.getResult().get(i).getSlot().get(j).toWeekViewEvent());
                }
            }
            for (int j=0; j<allSpecialistListModela.getResult().size(); j++){
                for (int i=0; i<multipleSpecialistEvent.getBlockTime().size(); i++){
                    if (allSpecialistListModela.getResult().get(j).get_id()
                            .equalsIgnoreCase(multipleSpecialistEvent.getBlockTime().get(i).getSpecId())){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
                        Calendar calendar = new GregorianCalendar();
                        calendar.add(Calendar.DATE, j);
                        String dasdd = format.format(calendar.getTime());
                        Log.d("fhfhsgd", "onTaskSuccess: "+dasdd +"\n"+j);
                        for (int k =0; k<multipleSpecialistEvent.getBlockTime().get(i).getBreakTime().size(); k++){
                            //if (multipleSpecialistEvent.getResult().get(i).getSlot().get(k).getDate().equalsIgnoreCase(dasdd)){
                            multipleSpecialistEvent.getBlockTime().get(i).getBreakTime().get(k).setBreakDate(dasdd);
                            //}
                        }
                    }
                }
            }
            if (multipleSpecialistEvent.getBlockTime() !=null && multipleSpecialistEvent.getBlockTime().size()>0){
                for (int i=0; i<multipleSpecialistEvent.getBlockTime().size(); i++){
                    for (int j =0; j<multipleSpecialistEvent.getBlockTime().get(i).getBreakTime().size(); j++){
                        // Toast.makeText(context, "Two", Toast.LENGTH_SHORT).show();
                        this.events.add(multipleSpecialistEvent.getBlockTime().get(i).getBreakTime().get(j).toWeekViewBlockEvent());
                    }
                }
            }
            binding.weekView.notifyDatasetChanged();
        }
    }
    protected String getEventTitle(Calendar time) {
        /*if (specialistName !=null && !specialistName.equalsIgnoreCase("")){
            return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
        }else {
            return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
        }*/
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
      }
    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
    @Override
    public void onEmptyViewLongPress(Calendar time) {
       // Toast.makeText(context, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }
    public WeekView getWeekView() {
        return binding.weekView;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        Log.d(TAG, "onMonthChange: ");
        //Toast.makeText(context, "OnMonth ", Toast.LENGTH_SHORT).show();
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent("First", getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(com.appointmentbooking.R.color.event_color_01));
        events.add(event);
        return  events;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onEmptyViewClicked(Calendar calendar) {  // selected date

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAddEventClicked(Calendar calendar, Calendar endTime) {

    }
    @Override
    public void onDrop(View view, Calendar date) {
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 400 && resultCode == RESULT_OK && data !=null && !data.getStringExtra("selectedDate").equalsIgnoreCase("")){
            //Toast.makeText(context, ""+data.getStringExtra("selectedDate"), Toast.LENGTH_SHORT).show();
            String selectedDate =data.getStringExtra("selectedDate");
            todaySelectedDate = selectedDate;
            final SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            currentDate = todaySelectedDate;
            Date comingDate  =DateFormat.orderDateCCalendar(selectedDate);
            c.setTime(DateFormat.orderDateCCalendar(selectedDate));
            if (specialistName != null && !specialistName.equalsIgnoreCase("")) {
                binding.weekView.setMaxDate(c);
                binding.weekView.setMinDate(c);
            }
            binding.viewDate.setText(DateFormat.orderDateFormatSendToShowThree(todaySelectedDate));
            binding.weekView.notifyDatasetChanged();
        }
    }
}
