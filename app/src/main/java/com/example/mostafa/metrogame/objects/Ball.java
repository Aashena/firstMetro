package com.example.mostafa.metrogame.objects;

import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.mostafa.metrogame.views.MyView;

/**
 * Created by Mostafa on 2/22/2018.
 */

public class Ball extends AsyncTask<Object,Object,Object> implements View.OnTouchListener{
    static final String TAG =new String("Ball");
    private MyView view;
    private int w,h;
    private double vx,vy;
    private double x,y,longestDistance;
    private int ballSize=60;
    private boolean isMoving;
    private boolean dChanged=false;

    public Ball(int width, int height){

        w=width;
        h=height;
        longestDistance = Math.hypot(w,h);
        vx=0;
        vy=0.9;
        x=w/2;
        y=h/2;
        move();

    }
    public void setView(MyView view){
        this.view=view;
    }
    public double getX(){
        return (int)x;
    }
    public double getY(){
        return (int)y;
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
    public int getSize(){
        return ballSize;
    }
    public boolean getIsMoving(){
        return isMoving;
    }
    public int getH(){
        return h;
    }
    public int getW(){
        return w;
    }

    public void move(){
        isMoving=true;
    }
    public void stop(){
        isMoving=false;
    }

    public boolean directionChanged(){
        if(dChanged)
        {
            dChanged=false;
            return true;
        }
        else
            return false;
    }

    @Override
    protected Object doInBackground(Object... params) {
        Ball myball=(Ball) params[0];
        double x= myball.getX();
        double y = myball.getY();
        double vx = myball.getVx();
        double vy = myball.getVy();
        boolean isMoving = myball.getIsMoving();
        int h=myball.getH();
        int w=myball.getW();
        int size = myball.getSize();

        while (isMoving==true) {
            if(directionChanged()) {
                vx = myball.getVx();
                vy = myball.getVy();
            }
            if(Math.abs(vy)>0.02 || y<h-size)
                vy=vy+0.01;
            else
                vy=0;
            x = x + vx;
            y = y + vy;

            if(x<=size){
                if(vx<0) {
//                    Log.d(TAG,"vx:"+String.valueOf(vx));
                    vx = -vx;
                    vx = vx - vx/10;
//                    Log.d(TAG, "touch by y");
//                    Log.d(TAG, "touch by x");

                }
            }
            else if (x+size>=w)
            {
                if(vx>0)
                {
                    vx = -vx;
                    vx = vx - vx/10;
//                    Log.d(TAG, "touch by x");
                }
            }
            if(y<=size) {
                if(vy<0) {
                    if(Math.abs(vy) > 0.02) {
                        vy = -vy;
                        vy = vy - vy / 10;
                    }
                    else
                        vy= 0;
                }
            }
            else if(y+size>=h){
                if(vy>0){
                    vy = -vy;
                    vy = vy - vy/10;
//                    Log.d(TAG, "touch by y");
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(x, y);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        x = (double) values[0];
        y = (double) values[1];
        view.invalidate((int)x-ballSize-1,(int)y-ballSize-1,(int)x+ballSize+1,(int)y+ballSize+1);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            view.changeBallColor();
            dChanged = true;
            int X = (int) event.getX();
            int Y = (int) event.getY();
            double dx = x - (double) X;
            double dy = y - (double) Y;
            double tmp = Math.hypot(dx, dy);
            if (longestDistance / tmp < 5) {
                vx = (dx / tmp) * (longestDistance / tmp);
                vy = (dy / tmp) * (longestDistance / tmp);
            } else {
                vx = (dx / tmp) * 5;
                vy = (dy / tmp) * 5;
            }
            Log.d(TAG, "vx:" + String.valueOf(vx));
            Log.d(TAG, "vy:" + String.valueOf(vy));
            return true;
        }
        else return false;
    }
}
