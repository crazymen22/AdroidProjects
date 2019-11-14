package com.example.personlistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> persons = new ArrayList();

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        countriesList = findViewById(R.id.countriesList);
        // создаем адаптер
        PersonAdapter stateAdapter = new PersonAdapter(this, R.layout.list_item, persons);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);



        initEvents();
    }

    private void initEvents() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PersonAdapter stateAdapter = (PersonAdapter) countriesList.getAdapter();
                stateAdapter.remove(persons.get(position));
            }
        };

        countriesList.setOnItemClickListener(itemListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("persons", (ArrayList<? extends Parcelable>) persons);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        persons = savedInstanceState.getParcelableArrayList("persons");
        PersonAdapter stateAdapter = new PersonAdapter(this, R.layout.list_item, persons);
        countriesList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PersonAdapter stateAdapter = (PersonAdapter) countriesList.getAdapter();
                stateAdapter.remove(persons.get(position));
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
        persons.add(new Person ("Завдання1", 1, 1, 1, 1, 1, 1, R.drawable.cat));
    }

    public void addButtonClick(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);

        startActivity(intent);
    }

    public void editButtonClick(View view) {
    }

    public void deleteButtonClick(View view) {
    }
}
