package com.bookappointmentcalender.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.callbacks.BaseCallBacks;
import com.bookappointmentcalender.databinding.FragmentSpeCalBinding;

import java.util.ArrayList;


public class BaseFragment extends Fragment implements BaseCallBacks {
    private BaseCallBacks callBacks;
    private Context context;
    private FragmentSpeCalBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseCallBacks){
            callBacks = (BaseCallBacks)context;
        }
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
    }
    @Override
    public void onDetach() {
        super.onDetach();
        callBacks = null;
    }

    @Override
    public void onTaskSuccess(Object responseObj) {
        if(callBacks!=null)callBacks.onTaskSuccess(responseObj);
    }

    @Override
    public void onTaskError(String errorMsg) {
        if(callBacks!=null)callBacks.onTaskError(errorMsg);
    }

    @Override
    public void onSessionExpire(String message) {
        if(callBacks!=null)callBacks.onSessionExpire(message);
    }


    @Override
    public void onInternetNotFound() {
        if(callBacks!=null)callBacks.onInternetNotFound();
    }


    @Override
    public void showLoader() {
        if(callBacks!=null)callBacks.showLoader();
    }

    @Override
    public void dismissLoader() {
        if(callBacks!=null)callBacks.dismissLoader();
    }

    @Override
    public void onFragmentDetach(String fragmentTag) {
        if(callBacks!=null)callBacks.onFragmentDetach(fragmentTag);
    }

    @Override
    public void onLoadMore(Object responseObj) {
    }
    @Override
    public void onLoadMore(ArrayList<?> list) {

    }
}
