package com.example.personlistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class NewTaskActivity extends AppCompatActivity {
    String[] categoryVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        categoryVals = new String[]{"Панда", "Кіт"};

        NumberPicker categoryPicker = findViewById(R.id.categoryPicker);

        categoryPicker.setDisplayedValues(categoryVals);
    }

    public void addTaskButtonClick(View view) {
        EditText nameText = findViewById(R.id.nameText);
        String name = String.valueOf(nameText.getText());

        DatePicker datePicker = findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        TimePicker timePicker = findViewById(R.id.timePicker);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        EditText priorityText = findViewById(R.id.priorityText);
        int priority = Integer.valueOf(String.valueOf(priorityText.getText()));

        NumberPicker categoryPicker = findViewById(R.id.categoryPicker);
        int category;
        switch (categoryPicker.getValue()) {
            case 1: {
                category = R.drawable.panda;
                break;
            }
            case 2: {
                category = R.drawable.cat;
                break;
            }
        }
    }
}