package com.example.mostafa.metrogame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.example.mostafa.metrogame.objects.Ball;
import com.example.mostafa.metrogame.objects.Basket;
import com.example.mostafa.metrogame.objects.Divider;

import java.util.Random;

//import com.example.mostafa.metrogame.gameObjects.Ball;

/**
 * Created by Mostafa on 2/22/2018.
 */

public class MyView extends View{
    static final String TAG= new String ("MyView");
    private Paint ballPaint , dividerPaint, basket1Paint;
    private Ball ball;
    private Divider divider;
    private Basket basket1 , basket2;
    private int w,h;
    public MyView(Context context, Ball ball, Divider divider , Basket bask1,Basket bask2 , int w, int h) {
        super(context);
        this.w=w;
        this.h=h;
        setBackgroundColor(Color.argb(255,0,0,0));
        ballPaint = new Paint();
        ballPaint.setColor(Color.parseColor("#ec0a0e"));
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.parseColor("#ec0a0e"));
        basket1Paint = new Paint();
        basket1Paint.setColor(Color.parseColor("#ec0a0e"));
        basket1Paint.setStyle(Paint.Style.STROKE);
        basket1Paint.setStrokeWidth(8);
        this.ball = ball;
        this.divider = divider;
        basket1 = bask1;
        basket2 = bask2;
        ball.setView(this);
        setOnTouchListener(ball);
    }

    public void changeBallColor (){
        Random r= new Random(System.currentTimeMillis());
        ballPaint.setColor(Color.argb(255,r.nextInt(256),r.nextInt(256),r.nextInt(256)));
    }
    public void changeDividerColor(){
        Random r= new Random(System.currentTimeMillis());
        dividerPaint.setColor(Color.argb(255,r.nextInt(256),r.nextInt(256),r.nextInt(256)));
    }
    public void changebasket1Color(){
        Random r= new Random(System.currentTimeMillis());
        basket1Paint.setColor(Color.argb(255,r.nextInt(256),r.nextInt(256),r.nextInt(256)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (divider!=null) canvas.drawRect(new Rect(divider.getX(),divider.getY(),divider.getX()+divider.getThick(),h),dividerPaint);

        if(basket1!= null) {
            if (basket1.isLeft()) {
                canvas.drawLine(0, basket1.getYT() + basket1.getSize() / 2, basket1.getXL(), basket1.getYT() + basket1.getSize() / 2, basket1Paint);
                canvas.drawOval(new RectF(basket1.getXL(), basket1.getYT(), basket1.getXR(), basket1.getYT() + basket1.getSize()), basket1Paint);
            } else {
                canvas.drawLine(w, basket1.getYT() + basket1.getSize() / 2, basket1.getXR(), basket1.getYT() + basket1.getSize() / 2, basket1Paint);
                canvas.drawOval(new RectF(basket1.getXL(), basket1.getYT(), basket1.getXR(), basket1.getYT() + basket1.getSize()), basket1Paint);
            }
        }
        if(basket2!=null) {
            if (basket2.isLeft()) {
                canvas.drawLine(0, basket2.getYT() + basket2.getSize() / 2, basket2.getXL(), basket2.getYT() + basket2.getSize() / 2, basket1Paint);
                canvas.drawOval(new RectF(basket2.getXL(), basket2.getYT(), basket2.getXR(), basket2.getYT() + basket2.getSize()), basket1Paint);
            } else {
                canvas.drawLine(w, basket2.getYT() + basket2.getSize() / 2, basket2.getXR(), basket2.getYT() + basket2.getSize() / 2, basket1Paint);
                canvas.drawOval(new RectF(basket2.getXL(), basket2.getYT(), basket2.getXR(), basket2.getYT() + basket2.getSize()), basket1Paint);
            }
        }
        canvas.drawCircle((int) ball.getX() , (int) ball.getY() , ball.getSize() , ballPaint);
//        Log.d(TAG,"onDraw!");
    }
}
