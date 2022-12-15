package com.bookappointmentcalender.base;

import android.content.Context;

import androidx.fragment.app.DialogFragment;

import com.bookappointmentcalender.callbacks.BaseCallBacks;

import java.util.ArrayList;


public class BaseFragmentDialog extends DialogFragment
        implements BaseCallBacks {

    private BaseCallBacks callBacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseCallBacks){
            callBacks = (BaseCallBacks)context;
        }
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

}
