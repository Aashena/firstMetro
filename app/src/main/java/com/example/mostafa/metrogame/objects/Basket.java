package com.example.mostafa.metrogame.objects;

/**
 * Created by Mostafa on 2/25/2018.
 */

public class Basket {
    private boolean left;
    private int xL,yT,xR;
    private int size=20;
    public Basket(int ballSize ,boolean left,int w,int h){
        this.left = left;
        yT= h/3;
        if(left) {
            xL = ballSize/3;
            xR = xL + ballSize*2 + (ballSize);
        }
        else{
            xR = w - ballSize/3;
            xL = xR - ballSize*2 - (ballSize);
        }
    }
    public boolean isLeft() {
        return left;
    }
    public int getXL(){
        return xL;
    }
    public int getYT(){
        return yT;
    }
    public int getXR(){
        return xR;
    }
    public int getSize(){
        return size;
    }

}
