package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class OlderNews extends AppCompat implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Button btnPicker, btnSubmit;
    EditText etKeyword;
    TextView twDate;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_news);
        setTitle(R.string.older_news);

        initComponents();
    }

    private void initComponents() {
        btnPicker = findViewById(R.id.btnPicker);
        btnPicker.setOnClickListener(this);
        btnSubmit = findViewById(R.id.btnSubimt);
        btnSubmit.setOnClickListener(this);
        twDate = findViewById(R.id.twDate);
        etKeyword = findViewById(R.id.etKeyword);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.bottomNavigationFunction(bottomNavigationView);

        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setChecked(true);

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
        month++;
        String monthString = ""+month;
        if (monthString.length() == 1) {
            monthString = "0" + monthString;
        }
        String dayString = ""+dayOfMonth;
        if (dayString.length() == 1){
            dayString = "0" + dayString;
        }

        String date = year + "-" + monthString + "-" + dayString;
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

                if(!etKeyword.getText().toString().equals("") && !twDate.getText().toString().equals(""))
                {
                    String date = twDate.getText().toString();
                    String dateForUrl = date.substring(date.length() - 10);

                    extras.putString("keyword", etKeyword.getText().toString());
                    extras.putString("date", dateForUrl);

                    intent.putExtras(extras);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, R.string.empty_keyword_date, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}