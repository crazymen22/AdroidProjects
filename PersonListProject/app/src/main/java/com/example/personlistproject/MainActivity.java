package com.example.personlistproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_READ_WRITE_EXTERNAL_STORAGE = 1;

    private List<Person> persons = new ArrayList();
    private DatabaseAdapter databaseAdapter;

    ListView personsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_READ_WRITE_EXTERNAL_STORAGE);
            }
        }

        databaseAdapter = new DatabaseAdapter(this);

        setInitialData();
        personsList = findViewById(R.id.countriesList);
        PersonAdapter stateAdapter = new PersonAdapter(this, R.layout.list_item, persons);
        personsList.setAdapter(stateAdapter);

        initEvents();
    }

    private void initEvents() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PersonAdapter stateAdapter = (PersonAdapter) personsList.getAdapter();
                stateAdapter.remove(persons.get(position));
            }
        };

        personsList.setOnItemClickListener(itemListener);
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
        personsList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PersonAdapter stateAdapter = (PersonAdapter) personsList.getAdapter();
                stateAdapter.remove(persons.get(position));
            }
        };
        personsList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
//        persons.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 14), true));
//        persons.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 13), false));
//        persons.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 10), true));
//        persons.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 9, 4), false));
//        persons.add(new Person("Name1", "SurName1", new GregorianCalendar(2016, 3, 1), true));
        databaseAdapter.open();
        persons.addAll(databaseAdapter.getPersons());
        databaseAdapter.close();
    }

    public void addButtonClick(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);

        startActivity(intent);
    }

    public void editButtonClick(View view) {
    }

    public void deleteButtonClick(View view) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted: " + PERMISSION_READ_WRITE_EXTERNAL_STORAGE, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission NOT granted: " + PERMISSION_READ_WRITE_EXTERNAL_STORAGE, Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }
}
