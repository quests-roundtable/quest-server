package com.quest.questserver.model;

public abstract class Adventure extends Card{
    int point;


    //getter
    public int getPoint(){
        return point;
    }

    public void setPoint(int n){
        point = n;
    }
}
