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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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


        if(!person.getImageName().equals("")) {
            Bitmap bitmap = null;
            FileInputStream fIn = null;
            try {
                fIn = getContext().openFileInput(person.getImageName());
                bitmap = BitmapFactory.decodeStream(fIn);
                imageView.setImageBitmap(bitmap);
                fIn.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {

        }

        return view;
    }
}