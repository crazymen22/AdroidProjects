package com.example.personlistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

public class NewTaskActivity extends AppCompatActivity {
    String imagePath;
    List<String> genderList;
    Person per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        genderList = new ArrayList<>();
        genderList = Arrays.asList("Жінка", "Чоловік");

        Spinner genderSpinner = findViewById(R.id.genderSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(adapter);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            per = arguments.getParcelable(Person.class.getSimpleName());

            EditText nameText = findViewById(R.id.nameText);
            EditText surNameText = findViewById(R.id.surNameText);
            DatePicker datePicker = findViewById(R.id.datePicker);

            String name = per.getName();
            String surName = per.getSurName();
            Calendar date = per.getDate();
            boolean gender = per.isGender();

            nameText.setText(name);
            surNameText.setText(surName);
            datePicker.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
            if(gender) {
                genderSpinner.setSelection(1);
            }
        }
    }

    public void addPersonButtonClick(View view) {
        EditText nameText = findViewById(R.id.nameText);
        String name = String.valueOf(nameText.getText());

        EditText surNameText = findViewById(R.id.surNameText);
        String surName = String.valueOf(surNameText.getText());

        DatePicker datePicker = findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        boolean gender = false;

        if (genderList.get(genderSpinner.getSelectedItemPosition()) == "Чоловік") {
            gender = true;
        }

        Person person = new Person(name, surName, new GregorianCalendar(year, month, day), gender);

        if(per != null)
            person.setId(per.getId());

        if(imagePath != null)
            person.setImageName(imagePath);

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        Bundle arguments = getIntent().getExtras();
        if(arguments != null)
            databaseAdapter.update(person);
        else {
            databaseAdapter.insert(person);
        }
        databaseAdapter.close();

        Intent intent = new Intent(this, MainActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }

    public void showImageSelectDialog(View view) {
        SelectImageDialog selectImageDialog = new SelectImageDialog();
        selectImageDialog.show(getSupportFragmentManager(), "image");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        ImageView imageView = findViewById(R.id.imagePerson);
        switch (requestCode) {
            case 0: {
                try {
                    Bitmap bitmap = (Bitmap) imageReturnedIntent.getExtras().get("data");

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String fileName = "IMAGE" + timeStamp + ".png";

                    FileOutputStream fOut = null;
                    try {
                        fOut = openFileOutput(fileName, MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    imagePath = fileName;
                    imageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 1: {
                Uri selectedImage = imageReturnedIntent.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                    String fileName = "IMAGE" + timeStamp + ".png";

                    FileOutputStream fOut = null;
                    try {
                        fOut = openFileOutput(fileName, MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    imagePath = fileName;
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void cancelButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }
}