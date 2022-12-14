package com.bookappointmentcalender.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bookappointmentcalender.R;
import com.appointmentbooking.adapter.CalenderListSpeAdapter;
import com.appointmentbooking.calender.HRDatabase;
import com.appointmentbooking.calender.HRDateUtil;
import com.bookappointmentcalender.databinding.ActivityVerticalCalendarBinding;

import org.joda.time.LocalDate;

public class ActivityVerticalCaleSpecialist extends AppCompatActivity {
    ActivityVerticalCalendarBinding binding;
    private String date, month;
    private CalenderListSpeAdapter HRCalendarListAdapter;
    private HRDatabase HRDatabase;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vertical_calendar);
        context = this;
        date = HRDateUtil.getTodaysDate().toString();
        HRCalendarListAdapter = new CalenderListSpeAdapter(this, listener, HRDateUtil.getTodaysDate(), null);
        HRDatabase = new HRDatabase(this);
        binding.calendarList.setAdapter(HRCalendarListAdapter);
        binding.toolbar.tvEdit.setVisibility(View.GONE);

        binding.toolbar.toolbarTitle.setAllCaps(true);
        binding.toolbar.toolbarTitle.setText(getString(R.string.app_launcher_name));

        binding.toolbar.toolbarBack.setOnClickListener(v->{
            finish();
        });
        binding.buttonDone.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.putExtra("selectedDate",date);
            setResult(RESULT_OK,intent);
            finish();
        });
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LocalDate selectedDate = (LocalDate) view.getTag();
            date = selectedDate.toString();
            HRCalendarListAdapter.updateSelection(selectedDate, null);
        }
    };
}