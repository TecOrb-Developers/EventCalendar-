package com.bookappointmentcalender.ui;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.bookappointmentcalender.R;
import com.appointmentbooking.adapter.HRCalendarListAdapter;
import com.appointmentbooking.calender.HRDatabase;
import com.appointmentbooking.calender.HRDateUtil;
import com.bookappointmentcalender.databinding.ActivityVerticalCalendarBinding;
import org.joda.time.LocalDate;

public class ActivityVerticalCalendar extends AppCompatActivity {
    ActivityVerticalCalendarBinding binding;
    private String date, month;
    private HRCalendarListAdapter HRCalendarListAdapter;
    private HRDatabase HRDatabase;
    private int viewTypeCalender ;
    private LocalDate selectedDate ;
    private LocalDate startDate = HRDateUtil.getTodaysDate();
    private LocalDate endDate = HRDateUtil.getTodaysDate().plusDays(2);
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vertical_calendar);
        context = this;
        if (getIntent() !=null){
            viewTypeCalender = getIntent().getIntExtra("viewTypeCalender",0);
            startDate = HRDateUtil.getTodaysDate();
            date = startDate.toString();
            endDate = HRDateUtil.getTodaysDate().plusDays(viewTypeCalender-1);
        }
        HRCalendarListAdapter = new HRCalendarListAdapter(this, listener, startDate,endDate);
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
             selectedDate = (LocalDate) view.getTag();
            date = selectedDate.toString();
            Log.d("sfssf", "onClick: "+selectedDate +"\n"+selectedDate.plusDays(viewTypeCalender-1) +"\n"+date);
            HRCalendarListAdapter.updateSelection(selectedDate,  selectedDate.plusDays(viewTypeCalender-1));
        }
    };
}
