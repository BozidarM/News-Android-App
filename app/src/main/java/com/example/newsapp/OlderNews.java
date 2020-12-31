package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.LinkedList;

public class OlderNews extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Button btnPicker, btnSubmit;
    TextView twDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_news);

        twDate = findViewById(R.id.twDate);


        initComponents();
    }

    private void initComponents() {
        btnPicker = findViewById(R.id.btnPicker);
        btnPicker.setOnClickListener(this);
        btnSubmit = findViewById(R.id.btnSubimt);
        btnSubmit.setOnClickListener(this);
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String monthString = ""+month;
        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        }
        String dayString = ""+dayOfMonth;
        if (dayString.length() == 1){
            dayString = "0" + dayString;
        }

        String date = "Choosen date: " + year + "-" + monthString + "-" + dayString;
        twDate.setText(date);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPicker:
                showDatePickerDialog();
                break;

            case R.id.btnSubimt:
                Intent intent = new Intent(this, DatePicked.class);
                Bundle extras = new Bundle();

                String date = twDate.getText().toString();
                String dateForUrl = date.substring(date.length() - 10);

                extras.putString("date", dateForUrl);

                intent.putExtras(extras);
                startActivity(intent);
                break;
        }
    }
}