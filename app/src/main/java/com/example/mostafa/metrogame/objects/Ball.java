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
        boolean changeDividerColor=false;
        Ball myball=(Ball) params[0];
        double x= myball.getX();
        double y = myball.getY();
        double vx = myball.getVx();
        double vy = myball.getVy();
        boolean isMoving = myball.getIsMoving();
        int h=myball.getH();
        int w=myball.getW();
        int size = myball.getSize();
        Divider divider=(Divider) params[1];
        int dividersX=0;
        int dividersY=0;
        int dividersThick=0;
        if(divider!=null)
        {
            dividersX =divider.getX();
            dividersY = divider.getY();
            dividersThick = divider.getThick();
        }
        Basket basket1 = (Basket) params[2];
        int b1xl=0;
        int b1xr=0;
        int b1yt=0;
        int b1Size=0;
        if(basket1!=null)
        {
            b1xl=basket1.getXL();
            b1xr=basket1.getXR();
            b1yt=basket1.getYT();
            b1Size=basket1.getSize();
        }
        Basket basket2 = (Basket) params[3];
        int b2xl=0;
        int b2xr=0;
        int b2yt=0;
        int b2Size=0;
        if(basket2!=null)
        {
            b2xl=basket2.getXL();
            b2xr=basket2.getXR();
            b2yt=basket2.getYT();
            b2Size=basket2.getSize();
        }

        while (isMoving==true) {
            if(directionChanged()) {
                vx = myball.getVx();
                vy = myball.getVy();
            }
            if(Math.abs(vy)>0.02 || y<h-size)
                vy=vy+0.015;
            else
                vy=0;
            x = x + vx;
            y = y + vy;

            if(x<=size){
                if(vx<0) {
//                    Log.d(TAG,"vx:"+String.valueOf(vx));
                    vx = -vx;
                    vx = vx - vx/10;
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
            if(divider != null) {
                if (y >= dividersY - size && x >= dividersX - size && x <= dividersX + dividersThick + size) { //divider
                    if (y >= dividersY) {
                        if (x <= dividersX + dividersThick + size && x > dividersX + dividersThick) {
                            if (vx < 0) {
//                    Log.d(TAG,"vx:"+String.valueOf(vx));
                                vx = -vx;
                                vx = vx - vx / 10;
                                Log.d(TAG, "touch by divider's right");
                                changeDividerColor = true;

//                    Log.d(TAG, "touch by x");

                            }
                        } else if (x >= dividersX - size && x < dividersX) {
                            if (vx > 0) {
                                vx = -vx;
                                vx = vx - vx / 10;
                                Log.d(TAG, "touch by divider's left");
                                changeDividerColor = true;
                            }
                        }
                    } else if (x >= dividersX && x < dividersX + dividersThick) {
                        if (y >= dividersY - size) {
                            if (vy > 0) {
                                if (Math.abs(vy) > 0.02) {
                                    vy = -vy;
                                    vy = vy - vy / 10;
                                    Log.d(TAG, "touch by divider's Top");
                                    changeDividerColor = true;
                                } else
                                    vy = 0;
                            }
                        }
                    } else {
                        int forceArowX;
                        int forceArowY = (int) y - (dividersY);
                        if (x > w / 2)
                            forceArowX = (int) x - (dividersX + dividersThick);
                        else
                            forceArowX = (int) x - (dividersX);
                        double forceArowLen = Math.hypot(forceArowX, forceArowY);
                        double fX, fY;
                        if (forceArowLen <= size) {
                            fX = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowX;
                            fY = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowY;
                            if (fX * forceArowX < 0 || fY * forceArowY < 0) {
                                vx = vx - 2 * fX;
                                vy = vy - 2 * fY;
                                vy = vy - vy / 10;
                                vx = vx - vx / 10;
                            }
                        }
                    }
                }
            }
            if(basket1!=null) { //basket1
                if (x >= b1xl - size && x <= b1xr + size && y >= b1yt - size && y <= b1yt + b1Size + size) {
                    int forceArowX;
                    int forceArowY = (int) y - (b1yt);
                    if (x > (b1xl + b1xr) / 2)
                        forceArowX = (int) x - (b1xr);
                    else
                        forceArowX = (int) x - (b1xl);
                    double forceArowLen = Math.hypot(forceArowX, forceArowY);
                    double fX, fY;
                    if (forceArowLen <= size) {
                        fX = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowX;
                        fY = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowY;
                        if (fX * forceArowX < 0 || fY * forceArowY < 0) {
                            vx = vx - 2 * fX;
                            vy = vy - 2 * fY;
                            vy = vy - vy / 10;
                            vx = vx - vx / 10;
                        }
                    }
                }
            }
            if(basket2!=null) { //basket2
                if (x >= b2xl - size && x <= b2xr + size && y >= b2yt - size && y <= b2yt + b2Size + size) {
                    int forceArowX;
                    int forceArowY = (int) y - (b2yt);
                    if (x > (b2xl + b2xr) / 2)
                        forceArowX = (int) x - (b2xr);
                    else
                        forceArowX = (int) x - (b2xl);
                    double forceArowLen = Math.hypot(forceArowX, forceArowY);
                    double fX, fY;
                    if (forceArowLen <= size) {
                        fX = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowX;
                        fY = ((vx * forceArowX) + (vy * forceArowY)) / (forceArowLen * forceArowLen) * forceArowY;
                        if (fX * forceArowX < 0 || fY * forceArowY < 0) {
                            vx = vx - 2 * fX;
                            vy = vy - 2 * fY;
                            vy = vy - vy / 10;
                            vx = vx - vx / 10;
                        }
                    }
                }
            }



            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(x, y,changeDividerColor);
            changeDividerColor=false;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        x = (double) values[0];
        y = (double) values[1];
        view.invalidate((int)x-ballSize-1,(int)y-ballSize-1,(int)x+ballSize+1,(int)y+ballSize+1);
        if((boolean)values[2])
            view.changeDividerColor();
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
