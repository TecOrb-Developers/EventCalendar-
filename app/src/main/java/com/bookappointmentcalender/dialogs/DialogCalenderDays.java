package com.bookappointmentcalender.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.adapter.AdapterCalSpecialistDialog;
import com.bookappointmentcalender.base.BaseFragmentDialog;
import com.bookappointmentcalender.databinding.DialogCalenderViewBinding;
import com.bookappointmentcalender.helper.HRPrefManager;
import com.appointmentbooking.model.GetAllSpecialistModel;

import java.util.ArrayList;
import java.util.List;

public class DialogCalenderDays extends BaseFragmentDialog implements View.OnClickListener , AdapterCalSpecialistDialog.OnClickSpecialist {
    private Context context;
    private DialogCalenderViewBinding binding;
    private OnClickDay clickDay;
    private String selectedSpecialistId;
    private GetAllSpecialistModel allSpecialistListModel;
    private List<GetAllSpecialistModel.ResultBean> staffList = new ArrayList<>();
    private boolean loaderStatus =true;
    public DialogCalenderDays(Context context , OnClickDay clickDay){
        this.context = context;
        this.clickDay = clickDay;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_calender_view, null, false);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        binding.AllSpecialist.setOnClickListener(this);
        binding.tvSpecialist.setOnClickListener(this);
        binding.imageDay.setOnClickListener(this);
        binding.tvDay.setOnClickListener(this);
        binding.imageThreeDays.setOnClickListener(this);
        binding.tvThreeDays.setOnClickListener(this);
        binding.imageWeek.setOnClickListener(this);
        binding.tvWeek.setOnClickListener(this);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.galleryRec.setLayoutManager(horizontalLayoutManagaer);
        binding.tvDone.setOnClickListener(v->{
            dismiss();
        });
        if (HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("day")){
            binding.tvDay.setTextColor(context.getResources().getColor(R.color.orange));
        }else if (HRPrefManager.getInstance(context).getWhichDaySelected().equalsIgnoreCase("week")){
            binding.tvWeek.setTextColor(context.getResources().getColor(R.color.orange));
        }else {
            binding.tvThreeDays.setTextColor(context.getResources().getColor(R.color.orange));
        }
        if (HRPrefManager.getInstance(context).getKeyIsAllStaff()){
            binding.AllSpecialist.setTextColor(context.getResources().getColor(R.color.orange));
        }else {
            selectedSpecialistId = HRPrefManager.getInstance(context).getWhichSpecialistSelected();
            Log.d("sfsdfsfgs", "onViewCreated: "+selectedSpecialistId);
        }
        for (int i=0; i<10; i++){
            GetAllSpecialistModel.ResultBean resultBean = new GetAllSpecialistModel.ResultBean();
            resultBean.set_id(""+i);
            resultBean.setName("ajay".concat(" 0"+i));
            resultBean.setChecked(false);
            staffList.add(resultBean);
        }
        allSpecialistListModel = new GetAllSpecialistModel();
        allSpecialistListModel.setResult(staffList);
        setListOfEmployee(allSpecialistListModel);

    }

    private void setListOfEmployee(GetAllSpecialistModel allSpecialistListModel) {

            if (selectedSpecialistId !=null && !selectedSpecialistId.equalsIgnoreCase("")){
                for (int i =0;  i<allSpecialistListModel.getResult().size(); i++){
                    if (selectedSpecialistId.equalsIgnoreCase(allSpecialistListModel.getResult().get(i).get_id())){
                        allSpecialistListModel.getResult().get(i).setChecked(true);
                    }
                }
            }else{
                if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() !=null && !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")){
                }else{
                    if (allSpecialistListModel.getResult().size()>0){
                        allSpecialistListModel.getResult().get(0).setChecked(true);
                        HRPrefManager.getInstance(context).setWhichSpecialistSelected(allSpecialistListModel.getResult().get(0).get_id());
                        HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(allSpecialistListModel.getResult().get(0).getName());
                    }
                }
            }
            if (allSpecialistListModel !=null && allSpecialistListModel.getResult().size()>0){
                AdapterCalSpecialistDialog  mAdapter = new AdapterCalSpecialistDialog(allSpecialistListModel.getResult(), context ,this);
                binding.galleryRec.setAdapter(mAdapter);
            }else{
                binding.AllSpecialist.setVisibility(View.GONE);
            }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (metrics.heightPixels * 0.50));// here i have fragment height 30% of window's height you can set it as per your requirement
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_new;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSpecialist:
                clickDay.onClickDeleteTab(0);
                dismiss();
                break;
            case R.id.AllSpecialist:
                clickDay.onClickAllDayTab(allSpecialistListModel);
                HRPrefManager.getInstance(context).setKeyIsAllStaff(true);
                HRPrefManager.getInstance(context).setWhichDaySelected("day");
                HRPrefManager.getInstance(context).setKeyAllSpecialist(allSpecialistListModel);
                dismiss();
                break;
            case R.id.tvDay:
            case R.id.imageDay:  // work all staff in this case
                HRPrefManager.getInstance(context).setWhichDaySelected("day");
                clickDay.onClickDeleteTab(2);
                dismiss();
                break;
            case R.id.tvThreeDays:   // work only single staff in this case
            case R.id.imageThreeDays:
                HRPrefManager.getInstance(context).setWhichDaySelected("threeDay");
                HRPrefManager.getInstance(context).setKeyIsAllStaff(false);
                if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() !=null && !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")){
                }else {
                    if (allSpecialistListModel !=null && allSpecialistListModel.getResult() !=null && allSpecialistListModel.getResult().size()>0){
                        HRPrefManager.getInstance(context).setWhichSpecialistSelected(allSpecialistListModel.getResult().get(0).get_id());
                        HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(allSpecialistListModel.getResult().get(0).getName());
                    }
                }
                clickDay.onClickDeleteTab(3);
                dismiss();
                break;

            case R.id.tvWeek:   // work only single staff in this case
            case R.id.imageWeek:
                HRPrefManager.getInstance(context).setWhichDaySelected("Week");
                HRPrefManager.getInstance(context).setKeyIsAllStaff(false);

                if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() !=null && !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")) {
                }else{
                    if (allSpecialistListModel !=null && allSpecialistListModel.getResult().size()>0){
                        HRPrefManager.getInstance(context).setWhichSpecialistSelected(allSpecialistListModel.getResult().get(0).get_id());
                        HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(allSpecialistListModel.getResult().get(0).getName());
                    }
                }
                clickDay.onClickDeleteTab(4);
                dismiss();
                break;

            default:
                break;
        }
    }
    @Override
    public void onTaskSuccess(Object responseObj) {
        super.onTaskSuccess(responseObj);
        if (responseObj instanceof GetAllSpecialistModel){
             allSpecialistListModel = (GetAllSpecialistModel)responseObj;
             if (selectedSpecialistId !=null && !selectedSpecialistId.equalsIgnoreCase("")){
                 for (int i =0;  i<allSpecialistListModel.getResult().size(); i++){
                     if (selectedSpecialistId.equalsIgnoreCase(allSpecialistListModel.getResult().get(i).get_id())){
                         allSpecialistListModel.getResult().get(i).setChecked(true);
                     }
                 }
             }else{
                 if (HRPrefManager.getInstance(context).getWhichSpecialistSelected() !=null && !HRPrefManager.getInstance(context).getWhichSpecialistSelected().equalsIgnoreCase("")){
                 }else{
                     if (allSpecialistListModel.getResult().size()>0){
                         allSpecialistListModel.getResult().get(0).setChecked(true);
                         HRPrefManager.getInstance(context).setWhichSpecialistSelected(allSpecialistListModel.getResult().get(0).get_id());
                         HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(allSpecialistListModel.getResult().get(0).getName());
                     }
                 }
             }
             if (allSpecialistListModel !=null && allSpecialistListModel.getResult().size()>0){
                 AdapterCalSpecialistDialog  mAdapter = new AdapterCalSpecialistDialog(allSpecialistListModel.getResult(), context ,this);
                 binding.galleryRec.setAdapter(mAdapter);
             }else{
                 binding.AllSpecialist.setVisibility(View.GONE);
             }
        }
    }
    @Override
    public void onClickSpecialist(String specialistName,String selectedSpecialistId) {
        HRPrefManager.getInstance(context).setWhichSpecialistSelected(selectedSpecialistId);
        HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(specialistName);
        clickDay.onClickSpecialist(specialistName,1);
        this.selectedSpecialistId =selectedSpecialistId;
        HRPrefManager.getInstance(context).setKeyIsAllStaff(false);
        for (int i=0; i<allSpecialistListModel.getResult().size(); i++){
            if (selectedSpecialistId !=null && selectedSpecialistId.equalsIgnoreCase(allSpecialistListModel.getResult().get(i).get_id())){
                allSpecialistListModel.getResult().get(i).setChecked(true);
                HRPrefManager.getInstance(context).setWhichSpecialistSelected(allSpecialistListModel.getResult().get(i).get_id());
                HRPrefManager.getInstance(context).setWhichSpecialistSelectedName(allSpecialistListModel.getResult().get(i).getName());
            }
        }
        dismiss();
    }
    public interface OnClickDay {
        void onClickDeleteTab(int position);
        void onClickAllDayTab(GetAllSpecialistModel allSpecialistListModel);
        void onClickSpecialist(String specialistName,int position);
    }
}