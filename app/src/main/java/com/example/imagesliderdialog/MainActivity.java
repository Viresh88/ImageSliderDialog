package com.example.imagesliderdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button show_dialog_button;
    ArrayList<Integer> imageIds = new ArrayList<>();

    int delay;
   boolean loop;
   String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_dialog_button = findViewById(R.id.show_dialog_button);

         imageIds.add(R.drawable.one);
         imageIds.add(R.drawable.two);
        imageIds.add(R.drawable.three);
        imageIds.add(R.drawable.four);
        imageIds.add(R.drawable.five);

        delay = 3000;
        loop =true;
        title ="Image Slider";


        show_dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSliderDialog dialog = new ImageSliderDialog(MainActivity.this, title, imageIds, delay, loop);
                dialog.show();
            }
        });


    }
}