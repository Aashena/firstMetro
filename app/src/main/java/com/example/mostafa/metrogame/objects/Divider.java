package com.example.mostafa.metrogame.objects;

/**
 * Created by Mostafa on 2/24/2018.
 */

public class Divider {
    private static final String TAG= new String("Divider");
    private int w,h;
    private int x,y;
    private int thick=60;
    public Divider(int w,int h){
        this.w=w;
        this.h=h;
        y=(h/3)*2;
        x=w/2-thick/2;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getThick(){
        return thick;
    }
}
