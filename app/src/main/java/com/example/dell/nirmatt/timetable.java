package com.example.dell.nirmatt;

import android.util.Log;

/**
 * Created by DELL on 4/6/2018.
 */

public class timetable {
    public String clas;
    public String subject;
    public String type;

    /*public timetable(String ar){
        String[] arr=ar.split(",");
        Log.d("CI",arr[0]);
        this.clas=arr[0];
        this.subject=arr[1];
        this.type=arr[2];
    }*/

    public timetable(){

    }

    public timetable(String clas, String subject, String type) {
        this.clas = clas;
        this.subject = subject;
        this.type = type;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
