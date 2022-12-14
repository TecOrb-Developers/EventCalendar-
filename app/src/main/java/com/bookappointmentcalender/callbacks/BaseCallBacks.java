package com.bookappointmentcalender.callbacks;

import java.util.ArrayList;

public interface BaseCallBacks {
    void onTaskSuccess(Object responseObj);
    void onTaskError(String errorMsg);
    void onSessionExpire(String message);
    void onInternetNotFound();
    void showLoader();
    void dismissLoader();
    void onFragmentDetach(String fragmentTag);
    void onLoadMore(Object responseObj);
    void onLoadMore(ArrayList<?> list);
}
