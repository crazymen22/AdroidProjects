package com.example.privateimager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_READ_WRITE_EXTERNAL_STORAGE = 1;

    private List<Folder> folders = new ArrayList();
    private DatabaseAdapter databaseAdapter;

    private View lastSelectedView = null;
    private int selectPosition;

    ListView foldersList;

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

        foldersList = findViewById(R.id.countriesList);
        FolderAdapter folderAdapter = new FolderAdapter(this, R.layout.list_item, folders);
        foldersList.setAdapter(folderAdapter);

        initEvents();
    }

    private void initEvents() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                FolderAdapter folderAdapter = (FolderAdapter) foldersList.getAdapter();

                clearSelection();
                lastSelectedView = v;
                selectPosition = position;
                v.setBackgroundResource(R.color.colorPrimeryLight);
//                stateAdapter.remove(folders.get(position));
            }
        };

        foldersList.setOnItemClickListener(itemListener);
    }

    @SuppressLint("ResourceAsColor")
    private void clearSelection() {
        if (lastSelectedView != null)
            lastSelectedView.setBackgroundColor(android.R.color.transparent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("folders", (ArrayList<? extends Parcelable>) folders);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        folders = savedInstanceState.getParcelableArrayList("folders");
        FolderAdapter folderAdapter = new FolderAdapter(this, R.layout.list_item, folders);
        foldersList.setAdapter(folderAdapter);

        initEvents();
    }

    private void setInitialData() {
//        folders.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 14), true));
//        folders.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 13), false));
//        folders.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 11, 10), true));
//        folders.add(new Person("Name1", "SurName1", new GregorianCalendar(2019, 9, 4), false));
//        folders.add(new Person("Name1", "SurName1", new GregorianCalendar(2016, 3, 1), true));
        databaseAdapter.open();
        folders.addAll(databaseAdapter.getFolders());
        databaseAdapter.close();
    }

    public void addButtonClick(View view) {
        Intent intent = new Intent(this, NewFolderActivity.class);

        startActivity(intent);
    }

    public void editButtonClick(View view) {
        Intent intent = new Intent(this, NewFolderActivity.class);

        Folder f = folders.get(selectPosition);

        intent.putExtra(Folder.class.getSimpleName(), f);

        startActivity(intent);
    }

    public void deleteButtonClick(View view) {
        FolderAdapter folderAdapter = (FolderAdapter) foldersList.getAdapter();

        Folder f = folders.get(selectPosition);

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        if (databaseAdapter.getPImageCount() != 0) {
            for (PImage pImage : databaseAdapter.getPImages()) {
                if (pImage.getIdFolder() == f.getId())
                    databaseAdapter.deletePImage(pImage.getId());
            }
        }
        databaseAdapter.deleteFolder(f.getId());
        databaseAdapter.close();

        folderAdapter.remove(f);
    }

    public void openButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);

        final Folder f = folders.get(selectPosition);

        builder.setTitle("Введіть пароль");
        builder.setCancelable(false);
        builder.setPositiveButton("Підтвердити",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String inputPassword = String.valueOf(input.getText());
                        String password = f.getPassword();
                        if (inputPassword.equals(password)) {
                            Intent intent = new Intent(getApplicationContext(), PImagesActivity.class);

                            Folder f = folders.get(selectPosition);

                            intent.putExtra(Folder.class.getSimpleName(), f);

                            startActivity(intent);
                        }
                    }
                });
        builder.setNegativeButton("Відмінити",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
