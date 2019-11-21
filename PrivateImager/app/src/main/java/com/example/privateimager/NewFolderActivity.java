package com.example.privateimager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class NewFolderActivity extends AppCompatActivity {
    Folder fol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_folder);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            fol = arguments.getParcelable(Folder.class.getSimpleName());

            EditText nameText = findViewById(R.id.nameText);
            EditText passwordText = findViewById(R.id.passwordText);

            String name = fol.getName();
            String password = fol.getPassword();

            nameText.setText(name);
            passwordText.setText(password);
        }
    }

    public void addPersonButtonClick(View view) {
        EditText nameText = findViewById(R.id.nameText);
        String name = String.valueOf(nameText.getText());

        EditText passwordText = findViewById(R.id.passwordText);
        String password = String.valueOf(passwordText.getText());

        Folder folder = new Folder(name, password);

        if(fol != null)
            folder.setId(fol.getId());

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        Bundle arguments = getIntent().getExtras();
        if(arguments != null)
            databaseAdapter.updateFolder(folder);
        else {
            databaseAdapter.insertFolder(folder);
        }
        databaseAdapter.close();

        Intent intent = new Intent(this, MainActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }

    public void cancelButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }
}