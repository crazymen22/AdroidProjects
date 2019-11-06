package com.example.myadapterproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks = new ArrayList();

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
        TaskAdapter stateAdapter = new TaskAdapter(this, R.layout.list_item, tasks);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);



        initEvents();
    }

    private void initEvents() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TaskAdapter stateAdapter = (TaskAdapter) countriesList.getAdapter();
                stateAdapter.remove(tasks.get(position));
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
        outState.putParcelableArrayList("tasks", (ArrayList<? extends Parcelable>) tasks);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tasks = savedInstanceState.getParcelableArrayList("tasks");
        TaskAdapter stateAdapter = new TaskAdapter(this, R.layout.list_item, tasks);
        countriesList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TaskAdapter stateAdapter = (TaskAdapter) countriesList.getAdapter();
                stateAdapter.remove(tasks.get(position));
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
        tasks.add(new Task ("Завдання1", 1, 1, 1, 1, 1, 1, R.drawable.cat));
        tasks.add(new Task ("Завдання2", 2, 2, 2, 2, 2, 2, R.drawable.cat));
        tasks.add(new Task ("Завдання3", 3, 3, 3, 3, 3, 3, R.drawable.cat));
        tasks.add(new Task ("Завдання4", 4, 4, 4, 4, 4, 4, R.drawable.cat));
        tasks.add(new Task ("Завдання5", 5, 5, 5, 5, 5, 5, R.drawable.cat));
    }
}
