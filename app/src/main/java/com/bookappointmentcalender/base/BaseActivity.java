package com.bookappointmentcalender.base;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bookappointmentcalender.R;
import com.bookappointmentcalender.callbacks.BaseCallBacks;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity
        implements BaseCallBacks, View.OnClickListener {
    private ProgressDialog progressDialog;
    private Context context;
    private static List<AlertDialog> sessionExpireDialogList = new ArrayList<>();
    private static List<AlertDialog> errorDialogList = new ArrayList<>();
    public abstract void onClick(int viewId, View view);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        context = this;
      }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setAppTitle(String title) {
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;

            default:
                onClick(v.getId(), v);
                break;
        }
    }

    @Override
    public void dismissLoader() {
        if (!isFinishing()) progressDialog.dismiss();
    }

    @Override
    public void onTaskSuccess(Object responseObj) {
        dismissLoader();
    }


    @Override
    public void onTaskError(String errorMsg) {

    }

    @Override
    public void onSessionExpire(String message) {

    }

    @Override
    public void onInternetNotFound() {

    }

    @Override
    public void showLoader() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentDetach(String fragmentTag) {
    }
    @Override
    public void onLoadMore(Object responseObj) {
    }
    @Override
    public void onLoadMore(ArrayList<?> list) {

    }
    public Fragment getFragment(String tag) {
        return getFragmentManager().findFragmentByTag(tag);
    }

}
