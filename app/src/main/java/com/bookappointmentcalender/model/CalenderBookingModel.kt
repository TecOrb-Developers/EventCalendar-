package com.bookappointmentcalender.model

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.bookappointmentcalender.helper.DateFormat
import com.appointmentbooking.weekviewCal.WeekViewEvent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalenderBookingModel {
    /**
     * code : 200
     * message : Succes
     * result : {"date":"2021-07-10","spcName":"Prashant Sharm","specId":"60068b7932f15f470d25f804","slot":[{"slot":"15:30","bookingId":"60e93b45936a56272ec932a9","date":"2021-07-10T00:00:00+00:00","serviceTime":"30","userName":"ajay kumar","pay":157,"category":{"categoryName":"punit testing","color":"#ffdade"}}]}
     */
    private var code = 0
    private var message: String? = null

    /**
     * date : 2021-07-10
     * spcName : Prashant Sharm
     * specId : 60068b7932f15f470d25f804
     * slot : [{"slot":"15:30","bookingId":"60e93b45936a56272ec932a9","date":"2021-07-10T00:00:00+00:00","serviceTime":"30","userName":"ajay kumar","pay":157,"category":{"categoryName":"punit testing","color":"#ffdade"}}]
     */
    private var result: ResultBean? = null
    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getResult(): ResultBean? {
        return result
    }

    fun setResult(result: ResultBean?) {
        this.result = result
    }
    class ResultBean {
        var date: String? = null
        var spcName: String? = null
        var specId: String? = null
        /**
         * slot : 15:30
         * bookingId : 60e93b45936a56272ec932a9
         * date : 2021-07-10T00:00:00+00:00
         * serviceTime : 30
         * userName : ajay kumar
         * pay : 157
         * category : {"categoryName":"punit testing","color":"#ffdade"}
         */
        var slot: List<SlotBean>? = null
        var breakTime: List<BreakTimeBean>? = null

        class SlotBean {
            @Expose
            @SerializedName("slot")
            var slot: String? = null
            var bookingId: String? = null
            var date: String? = null
            var serviceTime: String? = null
            var userName: String? = null
            var pay = 0

            /**
             * categoryName : punit testing
             * color : #ffdade
             */
            var category: CategoryBean? = null

            class CategoryBean {
                var categoryName: String? = null
                var color: String? = null
            }
            @SuppressLint("SimpleDateFormat")
            fun toWeekViewEvent(): WeekViewEvent {
                // Parse time.
                Log.d("agsgsdfg", "onTaskSuccess: ")
                val sdf = SimpleDateFormat("HH:mm")
                var start = Date()
                var end = Date()
                var startTimeNew = ""
                var newTime = ""
                try {
                    start = sdf.parse(slot)
                }catch (e: ParseException) {
                    e.printStackTrace()
                }
                try {
                    val cal = Calendar.getInstance()
                    cal.time = start
                    startTimeNew =sdf.format(cal.time)
                    cal.add(Calendar.MINUTE, serviceTime!!.toInt())
                     newTime = sdf.format(cal.time)
                    end = sdf.parse(newTime)
                    Log.d("aafsgsgfg", "toWeekViewEvent: "+start +"\nendTime---->"+end)
                }catch (e: ParseException) {
                    e.printStackTrace()
                }
                // Initialize start and end time kotlin code
                val now = Calendar.getInstance()
                val startTime = Calendar.getInstance()
                startTime.timeInMillis = start.time
                with(startTime) {
                    set(Calendar.YEAR, now.get(Calendar.YEAR))
                    set(Calendar.MONTH, now.get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, DateFormat.getDateOnly(date))
                }
                val endTime = startTime.clone() as Calendar
                endTime.timeInMillis = end.time
                with(endTime) {
                    set(Calendar.YEAR, startTime.get(Calendar.YEAR))
                    set(Calendar.MONTH, startTime.get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH))
                }
                var finaUserEvent = startTimeNew+"   -   "+newTime+"\n"+userName+"\n"+category?.categoryName+"                                            "+pay+"kr"
                //    weekViewEvent.setName(startTimeNew+"   -   "+newTime+"\n\n"+getUserName()+"\n\n"+getCategory().getCategoryName()+"                                            "+getPay()+"kr");
                val weekViewEvent = WeekViewEvent(bookingId, finaUserEvent, startTime, endTime)
                weekViewEvent.color = Color.parseColor(category!!.color)
                return weekViewEvent
            }
        }

        class BreakTimeBean {
            var usertype: String? = null
            var startTime: String? = null
            var endTime: String? = null
            private var _id: String? = null
            var breakDate: String? = null

            fun get_id(): String? {
                return _id
            }

            fun set_id(_id: String?) {
                this._id = _id
            }
            @SuppressLint("SimpleDateFormat")
            fun toBlockWeekViewEvent(): WeekViewEvent {
                // Parse time.
                Log.d("agsgsdfg", "onTaskSuccess: ")
                val sdf = SimpleDateFormat("HH:mm")
                var start = Date()
                var end = Date()
                var startTimeNew = ""
                var newTime = ""
                try {
                    start = sdf.parse(startTime)
                }catch (e: ParseException) {
                    e.printStackTrace()
                }
                try {
                    val cal = Calendar.getInstance()
                    cal.time = start
                    startTimeNew =sdf.format(cal.time)
                    //cal.add(Calendar.MINUTE, serviceTime!!.toInt())
                    end = sdf.parse(endTime)
                    cal.time = end
                    newTime = sdf.format(end)
                    Log.d("aafsgsgfg", "toWeekViewEvent: "+start +"\nendTime---->"+end)
                }catch (e: ParseException) {
                    e.printStackTrace()
                }
                // Initialize start and end time kotlin code
                val now = Calendar.getInstance()
                val startTime = Calendar.getInstance()
                startTime.timeInMillis = start.time
                with(startTime) {
                    set(Calendar.YEAR, now.get(Calendar.YEAR))
                    set(Calendar.MONTH, now.get(Calendar.MONTH))
                    var nowMonth  =now.get(Calendar.MONTH)
                    nowMonth = (nowMonth+1)
                    var  bookBreakDate =DateFormat.getMonthOnly(breakDate)
                    Log.d("asfsfsd", "toBlockWeekViewEvent: "+(nowMonth)+"\n"+now.get(Calendar.MONTH)+"\n"+breakDate+"\n"+bookBreakDate)
                    if (nowMonth == bookBreakDate){
                        set(Calendar.DAY_OF_MONTH, DateFormat.getDateOnly(breakDate))
                    }
                }
                val endTime = startTime.clone() as Calendar
                endTime.timeInMillis = end.time
                with(endTime) {
                    set(Calendar.YEAR, startTime.get(Calendar.YEAR))
                    set(Calendar.MONTH, startTime.get(Calendar.MONTH))
                    set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH))
                }
                var finaUserEvent = startTimeNew+"   -   "+newTime+"\n"+"Block Time"
                //    weekViewEvent.setName(startTimeNew+"   -   "+newTime+"\n\n"+getUserName()+"\n\n"+getCategory().getCategoryName()+"                                            "+getPay()+"kr");
                val weekViewEvent = WeekViewEvent(usertype, finaUserEvent, startTime, endTime)
                weekViewEvent.color = Color.parseColor("#E95A5B")
                return weekViewEvent
            }
        }
    }
}
