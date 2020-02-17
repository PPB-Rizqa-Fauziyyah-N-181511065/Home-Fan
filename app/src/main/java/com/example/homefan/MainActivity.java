package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Deklarasi Variabel
    ToggleButton toggleButton;
    SeekBar seekBar;
    ImageView iconFan;
    Switch switchButton;
    ObjectAnimator rotateAnimator;
    final int SPEED[] = {0, 5000, 3000, 1000};
    final GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        iconFan = (ImageView) findViewById(R.id.iconFan);
        switchButton = (Switch) findViewById(R.id.lights);
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        rotateAnimator=ObjectAnimator.ofFloat(iconFan, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        int index;

        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean on =((ToggleButton)v).isChecked();
                if(on){
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                }
                else{
                    rotateAnimator.end();
                }
            }
        });

        switchButton.setOnClickListener(new CompoundButton.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean on =((Switch)v).isChecked();
                if(on){
                    gd.setColors(new int[]{Color.YELLOW, Color.TRANSPARENT});
                    iconFan.setBackground(gd);
                }
                else{
                    iconFan.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rotateAnimator.setDuration(SPEED[progress]);
                rotateAnimator.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
