package com.minglead.security.security.cas;

public class Gable {

    private String desc;

    public Gable(String desc){
        this.desc = desc;
    }

    public String getGable(){
        return this.desc;
    }

    public String putGable(String desc){
        this.desc = desc;
        return this.desc;
    }
}
