package com.example.personlistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    public void addTaskButtonClick(View view) {
        EditText nameText = findViewById(R.id.nameText);
        String name = String.valueOf(nameText.getText());

        EditText surNameText = findViewById(R.id.surNameText);
        String surName = String.valueOf(surNameText.getText());



        DatePicker datePicker = findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        TextView genderText = findViewById(R.id.genderView);
        boolean gender;

        if(genderText.getText() == "жінка") {
            gender = false;
        }
        if(genderText.getText() == "чоловік") {
            gender = true;
        }
    }
}