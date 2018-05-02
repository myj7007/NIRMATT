package com.example.dell.nirmatt;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    public String date;
    private android.support.v7.widget.Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        //datePicker.setMaxDate(System.currentTimeMillis()-1000);

        mToolbar=findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Select Date");

        dateView = (TextView) findViewById(R.id.text_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        try {
            showDate(year, month + 1, day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    public void setDate(View view) {
        showDialog(999);
        /*Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            //datePicker.setMaxDate(System.currentTimeMillis()-1000);
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }




    private void showDate(int year, int month, int day) throws ParseException {

        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        date=dateView.getText().toString();

        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1=format1.parse(date);
        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(dt1);



    }

    private void btnclick(int year, int month, int day) throws ParseException {



        String date;
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        date=dateView.getText().toString();



        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1=format1.parse(date);
        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(dt1);

        Intent i = new Intent(DatePickerActivity.this,SlotActivity.class);
        i.putExtra("day", finalDay);
        startActivity(i);





    }

    private OnDateSetListener myDateListener = new
            OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                    try {
                        btnclick(year, month + 1, dayOfMonth);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }



                }};




}

