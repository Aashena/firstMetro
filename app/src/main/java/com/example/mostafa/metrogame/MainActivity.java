package com.example.mostafa.metrogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.mostafa.metrogame.objects.Ball;
import com.example.mostafa.metrogame.objects.Basket;
import com.example.mostafa.metrogame.objects.Divider;
import com.example.mostafa.metrogame.views.MyView;

public class MainActivity extends AppCompatActivity {

    int height=100,width=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        getScreenSize();
        Divider divider= new Divider(width,height);

        Ball ball=new Ball(width,height);

        Basket basket1 = new Basket(ball.getSize(), true , width , height);
        Basket basket2 = new Basket (ball.getSize(),false, width, height);
        MyView myView= new MyView(this,ball,divider, basket1 , basket2 ,width,height);
        setContentView(myView);

        ball.execute(ball,divider,basket1,basket2);
    }

    private void fullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void getScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }
}

