package com.example.dell.nirmatt;

/**
 * Created by DELL on 5/1/2018.
 */

public class ListSlot {
    private String clas;
    private String time;
    private String subject;

    public ListSlot(String clas, String time, String subject) {
        this.clas = clas;
        this.time = time;
        this.subject = subject;
    }

    public String getClas() {
        return clas;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }
}
