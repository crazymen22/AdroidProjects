package com.example.currencyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> siteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siteList = new ArrayList<>();
        siteList = Arrays.asList("https://minfin.com.ua/ua/currency/", "https://finance.i.ua/", "https://creditdnepr.com.ua/currency");

        Spinner siteSpiner = findViewById(R.id.siteList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, siteList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        siteSpiner.setAdapter(adapter);
    }
}
