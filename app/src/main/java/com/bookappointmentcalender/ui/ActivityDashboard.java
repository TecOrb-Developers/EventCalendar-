package com.bookappointmentcalender.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.appointmentbooking.model.GetAllSpecialistModel;
import com.bookappointmentcalender.R;
import com.bookappointmentcalender.base.BaseActivity;
import com.bookappointmentcalender.databinding.ActivityDashboardBinding;
import com.bookappointmentcalender.dialogs.DialogCalenderDays;
import com.bookappointmentcalender.fragment.FragmentDayCal;
import com.bookappointmentcalender.fragment.FragmentSpeCal;
import com.bookappointmentcalender.helper.DateFormat;
import com.bookappointmentcalender.helper.HRPrefManager;
import com.bookappointmentcalender.helper.IntentHelper;
import com.bookappointmentcalender.model.CalenderBookingModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ActivityDashboard extends BaseActivity implements DialogCalenderDays.OnClickDay {
    ActivityDashboardBinding binding;
    private Context context;
    private Fragment currentFragment;
    private Activity activityName;
    public static final int KLGOIN_INTENT = 002;
    private Fragment fragment = null;
    public String tag = "Home";
    private String differenceNumOfDay;
    private int backCounter = 2;
    private int viewTypeCalender =3;
    private CalenderBookingModel calenderBookingModel;
    private GetAllSpecialistModel allSpecialistListModel;
    private List<GetAllSpecialistModel.ResultBean> staffList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binding.toolbarMenu.toolbarDate.setOnClickListener(v->{
            startActivityForResult(IntentHelper.getActivityVerticalCalendar(context)
                    .putExtra("viewTypeCalender",viewTypeCalender)
            ,400);
        });
        init();
            loadHomeFragment();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setHeaderScrollDate (String lastVisibleDate){
        if (lastVisibleDate !=null && !lastVisibleDate.equalsIgnoreCase("")){
            int date = DateFormat.getDateOnlyDash(lastVisibleDate);
            Log.d("sfsg", "setHeaderScrollDate: "+lastVisibleDate +"\n"+date);
            String currentDate = DateFormat.getCurrentDateDash();
            //Log.d("sdfsdfsdfadsfsdf", "onActivityResult"+  DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendarDash(currentDate),DateFormat.orderDateCCalendarDash(lastVisibleDate)));
          // String difference=DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendarDash(currentDate),DateFormat.orderDateCCalendarDash(lastVisibleDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateFormat.orderDateCCalendarDash(lastVisibleDate));
            if(viewTypeCalender==7){
                calendar.add(Calendar.DATE,6);
            }else if (viewTypeCalender==1){
                calendar.add(Calendar.DATE,0);
            }else{
                calendar.add(Calendar.DATE,2);
            }
            String date2 = android.text.format.DateFormat.format("dd MMMM, yyyy", calendar).toString();
            if (viewTypeCalender==1){
                binding.toolbarMenu.toolbarDate.setText(date2);
            }else{
                binding.toolbarMenu.toolbarDate.setText(""+date+"  -  "+date2);
            }
        }else{
            if(!HRPrefManager.getInstance(context).getKeyIsAllStaff() && HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("day")){
                binding.toolbarMenu.toolbarDate.setText(DateFormat.getCurrentDateDashSingleNoSpecialist(viewTypeCalender));
        }else{
                binding.toolbarMenu.toolbarDate.setText(DateFormat.getCurrentDateDash(viewTypeCalender));
            }
        }
    }
    public void setViewSelectedCalenderType(int typ){
        viewTypeCalender = typ;
    }

    private void init() {
        binding.toolbarMenu.tvEdit.setOnClickListener(v -> {
            DialogCalenderDays filterDialog = new DialogCalenderDays(context, this);
            filterDialog.show(getSupportFragmentManager(), filterDialog.getTag());
        });

        for (int i=0; i<10; i++){
            GetAllSpecialistModel.ResultBean resultBean = new GetAllSpecialistModel.ResultBean();
            resultBean.set_id(""+i);
            resultBean.setName("ajay".concat("0"+i));
            resultBean.setChecked(false);
            staffList.add(resultBean);
        }
        allSpecialistListModel = new GetAllSpecialistModel();
        allSpecialistListModel.setResult(staffList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadHomeFragment() {
        setFragment(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setFragment(int position) {
        switch (position) {
            case 0:
                tag = "Home";
                binding.toolbarMenu.toolbarRoot.setVisibility(View.VISIBLE);

                binding.toolbarMenu.toolbarTitle.setText(HRPrefManager.getInstance(context).getWhichSpecialistSelectedName());
                updateStatus();
                if (HRPrefManager.getInstance(context).getKeyIsAllStaff() && HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("day")) {
                    loadFragment(new FragmentSpeCal(HRPrefManager.getInstance(context).getKeyAllSpecialist()));
                    binding.toolbarMenu.toolbarDate.setVisibility(View.GONE);
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.GONE);

                }else if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() != null &&
                        !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")
                        && HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("Week")) {
                    viewTypeCalender =7;
                    differenceNumOfDay ="";
                    setHeaderScrollDate("");
                    loadFragment(new FragmentDayCal(7));
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                    binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                }else if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() != null &&
                        !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")
                        && HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("threeDay")) {
                    viewTypeCalender =3;
                    setHeaderScrollDate("");
                    differenceNumOfDay ="";
                    loadFragment(new FragmentDayCal(3));
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                    binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                }else if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() != null && !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")) {
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.GONE);
                    loadFragment(new FragmentSpeCal(HRPrefManager.getInstance(context).getWhichSpecialistSelectedName(), 1));
                    binding.toolbarMenu.toolbarDate.setVisibility(View.GONE);

                }else if (!HRPrefManager.getInstance(context).getKeyIsAllStaff() && HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("day")){
                    viewTypeCalender =1;
                    setHeaderScrollDate("");
                    differenceNumOfDay ="";
                    loadFragment(new FragmentDayCal(1));
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                    binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                } else {
                    viewTypeCalender =3;
                    setHeaderScrollDate("");
                    differenceNumOfDay ="";
                    loadFragment(new FragmentDayCal(3));
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                    binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                }
                break;

            default:
                tag = "none";
                fragment = new Fragment();
                break;
        }
        this.currentFragment = fragment;
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit);
        transaction.replace(R.id.frame_dashboard, fragment, tag).commitAllowingStateLoss();
    }
    public void updateStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Drawable background = getResources().getDrawable(R.drawable.bg_white); //bg_gradient is your gradient.
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {
        switch (tag) {
            case "Home":
                if (backCounter > 1) {
                    backCounter--;
                    startCounterToSetToDefault();
                    Toast.makeText(this, this.getResources().getString(R.string.txt_press_back_again_to_exit), Toast.LENGTH_SHORT).show();
                } else {
                    finishAffinity();
                }
                return;
            case "Appointment":
            case "Specialist":
            case "Dashboard":
            case "Chat":
            case "Account":
                getSupportFragmentManager().popBackStackImmediate();
                loadHomeFragment();
                return;

            default:
                if (getSupportFragmentManager().getBackStackEntryCount() != 1) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    getSupportFragmentManager().popBackStack();
                    loadHomeFragment();
                }
        }
        // default action for any other fragment (return to previous)
    }

    public GetAllSpecialistModel getAllSpecialistListModel() {
        return allSpecialistListModel;
    }

    private void startCounterToSetToDefault() {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                backCounter = 2;
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(int viewId, View view) {
        switch (view.getId()) {
            case R.drawable.back_icon:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClickDeleteTab(int position) {
        switch (position) {
            case 2:
                String selectedSpecialistId = HRPrefManager.getInstance(context).getWhichSpecialistSelected();
                if (allSpecialistListModel !=null && allSpecialistListModel.getResult().size()>0){
                    for (int i = 0; i < allSpecialistListModel.getResult().size(); i++) {
                        if (selectedSpecialistId != null && selectedSpecialistId.equalsIgnoreCase(allSpecialistListModel.getResult().get(i).get_id())) {
                            loadFragment(new FragmentSpeCal(allSpecialistListModel.getResult().get(i).getName(), 1));
                            binding.toolbarMenu.toolbarDate.setVisibility(View.GONE);
                            binding.toolbarMenu.toolbarTitle.setVisibility(View.GONE);
                        }
                    }
                }else {
                    viewTypeCalender =1;
                    setHeaderScrollDate("");
                    differenceNumOfDay ="";
                    loadFragment(new FragmentDayCal(1));
                    binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                    binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                }
                break;

            case 3:
                viewTypeCalender =3;
                setHeaderScrollDate("");
                differenceNumOfDay ="";
                loadFragment(new FragmentDayCal(3));
                binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                break;

            case 4:
                viewTypeCalender =7;
                setHeaderScrollDate("");
                differenceNumOfDay ="";
                loadFragment(new FragmentDayCal(7));
                binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
                binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }


    @Override
    public void onClickAllDayTab(GetAllSpecialistModel allSpecialistListModel) {
        loadFragment(new FragmentSpeCal(allSpecialistListModel));
        binding.toolbarMenu.toolbarDate.setVisibility(View.GONE);
        binding.toolbarMenu.toolbarTitle.setVisibility(View.GONE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClickSpecialist(String specialistName, int position) {
        if (HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("Week")) {
            viewTypeCalender =7;
            setHeaderScrollDate("");
            differenceNumOfDay ="";
            binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
            binding.toolbarMenu.toolbarTitle.setText(HRPrefManager.getInstance(context).getWhichSpecialistSelectedName());
            loadFragment(new FragmentDayCal(7));
            binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
        } else if (HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("threeDay")) {
            viewTypeCalender =3;
            setHeaderScrollDate("");
            differenceNumOfDay ="";
            binding.toolbarMenu.toolbarTitle.setVisibility(View.VISIBLE);
            binding.toolbarMenu.toolbarTitle.setText(HRPrefManager.getInstance(context).getWhichSpecialistSelectedName());
            loadFragment(new FragmentDayCal(3));
            binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
        }else{
            binding.toolbarMenu.toolbarTitle.setVisibility(View.GONE);
            loadFragment(new FragmentSpeCal(specialistName, position));
            binding.toolbarMenu.toolbarDate.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KLGOIN_INTENT && resultCode == RESULT_OK) {
                setFragment(0);

        }else if(requestCode == 400 && resultCode == RESULT_OK && data !=null && !data.getStringExtra("selectedDate").equalsIgnoreCase("")){
            if (viewTypeCalender ==7 ){
                Log.d("fsdfsf", "onActivityResult: "+data.getStringExtra("selectedDate"));
                String currentDate = DateFormat.getCurrentDate();
                Log.d("sdfsdfsdf", "onActivityResult"+  DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                setDiffernce(DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                loadFragment(new FragmentDayCal(7));
                setHeaderScrollDate(DateFormat.getBlocketDateFormatDash(data.getStringExtra("selectedDate")));
                binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
            }else if (viewTypeCalender==1){
                Log.d("fsdfsf", "onActivityResult: "+data.getStringExtra("selectedDate"));
                String currentDate = DateFormat.getCurrentDate();
                Log.d("sdfsdfsdf", "onActivityResult"+  DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                setDiffernce(DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                loadFragment(new FragmentDayCal(1));
                setHeaderScrollDate(DateFormat.getBlocketDateFormatDash(data.getStringExtra("selectedDate")));
                binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);

            }else {
                Log.d("fsdfsf", "onActivityResult: "+data.getStringExtra("selectedDate"));
                String currentDate = DateFormat.getCurrentDate();
                Log.d("sdfsdfsdf", "onActivityResult"+  DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                        setDiffernce(DateFormat.getDaysDurationCalKot(DateFormat.orderDateCCalendar(currentDate),DateFormat.orderDateCCalendar(data.getStringExtra("selectedDate"))));
                loadFragment(new FragmentDayCal(3));
                setHeaderScrollDate(DateFormat.getBlocketDateFormatDash(data.getStringExtra("selectedDate")));
                binding.toolbarMenu.toolbarDate.setVisibility(View.VISIBLE);
            }
        }
    }
    public void  setDiffernce(String numOfDate){
        this.differenceNumOfDay = numOfDate;
    }
    public String  getDifference() {
        return differenceNumOfDay;
    }
    protected void onResume() {
        super.onResume();
    }
}
