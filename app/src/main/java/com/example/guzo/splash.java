package com.example.guzo;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
 private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView) findViewById(R.id.guzo);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        final Intent intent = new Intent(this,MainActivity.class);
        imageView.startAnimation(myanim);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                     startActivity(intent);
                     finish();
                }
            }
        };
        timer.start();
    }
}
