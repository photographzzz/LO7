package com.photograph.lo7.ui.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.photograph.lo7.R;
import com.photograph.lo7.controller.RoomController;
import com.photograph.lo7.databinding.ActivityCreateRoomBinding;
import com.rxjava.rxlife.RxLife;

public class CreateRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCreateRoomBinding createRoomBinding;
    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMinute;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinute;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createRoomBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_room);

        setSupportActionBar(createRoomBinding.createRoomToolBar.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        createRoomBinding.startDateBtn.setOnClickListener(this);
        createRoomBinding.startTimeBtn.setOnClickListener(this);
        createRoomBinding.endDateBtn.setOnClickListener(this);
        createRoomBinding.endTimeBtn.setOnClickListener(this);
        createRoomBinding.createRoomBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_date_btn:
                DatePickerDialog startDatePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    startYear = year;
                    startMonth = month + 1;
                    startDay = dayOfMonth;
                    startDate = startYear + "-" + startMonth + "-" + startDay;
                    createRoomBinding.startDateTxt.setText(startDate);
                }, 2020, 5 - 1, 8);
                startDatePicker.setTitle("开始日期");
                startDatePicker.show();
                break;
            case R.id.start_time_btn:
                TimePickerDialog startTimePicker = new TimePickerDialog(this, (view2, hourOfDay, minute) -> {
                    startHour = hourOfDay;
                    startMinute = minute;
                    startTime = startHour + ":" + startMinute;
                    createRoomBinding.startTimeTxt.setText(startTime);
                }, startHour, startMinute, true);
                startTimePicker.setTitle("开始时间");
                startTimePicker.show();
                break;
            case R.id.end_date_btn:
                DatePickerDialog endDatePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                    endYear = year;
                    endMonth = month + 1;
                    endDay = dayOfMonth;
                    endDate = endYear + "-" + endMonth + "-" + endDay;
                    createRoomBinding.endDateTxt.setText(endDate);
                }, 2020, 5 - 1, 8);
                endDatePicker.setTitle("结束日期");
                endDatePicker.show();
                break;
            case R.id.end_time_btn:
                TimePickerDialog endTimePicker = new TimePickerDialog(this, (view2, hourOfDay, minute) -> {
                    endHour = hourOfDay;
                    endMinute = minute;
                    endTime = endHour + ":" + endMinute;
                    createRoomBinding.endTimeTxt.setText(endTime);
                }, endHour, endMinute, true);
                endTimePicker.setTitle("结束时间");
                endTimePicker.show();
                break;
            case R.id.create_room_btn:
                String start = startDate + " " + startTime;
                String end = endDate + " " + endTime;
                RoomController.INSTANCE.create(start, end)
                        .as(RxLife.asOnMain(this))
                        .subscribe(room -> {
                            Intent intent = new Intent();
                            intent.putExtra("room", room);
                            setResult(200, intent);
                            finish();
                        });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
    