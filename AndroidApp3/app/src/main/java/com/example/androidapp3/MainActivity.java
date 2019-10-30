package com.example.androidapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int clickCount;
    private LinearLayout ll;
    private CheckBox checkRed;
    private CheckBox checkGreen;
    private CheckBox checkBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.mainLayout);

        checkRed = findViewById(R.id.checkRed);
        checkGreen = findViewById(R.id.checkGreen);
        checkBlue = findViewById(R.id.checkBlue);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("click-count", clickCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        clickCount = savedState.getInt("click-count");
    }

    public void onCheckColor(View view) {
        int color = Color.WHITE;
        int red = checkRed.isChecked() ? Color.RED : 0;
        int green = checkGreen.isChecked() ? Color.GREEN : 0;
        int blue = checkBlue.isChecked() ? Color.BLUE : 0;

        int[] startColors = new int[] {red, green, blue};
        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if(startColors[i] != 0)
                colors.add(startColors[i]);
        }
        boolean flag = false;
        for (int col : colors) {
            if(!flag) {
                color += col;
                flag = true;
            }

            color = mixTwoColors(color, col, 0.5F);
        }

        ll.setBackgroundColor(color);
    }

    private int mixTwoColors(int color1, int color2, float amount) {
        final byte ALPHA_CHANNEL = 24;
        final byte RED_CHANNEL = 16;
        final byte GREEN_CHANNEL = 8;
        final byte BLUE_CHANNEL = 0;

        final float inverseAmount = 1.0f - amount;

        int a = ((int) (((float) (color1 >> ALPHA_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> ALPHA_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int r = ((int) (((float) (color1 >> RED_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> RED_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int g = ((int) (((float) (color1 >> GREEN_CHANNEL & 0xff) * amount) +
                ((float) (color2 >> GREEN_CHANNEL & 0xff) * inverseAmount))) & 0xff;
        int b = ((int) (((float) (color1 & 0xff) * amount) +
                ((float) (color2 & 0xff) * inverseAmount))) & 0xff;

        return a << ALPHA_CHANNEL | r << RED_CHANNEL | g << GREEN_CHANNEL | b << BLUE_CHANNEL;
    }
}
