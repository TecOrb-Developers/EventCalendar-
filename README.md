# EventCalendar

**Android Week View** is an android library to display events calendars (week view or day view) within the app. It supports custom styling.


- Week view calendar
- Day view calendar
- Custom styling
- Horizontal and vertical scrolling
- Infinite horizontal scrolling
- Live preview of custom styling in xml preview window

# Screen Shot

![This is an image](https://s4.aconvert.com/convert/p3r68-cdx67/ac641-0n1o8.jpg)
![This is an image](https://s4.aconvert.com/convert/p3r68-cdx67/ah7na-stst3.jpg)
![This is an image](https://s4.aconvert.com/convert/p3r68-cdx67/am0b5-e8237.jpg)
![This is an image](https://s4.aconvert.com/convert/p3r68-cdx67/ai09a-idpig.jpg)




### How to Use

Step 1. Add it in your root build.gradle at the end of repositories:
```allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
```
Step 2. Add the dependency
dependencies {
	        implementation 'com.github.TecOrb-Developers:EventCalendar-:v1.0.0'
	}
```      


Step 3. Add WeekView in your xml layout for single or multi names.

```

<com.appointmentbooking.weekviewNames.WeekView
            android:id="@+id/weekView"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:dayBackgroundColor="#05000000"
            app:eventTextColor="@android:color/black"
            app:headerColumnBackground="#ffffffff"
            app:headerColumnPadding="12dp"
            app:headerColumnTextColor="@color/black"
            app:headerRowBackgroundColor="@color/white"
            app:headerRowPadding="6dp"
            android:layout_marginTop="7dp"
            app:hourHeight="60dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/recyclerView"
            app:noOfVisibleDays="3"
            app:nowLineColor="@color/colorPurpleDark"
            app:nowLineThickness="1dp"
            app:textSize="14sp"
            app:todayBackgroundColor="#05000000"
            app:todayHeaderTextColor="@color/black" />
	    
	   
```



Step 4. Add WeekView in your xml layout for calender.

```

<com.appointmentbooking.weekviewCal.WeekViewCal
        android:id="@+id/weekViewCal"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:autoLimitTime="false"
        android:layout_marginEnd="5dp"
        android:layout_marginStart="3dp"
        app:dayBackgroundColor="#05000000"
        app:headerColumnTextColor="@color/black"
        app:headerRowBackgroundColor="@color/white"
        app:maxTime="24"
        app:minOverlappingMinutes="5"
        app:minTime="4"
        app:newEventTimeResolutionInMinutes="15"
        app:noOfVisibleDays="3"
        app:timeColumnResolution="60"
        app:todayColumnBackgroundColor="#1848adff"
        app:todayHeaderTextColor="@color/accent"
        app:untitledEventText="@string/txt_in_minute"/>
	    
	   
```

Step 5. Write the following code in your class.

```
// Get a reference for the week view in the layout.
mWeekView = (WeekView) findViewById(R.id.weekView);

// Set an action when any event is clicked.
mWeekView.setOnEventClickListener(mEventClickListener);

// The week view has infinite scrolling horizontally. We have to provide the events of a
// month every time the month changes on the week view.
mWeekView.setMonthChangeListener(mMonthChangeListener);

// Set long press listener for events.
mWeekView.setEventLongPressListener(mEventLongPressListener);

```

