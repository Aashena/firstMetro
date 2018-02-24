package com.example.mostafa.metrogame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.example.mostafa.metrogame.objects.Ball;

import java.util.Random;

//import com.example.mostafa.metrogame.gameObjects.Ball;

/**
 * Created by Mostafa on 2/22/2018.
 */

public class MyView extends View{
    static final String TAG= new String ("MyView");
    private Paint ballPaint;
    private Ball ball;
    public MyView(Context context,Ball ball) {
        super(context);
        setBackgroundColor(Color.argb(255,0,0,0));
        ballPaint= new Paint();
        ballPaint.setColor(Color.parseColor("#ec0a0e"));
        this.ball=ball;
        ball.setView(this);
        setOnTouchListener(ball);
    }

    public void changeBallColor (){
        Random r= new Random(System.currentTimeMillis());
        ballPaint.setColor(Color.argb(255,r.nextInt(256),r.nextInt(256),r.nextInt(256)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((int) ball.getX() , (int) ball.getY() , ball.getSize() , ballPaint);
//        Log.d(TAG,"onDraw!");
    }
}
