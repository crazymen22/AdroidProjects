package com.example.myadapterproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Objects;

public class FragmentTask extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment, container, false);

        final TextView dateView = view.findViewById(R.id.dateInput);
        TextView timeView = view.findViewById(R.id.timeInput);

        final DatePickerDialog[] picker = new DatePickerDialog[1];

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker[0] = new DatePickerDialog(Objects.requireNonNull(FragmentTask.this.getContext()),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String dateStr = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                dateView.setText(dateStr);
                            }
                        }, year, month, day);
                picker[0].show();
            }
        });

        return view;
    }
}
