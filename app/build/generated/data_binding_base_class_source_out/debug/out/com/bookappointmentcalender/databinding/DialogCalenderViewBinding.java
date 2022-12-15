// Generated by data binding compiler. Do not edit!
package com.bookappointmentcalender.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bookappointmentcalender.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class DialogCalenderViewBinding extends ViewDataBinding {
  @NonNull
  public final TextView AllSpecialist;

  @NonNull
  public final CardView frameLayout;

  @NonNull
  public final RecyclerView galleryRec;

  @NonNull
  public final ImageView imageDay;

  @NonNull
  public final ImageView imageThreeDays;

  @NonNull
  public final ImageView imageWeek;

  @NonNull
  public final NestedScrollView scrollView;

  @NonNull
  public final TextView tvDay;

  @NonNull
  public final TextView tvDone;

  @NonNull
  public final TextView tvSpecialist;

  @NonNull
  public final TextView tvThreeDays;

  @NonNull
  public final TextView tvWeek;

  protected DialogCalenderViewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView AllSpecialist, CardView frameLayout, RecyclerView galleryRec, ImageView imageDay,
      ImageView imageThreeDays, ImageView imageWeek, NestedScrollView scrollView, TextView tvDay,
      TextView tvDone, TextView tvSpecialist, TextView tvThreeDays, TextView tvWeek) {
    super(_bindingComponent, _root, _localFieldCount);
    this.AllSpecialist = AllSpecialist;
    this.frameLayout = frameLayout;
    this.galleryRec = galleryRec;
    this.imageDay = imageDay;
    this.imageThreeDays = imageThreeDays;
    this.imageWeek = imageWeek;
    this.scrollView = scrollView;
    this.tvDay = tvDay;
    this.tvDone = tvDone;
    this.tvSpecialist = tvSpecialist;
    this.tvThreeDays = tvThreeDays;
    this.tvWeek = tvWeek;
  }

  @NonNull
  public static DialogCalenderViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_calender_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DialogCalenderViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DialogCalenderViewBinding>inflateInternal(inflater, R.layout.dialog_calender_view, root, attachToRoot, component);
  }

  @NonNull
  public static DialogCalenderViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_calender_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DialogCalenderViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DialogCalenderViewBinding>inflateInternal(inflater, R.layout.dialog_calender_view, null, false, component);
  }

  public static DialogCalenderViewBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static DialogCalenderViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (DialogCalenderViewBinding)bind(component, view, R.layout.dialog_calender_view);
  }
}
