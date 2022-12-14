package com.bookappointmentcalender.model;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;

import com.appointmentbooking.weekviewNames.WeekViewEvent;
import com.bookappointmentcalender.helper.DateFormat;
import com.appointmentbooking.weekviewNames.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MultipleSpecialistEvent {

    /**
     * code : 200
     * message : Succes
     * result : [{"date":"2021-07-19","spcName":"ajay","specId":"6017cc296a7bee001509a30d","slot":[{"slot":"12:20","bookingId":"60f1650f495e1623da1641d2","date":"2021-07-19","serviceTime":"40","userName":"deepika","bookingType":"offline","pay":246,"category":{"categoryName":"edghv","color":"#b7d0b3"}}]}]
     * blockTime : [{"date":"2021-07-19","spcName":"ajay","specId":"6017cc296a7bee001509a30d","breakTime":[{"usertype":"spec","startTime":"09:00","endTime":"10:00","_id":"60f1349cad1f4f20035745cf","breakDate":"2021-07-19T00:00:00.000Z"}]}]
     */
    private int code;
    private String message;
    /**
     * date : 2021-07-19
     * spcName : ajay
     * specId : 6017cc296a7bee001509a30d
     * slot : [{"slot":"12:20","bookingId":"60f1650f495e1623da1641d2","date":"2021-07-19","serviceTime":"40","userName":"deepika","bookingType":"offline","pay":246,"category":{"categoryName":"edghv","color":"#b7d0b3"}}]
     */

    private List<ResultBean> result;
    /**
     * date : 2021-07-19
     * spcName : ajay
     * specId : 6017cc296a7bee001509a30d
     * breakTime : [{"usertype":"spec","startTime":"09:00","endTime":"10:00","_id":"60f1349cad1f4f20035745cf","breakDate":"2021-07-19T00:00:00.000Z"}]
     */

    private List<BlockTimeBean> blockTime;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<BlockTimeBean> getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(List<BlockTimeBean> blockTime) {
        this.blockTime = blockTime;
    }

    public static class ResultBean {
        private String date;
        private String spcName;
        private String specId;
        /**
         * slot : 12:20
         * bookingId : 60f1650f495e1623da1641d2
         * date : 2021-07-19
         * serviceTime : 40
         * userName : deepika
         * bookingType : offline
         * pay : 246
         * category : {"categoryName":"edghv","color":"#b7d0b3"}
         */

        private List<SlotBean> slot;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSpcName() {
            return spcName;
        }

        public void setSpcName(String spcName) {
            this.spcName = spcName;
        }

        public String getSpecId() {
            return specId;
        }

        public void setSpecId(String specId) {
            this.specId = specId;
        }

        public List<SlotBean> getSlot() {
            return slot;
        }

        public void setSlot(List<SlotBean> slot) {
            this.slot = slot;
        }

        public static class SlotBean {
            private String slot;
            private String bookingId;
            private String date;
            private String serviceTime;
            private String userName;
            private String bookingType;
            private int pay;
            /**
             * categoryName : edghv
             * color : #b7d0b3
             */

            private CategoryBean category;

            public String getSlot() {
                return slot;
            }

            public void setSlot(String slot) {
                this.slot = slot;
            }

            public String getBookingId() {
                return bookingId;
            }

            public void setBookingId(String bookingId) {
                this.bookingId = bookingId;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getServiceTime() {
                return serviceTime;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getBookingType() {
                return bookingType;
            }

            public void setBookingType(String bookingType) {
                this.bookingType = bookingType;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public CategoryBean getCategory() {
                return category;
            }

            public void setCategory(CategoryBean category) {
                this.category = category;
            }

            public static class CategoryBean {
                private String categoryName;
                private String color;

                public String getCategoryName() {
                    return categoryName;
                }

                public void setCategoryName(String categoryName) {
                    this.categoryName = categoryName;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }
            }

            @SuppressLint("SimpleDateFormat")
            public WeekViewEvent toWeekViewEvent() {
                // Parse time.
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                Date start = new Date();
                Date end = new Date();
                String startTimeNew ="";
                String newTime ="";
                try {
                    start = sdf.parse(getSlot());
                    Log.d("dsdfsdfd", "toWeekViewEvent: "+start);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    int hours = Integer.parseInt(getServiceTime()) / 60; //since both are ints, you get an int
                    int minutes = Integer.parseInt(getServiceTime()) % 60;
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(start);
                    startTimeNew = sdf.format(cal.getTime());
                    cal.add(Calendar.MINUTE, Integer.parseInt(getServiceTime()));
                    newTime = sdf.format(cal.getTime());
                    end = sdf.parse(newTime);
                    Log.d("dsdfsdfd", "toWeekViewEventEnd: "+newTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Initialize start and end time.
                Calendar now = Calendar.getInstance();
                Calendar startTime = (Calendar) now.clone();
                startTime.setTimeInMillis(start.getTime());
                startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
                startTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
                startTime.set(Calendar.DAY_OF_MONTH, DateFormat.getDateOnlySpecialist(getDate()));
                Calendar endTime = (Calendar) startTime.clone();
                endTime.setTimeInMillis(end.getTime());
                endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
                endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
                endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

                // Create an week view event.
                WeekViewEvent weekViewEvent = new WeekViewEvent();
                weekViewEvent.setIdentifier(getBookingId());
                // weekViewEvent.setName(getUserName());
                weekViewEvent.setName(startTimeNew+"   -   "+newTime+"\n"+getUserName()+"\n"+getCategory().getCategoryName()+"                                            "+getPay()+"kr");

                weekViewEvent.setStartTime(startTime);
                weekViewEvent.setEndTime(endTime);
                weekViewEvent.setColor(Color.parseColor(getCategory().getColor()));

                return weekViewEvent;
            }
        }
    }

    public static class BlockTimeBean {
        private String date;
        private String spcName;
        private String specId;
        /**
         * usertype : spec
         * startTime : 09:00
         * endTime : 10:00
         * _id : 60f1349cad1f4f20035745cf
         * breakDate : 2021-07-19T00:00:00.000Z
         */

        private List<BreakTimeBean> breakTime;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSpcName() {
            return spcName;
        }

        public void setSpcName(String spcName) {
            this.spcName = spcName;
        }

        public String getSpecId() {
            return specId;
        }

        public void setSpecId(String specId) {
            this.specId = specId;
        }

        public List<BreakTimeBean> getBreakTime() {
            return breakTime;
        }

        public void setBreakTime(List<BreakTimeBean> breakTime) {
            this.breakTime = breakTime;
        }

        public static class BreakTimeBean {
            private String usertype;
            private String startTime;
            private String endTime;
            private String _id;
            private String breakDate;

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getBreakDate() {
                return breakDate;
            }

            public void setBreakDate(String breakDate) {
                this.breakDate = breakDate;
            }


            @SuppressLint("SimpleDateFormat")
            public WeekViewEvent toWeekViewBlockEvent() {
                // Parse time.
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                Date start = new Date();
                Date end = new Date();
                String startTimeNew ="";
                String newTime ="";
                try {
                    start = sdf.parse(getStartTime());
                    Log.d("dsdfsdfd", "toWeekViewEvent: "+start);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(start);
                    startTimeNew = sdf.format(cal.getTime());
                    end = sdf.parse(getEndTime());
                    cal.setTime(end);
                    newTime = sdf.format(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Initialize start and end time.
                Calendar now = Calendar.getInstance();
                Calendar startTime = (Calendar) now.clone();
                startTime.setTimeInMillis(start.getTime());
                startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
                startTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
                startTime.set(Calendar.DAY_OF_MONTH, DateFormat.getDateOnlySpecialist(getBreakDate()));
                Calendar endTime = (Calendar) startTime.clone();
                endTime.setTimeInMillis(end.getTime());
                endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
                endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
                endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
                // Create an week view event.
                WeekViewEvent weekViewEvent = new WeekViewEvent();
                weekViewEvent.setIdentifier(getUsertype());
                // weekViewEvent.setName(getUserName());
                weekViewEvent.setName(startTimeNew+"   -   "+newTime+"\n"+"Block Time");
                weekViewEvent.setStartTime(startTime);
                weekViewEvent.setEndTime(endTime);
                weekViewEvent.setColor(Color.parseColor("#E95A5B"));
                return weekViewEvent;
            }
        }
    }
}
