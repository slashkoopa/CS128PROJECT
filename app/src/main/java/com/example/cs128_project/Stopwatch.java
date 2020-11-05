package com.example.cs128_project;
public class Stopwatch {
    private long elapsedTime = 0;
    private boolean isTicking = false;
    private long startTime = 0;
    private int milliSec;
    private int sec;
    /*********************************************************************
     * Returns the elapsed time in milliseconds.
     *********************************************************************/
    public Stopwatch(long startTime){
        this.startTime =startTime;
    }
    public Stopwatch Setter(int sec, int milliSec){
        this.sec = sec;
        this.milliSec = milliSec;
        return null;
    }
    public int getSec(){
        return sec;
    }

    public int getMilliSec(){
        return milliSec;
    }

    public synchronized long getElapsedTime()

    {
        if (isTicking) {
            return System.currentTimeMillis() - startTime;
        } else {
            return elapsedTime;
        }
    }

    public synchronized void reset()

    {
        elapsedTime = 0;
        milliSec = 0;
        sec =0;
        isTicking = false;
    }

    public synchronized void start()

    {
        if (isTicking) {
            throw new IllegalStateException("already started");
        }

        isTicking = true;

        startTime = System.currentTimeMillis();
    }

    public synchronized Stopwatch stop()

    {
        Long tbuff = 0L;
        if (!isTicking) {
            throw new IllegalStateException("not started");
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        Long tUpdate = tbuff + elapsedTime;
        int sec = (int) (tUpdate / 1000);
        sec = sec % 60;
        int milliSec = (int) (tUpdate % 1000);
        isTicking = false;

        return Setter(sec, milliSec);
    }
}

