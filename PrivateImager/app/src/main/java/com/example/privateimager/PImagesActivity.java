package com.example.privateimager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PImagesActivity extends AppCompatActivity {

    private List<PImage> pImages = new ArrayList();
    private DatabaseAdapter databaseAdapter;

    private View lastSelectedView = null;
    private int selectPosition;

    ListView pImagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pimages);

//        databaseAdapter = new DatabaseAdapter(this);
//
//        databaseAdapter.open();
//        pImages.addAll(databaseAdapter.getPImages());
//        databaseAdapter.close();
//
//        pImagesList = findViewById(R.id.countriesList);
//        PImageAdapter pImageAdapter = new FolderAdapter(this, R.layout.list_item, folders);
//        pImagesList.setAdapter(pImageAdapter);
//
//        initEvents();
    }

//    private void initEvents() {
//        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                FolderAdapter folderAdapter = (FolderAdapter) foldersList.getAdapter();
//
//                clearSelection();
//                lastSelectedView = v;
//                selectPosition = position;
//                v.setBackgroundResource(R.color.colorPrimeryLight);
////                stateAdapter.remove(folders.get(position));
//            }
//        };
//
//        foldersList.setOnItemClickListener(itemListener);
//    }
}
