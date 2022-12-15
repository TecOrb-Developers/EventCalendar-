package com.bookappointmentcalender.fragment;

import android.graphics.RectF
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.appointmentbooking.weekviewCal.*
import com.bookappointmentcalender.R
import com.bookappointmentcalender.application.App
import com.bookappointmentcalender.base.BaseFragmentV4
import com.bookappointmentcalender.databinding.FragmentDayCalBinding
import com.bookappointmentcalender.helper.HRPrefManager
import com.bookappointmentcalender.model.CalenderBookingModel
import com.bookappointmentcalender.model.ProfileModel
import com.bookappointmentcalender.ui.ActivityDashboard
import kotlinx.android.synthetic.main.fragment_day_cal.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class FragmentDayCal(position: Int) : BaseFragmentV4(),
    WeekViewCal.EventClickListener,
    MonthLoaderCal.MonthChangeListener,
    WeekViewCal.EventLongPressListener,
    WeekViewCal.EmptyViewLongPressListener,
    WeekViewCal.EmptyViewClickListener,
    WeekViewCal.AddEventClickListener,
    WeekViewCal.DropListener {
    private  var TAG = "FragmentDayCal"
    var selectedPos = position
    var todaySelectedDate:String? =null
    var binding: FragmentDayCalBinding? = null
    val locale = Locale.getDefault()
     var eventsOne = ArrayList<WeekViewEvent>()
    private var calenderBookingMo : CalenderBookingModel?=null
    private var mWeekViewType = TYPE_THREE_DAY_VIEW
    private lateinit var shortDateFormat: java.text.DateFormat
    private lateinit var timeFormat: java.text.DateFormat
    private var counterNum=0
    private var counterDate =0
    private var profileModel: ProfileModel? = null
    private var activityDashboard : ActivityDashboard?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day_cal, container, false);
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortDateFormat = WeekViewUtil.getWeekdayWithNumericDayAndMonthFormat(App.getInstance(), true)
        timeFormat = android.text.format.DateFormat.getTimeFormat(context) ?: SimpleDateFormat("HH:mm", Locale.getDefault())
        activityDashboard = activity as ActivityDashboard?
        weekViewCal.isShowNowLine = true
       // weekViewCal.setAutoLimitTime(true)
        weekViewCal.eventClickListener = this
        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        // Set long press listener for events.
        weekViewCal.eventLongPressListener = this
        // Set long press listener for empty view
        weekViewCal.emptyViewLongPressListener = this
        // Set EmptyView Click Listener
        weekViewCal.emptyViewClickListener = this
        // Set AddEvent Click Listener
        weekViewCal.addEventClickListener = this
        weekViewCal.setLimitTime(0, 24)
        weekViewCal.isUsingCheckersStyle = true
        weekViewCal.columnGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics).toInt()
        weekViewCal.hourSeparatorHeight = weekViewCal.columnGap
        weekViewCal.isScrollNumberOfVisibleDays = true
        weekViewCal.dropListener = null
        weekViewCal.eventTextColor = resources.getColor(R.color.black)
       // weekViewCal.minDate = Calendar.getInstance()
        draggable_view.visibility =View.GONE
        weekViewCal.monthChangeListener = this

        if (selectedPos ==1){
            setDayViewType(TYPE_DAY_VIEW)
            activityDashboard?.setViewSelectedCalenderType(1)
        }else if (selectedPos ==7){
            activityDashboard?.setViewSelectedCalenderType(7)
            setDayViewType(TYPE_WEEK_VIEW)
        }else {
            setDayViewType(TYPE_THREE_DAY_VIEW)
            activityDashboard?.setViewSelectedCalenderType(3)
        }
        val cal = Calendar.getInstance()
        val currentHour = cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE) / 60.0
        weekViewCal.goToHour(Math.max(currentHour - 1, 0.0))
       // cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)

        if (activityDashboard?.difference !=null && !activityDashboard?.difference.equals("",true)){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, activityDashboard?.difference?.toInt()!!)
            weekViewCal.goToDate(calendar)
        }else{
            weekViewCal.goToDate(cal)
        }
        weekViewCal.scrollListener = object : WeekViewCal.ScrollListener {
            val monthFormatter = SimpleDateFormat("MMM", locale)
            val yearFormatter = SimpleDateFormat("yyyy", locale)
            val yearFormatteraadssdf = SimpleDateFormat("dd-MM-yyyy", locale)

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFirstVisibleDayChanged(newFirstVisibleDay: Calendar, oldFirstVisibleDay: Calendar?) {
                //we show just the month here, so no need to update it every time
                if (oldFirstVisibleDay == null || oldFirstVisibleDay.get(Calendar.MONTH) != newFirstVisibleDay.get(Calendar.MONTH)) {
                    val date = newFirstVisibleDay.time
                    if (cal.get(Calendar.YEAR) == newFirstVisibleDay.get(Calendar.YEAR)) {
                        weekViewCal.sideSubtitleText = ""
                        weekViewCal.sideTitleText = monthFormatter.format(date)
                        Log.d("dsfsfsdf", "onFirstVisibleDayChanged: "+yearFormatteraadssdf.format(date))
                    }else {
                        weekViewCal.sideTitleText = monthFormatter.format(date)
                        weekViewCal.sideSubtitleText = yearFormatter.format(date)
                        Log.d("dsfsfsdfsfdf", "onFirstVisibleDayChanged: "+yearFormatter.format(date))
                    }
                }else{
                    val yearFormatteraadssdf = SimpleDateFormat("dd-MM-yyyy", locale)
                    val yearFormatteraadssdfdasds = SimpleDateFormat("d", locale)
                    val date = newFirstVisibleDay.time
                    var scrolledDate = yearFormatteraadssdfdasds.format(date)
                    Log.d(TAG, "onFirstVisibleDayChanged: "+scrolledDate)
                    activityDashboard?.setHeaderScrollDate(yearFormatteraadssdf.format(date))
                    if(counterDate<2){
                        counterDate++
                    }else{
                        counterDate =0
                        Log.d("dsfsfsdfsfdfNew", "onFirstVisibleDayChanged: "+yearFormatteraadssdf.format(date))
                    }
                }
            }
        }
        setupDateTimeInterpreter(false)
        draggable_view.visibility = View.GONE
        weekViewCal.weekDaySubtitleInterpreter = object : WeekDaySubtitleInterpreter {
            val dateFormatTitle = SimpleDateFormat("d", locale)
            override fun getFormattedWeekDaySubtitle(date: Calendar): String = dateFormatTitle.format(date.time)
        }
        weekViewCal.eventClickListener = object : WeekViewCal.EventClickListener {
            override fun onEventClick(event: WeekViewEvent, eventRect: RectF) {
            }
        }
        initViews();
    }


    private fun initViews() {
        val c = Calendar.getInstance()
        val df1 = SimpleDateFormat("yyyy-MM-dd")
        todaySelectedDate = df1.format(c.timeInMillis) // date for sending backend
       // currentDate = df.format(c.timeInMillis)  // date for showing purpose
        callApiGetSpecilaistboking(todaySelectedDate)
    }
    private fun callApiGetSpecilaistboking(todaySelectedDate: String?) {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("date", todaySelectedDate)
             jsonObject.put("staffType", "single")
            jsonObject.put("type", "multipleDay")
            if(selectedPos == 7){
                jsonObject.put("filter", "seven")
            }else{
                jsonObject.put("filter", "three")
            }
            if (HRPrefManager.getInstance(context).whichSpecialistSelected !=null && !HRPrefManager.getInstance(context).whichSpecialistSelected.equals("",true)){
                jsonObject.put("specId", HRPrefManager.getInstance(context).whichSpecialistSelected)
            }else{
                if (activityDashboard?.allSpecialistListModel?.result?.size!! >0){
                    jsonObject.put("specId", activityDashboard?.allSpecialistListModel?.result?.get(0)?._id)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    override fun onTaskSuccess(responseObj: Any?) {
        super.onTaskSuccess(responseObj)
        if (responseObj is ProfileModel) {
            profileModel = responseObj
        } else if (responseObj is CalenderBookingModel){
            calenderBookingMo =responseObj;
            this.eventsOne.clear()
            //Toast.makeText(context, "OnTask ", Toast.LENGTH_SHORT).show()
            if (calenderBookingMo!!.getResult()!!.slot != null
                && calenderBookingMo!!.getResult()!!.slot!!.size > 0) {
                for (i in calenderBookingMo!!.getResult()!!.slot!!.indices) {
                    eventsOne.add(calenderBookingMo!!.getResult()!!.slot!!.get(i).toWeekViewEvent())
                }
            }
            if (calenderBookingMo!!.getResult()!!.breakTime != null
                && calenderBookingMo!!.getResult()!!.breakTime!!.isNotEmpty()) {
                for (i in calenderBookingMo!!.getResult()!!.breakTime!!.indices) {
                    eventsOne.add(calenderBookingMo!!.getResult()!!.breakTime!!.get(i).toBlockWeekViewEvent())
                }
            }
            Log.d("agsgsdfgvxcv", "onTaskSuccess: "+eventsOne.size)
            weekViewCal.notifyDataSetChanged()
        }
    }
    fun setupDateTimeInterpreter(shortDate: Boolean) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val timeFormat = SimpleDateFormat("HH:mm", locale)
        val dateFormatTitle = SimpleDateFormat("EEE", locale)
        weekViewCal.dateTimeInterpreter = object : DateTimeInterpreter {
            override fun getFormattedTimeOfDay(hour: Int, minutes: Int): String {
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minutes)
                return timeFormat.format(calendar.time)
            }
            override fun getFormattedWeekDayTitle(date: Calendar): String = dateFormatTitle.format(date.time)
        }
    }
    protected fun getEventTitle(startCal: Calendar, endCal: Calendar? = null, allDay: Boolean = false): String {
        val startDate = startCal.time
        val endDate = endCal?.time
        return when {
            allDay -> {
                if (endCal == null || WeekViewUtil.isSameDay(startCal, endCal))
                    shortDateFormat.format(startDate)
                else "${shortDateFormat.format(startDate)}..${shortDateFormat.format(endDate)}"
            }
            endCal == null -> "${shortDateFormat.format(startDate)} ${timeFormat.format(startDate)}"
            WeekViewUtil.isSameDay(startCal, endCal) -> "${shortDateFormat.format(startDate)} ${timeFormat.format(startDate)}..${
                timeFormat.format(endDate)
            }"
            else -> "${shortDateFormat.format(startDate)} ${timeFormat.format(startDate)}..${
                shortDateFormat.format(endDate)
            } ${timeFormat.format(endDate)}"
        }
    }


    override fun onEventClick(event: WeekViewEvent, eventRect: RectF) {
       // Toast.makeText(context, "Clicked " + event.name, Toast.LENGTH_SHORT).show()

    }
    override fun onEventLongPress(event: WeekViewEvent, eventRect: RectF) {
       // Toast.makeText(context, "Long pressed event: " + event.id, Toast.LENGTH_SHORT).show()

    }
    override fun onEmptyViewLongPress(time: Calendar) {
       // Toast.makeText(context, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onEmptyViewClicked(calendar: Calendar) {

    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onAddEventClicked(calendar: Calendar, endTime: Calendar) {

    }

    override fun onMonthChange(newYear: Int, newMonth: Int): MutableList<WeekViewEvent>? {
        /*if(counterNum<5){
           // Toast.makeText(context, "onMonthChange"+counterNum, Toast.LENGTH_SHORT).show()
            counterNum++
            return null
        }else{
            //Toast.makeText(context, "onMonthChangeEvent", Toast.LENGTH_SHORT).show()
            return eventsOne;
        }*/

            /*if (calenderBookingModel?.getResult() !=null && calenderBookingModel?.getResult()?.slot !=null){
                if (calenderBookingModel!!.getResult()!!.slot != null
                    && calenderBookingModel!!.getResult()!!.slot!!.isNotEmpty()) {
                    for (i in calenderBookingModel!!.getResult()!!.slot!!.indices) {
                        eventsOne.add(calenderBookingModel!!.getResult()!!.slot!!.get(i).toWeekViewEvent())
                    }
                }
                weekViewCal.notifyDataSetChanged()
            }*/
            val events = ArrayList<WeekViewEvent>()
        var startTime = Calendar.getInstance()
         startTime.set(Calendar.HOUR_OF_DAY, 4)
         startTime.set(Calendar.MINUTE, 20)
         startTime.set(Calendar.MONTH, newMonth - 1)
         startTime.set(Calendar.YEAR, newYear)
        var endTime = startTime.clone() as Calendar
         endTime.set(Calendar.HOUR_OF_DAY, 5)
         endTime.set(Calendar.MINUTE, 0)
         var event = WeekViewEvent("First", getEventTitle(startTime, endTime), startTime, endTime)
         event.color = ResourcesCompat.getColor(resources, com.appointmentbooking.R.color.event_color_03, null)
         events.add(event)
         return events;

    }
    override fun onDrop(view: View, date: Calendar) {
        //Toast.makeText(context, "View dropped to" + date.toString(), Toast.LENGTH_SHORT).show()
    }
    companion object {
        const val TYPE_DAY_VIEW = 1
        const val TYPE_THREE_DAY_VIEW = 2
        const val TYPE_WEEK_VIEW = 3
    }
    fun setDayViewType(dayViewType: Int) {
     //   setupDateTimeInterpreter(dayViewType == TYPE_WEEK_VIEW)
        when (dayViewType) {
            TYPE_DAY_VIEW -> {
                mWeekViewType = TYPE_DAY_VIEW
                weekViewCal.numberOfVisibleDays = 1
                // Lets change some dimensions to best fit the view.
                weekViewCal.columnGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
                weekViewCal.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
                weekViewCal.eventTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
            }
            TYPE_THREE_DAY_VIEW -> {
                mWeekViewType = TYPE_THREE_DAY_VIEW
                weekViewCal.numberOfVisibleDays = 3
                // Lets change some dimensions to best fit the view.
                weekViewCal.columnGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
                weekViewCal.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
                weekViewCal.eventTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
            }
            TYPE_WEEK_VIEW -> {
                mWeekViewType = TYPE_WEEK_VIEW
                weekViewCal.numberOfVisibleDays = 7
                // Lets change some dimensions to best fit the view.
                weekViewCal.columnGap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()
                weekViewCal.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f, resources.displayMetrics)
                weekViewCal.eventTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f, resources.displayMetrics)
            }
        }
    }
}