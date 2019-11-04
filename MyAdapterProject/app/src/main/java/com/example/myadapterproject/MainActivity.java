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

    private List<State> states = new ArrayList();

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                StateAdapter stateAdapter = (StateAdapter) countriesList.getAdapter();
                stateAdapter.remove(states.get(position));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        int id = item.getItemId();
        switch(id){
            case R.id.ukraine :
                stateAdapter.add(new State("Україна", "Київ", R.drawable.cat));
                return true;
            case R.id.poland:
                stateAdapter.add(new State("Польща", "Варшава", R.drawable.panda));
                return true;
            case R.id.canada:
                stateAdapter.add(new State("Канада", "Оттава", R.drawable.cat));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("states", (ArrayList<? extends Parcelable>) states);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        states = savedInstanceState.getParcelableArrayList("states");
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        countriesList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                StateAdapter stateAdapter = (StateAdapter) countriesList.getAdapter();
                stateAdapter.remove(states.get(position));
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){

        states.add(new State ("Бразилия", "Бразилиа", R.drawable.cat));
        states.add(new State ("Аргентина", "Буэнос-Айрес", R.drawable.panda));
        states.add(new State ("Колумбия", "Богота", R.drawable.cat));
        states.add(new State ("Уругвай", "Монтевидео", R.drawable.panda));
        states.add(new State ("Чили", "Сантьяго", R.drawable.cat));
    }
}
