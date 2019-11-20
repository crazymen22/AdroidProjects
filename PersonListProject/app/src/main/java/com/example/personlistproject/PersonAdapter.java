package com.example.personlistproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PersonAdapter extends ArrayAdapter<Person> {

    private LayoutInflater inflater;
    private int layout;
    private List<Person> persons;

    public PersonAdapter(Context context, int resource, List<Person> persons) {
        super(context, resource, persons);
        this.persons = persons;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView nameView = view.findViewById(R.id.name);
        TextView surNameView = view.findViewById(R.id.surname);
        TextView dateView = view.findViewById(R.id.date);

        Person person = persons.get(position);

        nameView.setText(person.getName());
        surNameView.setText(person.getSurName());

        Calendar date = person.getDate();
        String strDate = date.get(Calendar.DAY_OF_MONTH) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR);
        dateView.setText(strDate);

        if (person.getImageName() != null) {
            FileInputStream fin = null;
            try {
                Bitmap bitmap = null;
                fin = getContext().openFileInput(person.getImageName());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                bitmap = BitmapFactory.decodeStream(fin, null, options);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (person.isGender())
                imageView.setImageResource(R.drawable.boy);
            else
                imageView.setImageResource(R.drawable.girl);
        }

        return view;
    }
}