package com.example.personlistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class NewTaskActivity extends AppCompatActivity {
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
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

        TextView genderText = findViewById(R.id.genderView);
        boolean gender = false;

        if (genderText.getText() == "чоловік") {
            gender = true;
        }

        Person person = new Person(name, surName, new GregorianCalendar(year, month, day), gender);

        if(imagePath != null)
            person.setImageName(imagePath);

        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        databaseAdapter.insert(person);
        databaseAdapter.close();

        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

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

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
    }
}