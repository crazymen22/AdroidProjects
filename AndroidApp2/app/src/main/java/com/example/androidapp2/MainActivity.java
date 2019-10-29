package com.example.androidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;
    private TextView tw1;
    private TextView tw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = findViewById(R.id.root);
        tw1 = findViewById(R.id.text1);
        tw2 = findViewById(R.id.text2);

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                    int action = e.getAction();
                    float x = e.getX();
                    float y = e.getY();
                    String s = "";
                    switch (action) {
                        case MotionEvent.ACTION_DOWN: {
                            s += "Натиснути";
                            break;
                        }
                        case MotionEvent.ACTION_MOVE: {
                            s += "Перемістити";
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            s += "Відпустити";
                            break;
                        }
                    }
                    s += "x =" + x + " y= " + y;
                    clickCount++;
                    tw1.setText(String.valueOf(clickCount));
                    tw2.setText(s);
                    return false;
            }
        });
    }
}
