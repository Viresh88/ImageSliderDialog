package com.example.imagesliderdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ImageSliderDialog extends Dialog {

    private Context context;
    private ArrayList<Integer> imageIds;
    private int delay;
    private boolean loop;
    private String title;
    private ImageView imgView;
    private TextView txtTitle, imgIndex;
    private ImageView closeIcon;
    private int currentIndex = 0;
    private Handler handler;
    private Runnable runnable;

    public ImageSliderDialog(@NonNull Context context, String title, ArrayList<Integer> imageIds, int delay, boolean loop) {
        super(context);
        this.context = context;
        this.title = title;
        this.imageIds = imageIds;
        this.delay = delay;
        this.loop = loop;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image_slider);

        txtTitle = findViewById(R.id.txtTitle);
        imgIndex = findViewById(R.id.imgIndex);
        closeIcon = findViewById(R.id.closeIcon);
        imgView = findViewById(R.id.imgView);

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dismiss();
            }
        });

       handler = new Handler();
       runnable = new Runnable() {
           @Override
           public void run() {
               if (currentIndex >= imageIds.size()) {
                   if (loop) {
                       currentIndex = 0;
                   } else {
                       dismiss();
                       return;
                   }
               }
               imgView.setImageResource(imageIds.get(currentIndex));
               imgIndex.setText("Image " + (currentIndex + 1) + " of " + imageIds.size());
               currentIndex++;
               handler.postDelayed(this, delay);
           }
       };
    }
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
