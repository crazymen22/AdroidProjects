package com.example.privateimager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.List;

public class FolderAdapter extends ArrayAdapter<Folder> {

    private LayoutInflater inflater;
    private int layout;
    private List<Folder> folders;

    public FolderAdapter(Context context, int resource, List<Folder> folders) {
        super(context, resource, folders);
        this.folders = folders;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView nameView = view.findViewById(R.id.name);

        Folder folder = folders.get(position);

        nameView.setText(folder.getName());
        imageView.setImageResource(R.drawable.folder);

        return view;
    }
}