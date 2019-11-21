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
import java.util.List;

public class PImageAdapter extends ArrayAdapter<PImage> {
    private LayoutInflater inflater;
    private int layout;
    private List<PImage> pImages;

    public PImageAdapter(Context context, int resource, List<PImage> pImages) {
        super(context, resource, pImages);
        this.pImages = pImages;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView nameView = view.findViewById(R.id.name);

        PImage pImage = pImages.get(position);

        nameView.setText(pImage.getName());

        if (pImage.getPath() != null) {
            FileInputStream fin = null;
            try {
                Bitmap bitmap = null;
                fin = getContext().openFileInput(pImage.getPath());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                bitmap = BitmapFactory.decodeStream(fin, null, options);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return view;
    }
}
