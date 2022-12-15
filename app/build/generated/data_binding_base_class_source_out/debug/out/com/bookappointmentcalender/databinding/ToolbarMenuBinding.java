// Generated by data binding compiler. Do not edit!
package com.bookappointmentcalender.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bookappointmentcalender.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ToolbarMenuBinding extends ViewDataBinding {
  @NonNull
  public final ImageView toolbarBack;

  @NonNull
  public final TextView toolbarDate;

  @NonNull
  public final LinearLayout toolbarRoot;

  @NonNull
  public final TextView toolbarTitle;

  @NonNull
  public final AppCompatImageView tvEdit;

  protected ToolbarMenuBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView toolbarBack, TextView toolbarDate, LinearLayout toolbarRoot, TextView toolbarTitle,
      AppCompatImageView tvEdit) {
    super(_bindingComponent, _root, _localFieldCount);
    this.toolbarBack = toolbarBack;
    this.toolbarDate = toolbarDate;
    this.toolbarRoot = toolbarRoot;
    this.toolbarTitle = toolbarTitle;
    this.tvEdit = tvEdit;
  }

  @NonNull
  public static ToolbarMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.toolbar_menu, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ToolbarMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ToolbarMenuBinding>inflateInternal(inflater, R.layout.toolbar_menu, root, attachToRoot, component);
  }

  @NonNull
  public static ToolbarMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.toolbar_menu, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ToolbarMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ToolbarMenuBinding>inflateInternal(inflater, R.layout.toolbar_menu, null, false, component);
  }

  public static ToolbarMenuBinding bind(@NonNull View view) {
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
  public static ToolbarMenuBinding bind(@NonNull View view, @Nullable Object component) {
    return (ToolbarMenuBinding)bind(component, view, R.layout.toolbar_menu);
  }
}