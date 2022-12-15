package com.bookappointmentcalender;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.bookappointmentcalender.databinding.ActivityDashboardBindingImpl;
import com.bookappointmentcalender.databinding.ActivityVerticalCalendarBindingImpl;
import com.bookappointmentcalender.databinding.DialogCalenderViewBindingImpl;
import com.bookappointmentcalender.databinding.FragmentDayCalBindingImpl;
import com.bookappointmentcalender.databinding.FragmentSpeCalBindingImpl;
import com.bookappointmentcalender.databinding.ItemSpecialistNameBindingImpl;
import com.bookappointmentcalender.databinding.ToolbarDashboardBindingImpl;
import com.bookappointmentcalender.databinding.ToolbarMenuBindingImpl;
import com.bookappointmentcalender.databinding.ToolbarSearchPlacesBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDASHBOARD = 1;

  private static final int LAYOUT_ACTIVITYVERTICALCALENDAR = 2;

  private static final int LAYOUT_DIALOGCALENDERVIEW = 3;

  private static final int LAYOUT_FRAGMENTDAYCAL = 4;

  private static final int LAYOUT_FRAGMENTSPECAL = 5;

  private static final int LAYOUT_ITEMSPECIALISTNAME = 6;

  private static final int LAYOUT_TOOLBARDASHBOARD = 7;

  private static final int LAYOUT_TOOLBARMENU = 8;

  private static final int LAYOUT_TOOLBARSEARCHPLACES = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.activity_dashboard, LAYOUT_ACTIVITYDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.activity_vertical_calendar, LAYOUT_ACTIVITYVERTICALCALENDAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.dialog_calender_view, LAYOUT_DIALOGCALENDERVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.fragment_day_cal, LAYOUT_FRAGMENTDAYCAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.fragment_spe_cal, LAYOUT_FRAGMENTSPECAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.item_specialist_name, LAYOUT_ITEMSPECIALISTNAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.toolbar_dashboard, LAYOUT_TOOLBARDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.toolbar_menu, LAYOUT_TOOLBARMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookappointmentcalender.R.layout.toolbar_search_places, LAYOUT_TOOLBARSEARCHPLACES);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDASHBOARD: {
          if ("layout/activity_dashboard_0".equals(tag)) {
            return new ActivityDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYVERTICALCALENDAR: {
          if ("layout/activity_vertical_calendar_0".equals(tag)) {
            return new ActivityVerticalCalendarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_vertical_calendar is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGCALENDERVIEW: {
          if ("layout/dialog_calender_view_0".equals(tag)) {
            return new DialogCalenderViewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_calender_view is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDAYCAL: {
          if ("layout/fragment_day_cal_0".equals(tag)) {
            return new FragmentDayCalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_day_cal is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSPECAL: {
          if ("layout/fragment_spe_cal_0".equals(tag)) {
            return new FragmentSpeCalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_spe_cal is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSPECIALISTNAME: {
          if ("layout/item_specialist_name_0".equals(tag)) {
            return new ItemSpecialistNameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_specialist_name is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARDASHBOARD: {
          if ("layout/toolbar_dashboard_0".equals(tag)) {
            return new ToolbarDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARMENU: {
          if ("layout/toolbar_menu_0".equals(tag)) {
            return new ToolbarMenuBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_menu is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARSEARCHPLACES: {
          if ("layout/toolbar_search_places_0".equals(tag)) {
            return new ToolbarSearchPlacesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_search_places is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/activity_dashboard_0", com.bookappointmentcalender.R.layout.activity_dashboard);
      sKeys.put("layout/activity_vertical_calendar_0", com.bookappointmentcalender.R.layout.activity_vertical_calendar);
      sKeys.put("layout/dialog_calender_view_0", com.bookappointmentcalender.R.layout.dialog_calender_view);
      sKeys.put("layout/fragment_day_cal_0", com.bookappointmentcalender.R.layout.fragment_day_cal);
      sKeys.put("layout/fragment_spe_cal_0", com.bookappointmentcalender.R.layout.fragment_spe_cal);
      sKeys.put("layout/item_specialist_name_0", com.bookappointmentcalender.R.layout.item_specialist_name);
      sKeys.put("layout/toolbar_dashboard_0", com.bookappointmentcalender.R.layout.toolbar_dashboard);
      sKeys.put("layout/toolbar_menu_0", com.bookappointmentcalender.R.layout.toolbar_menu);
      sKeys.put("layout/toolbar_search_places_0", com.bookappointmentcalender.R.layout.toolbar_search_places);
    }
  }
}
