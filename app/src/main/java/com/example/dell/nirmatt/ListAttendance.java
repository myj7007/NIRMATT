package com.example.dell.nirmatt;

/**
 * Created by DELL on 5/1/2018.
 */

public class ListAttendance {
    private long absent;
    private long present;
    private String subject;
    

    public ListAttendance(String subject,long abs ,long pre) {
        this.subject = subject;
        this.absent=abs;
        this.present=pre;
    }

    public long getAbsent() {
        return absent;
    }

    public long getPresent() {
        return present;
    }

    public String getSubject() {
        return subject;



    }
}
