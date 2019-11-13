package com.example.personlistproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private LayoutInflater inflater;
    private int layout;
    private List<Task> tasks;

    public TaskAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.tasks = tasks;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView categoryView = view.findViewById(R.id.category);
        TextView nameView = view.findViewById(R.id.name);
        TextView dateView = view.findViewById(R.id.date);
        TextView timeView = view.findViewById(R.id.time);
        TextView priorityView = view.findViewById(R.id.priority);

        Task task = tasks.get(position);

        categoryView.setImageResource(task.getCategoryResource());
        nameView.setText(task.getName());
        String date = task.getDay() + "." + task.getMonth() + "." + task.getYear();
        dateView.setText(date);
        String time = task.getHour() + ":" + task.getMinute();
        timeView.setText(time);
        priorityView.setText(String.valueOf(task.getPriority()));


        return view;
    }
}