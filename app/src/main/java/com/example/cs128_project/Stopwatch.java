package com.example.cs128_project;
public class Stopwatch {
    private long elapsedTime = 0;
    private boolean isTicking = false;
    private long startTime = 0;
    /*********************************************************************
     * Returns the elapsed time in milliseconds.
     *********************************************************************/
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

    public synchronized void stop()

    {
        if (!isTicking) {
            throw new IllegalStateException("not started");
        }

        elapsedTime = System.currentTimeMillis() - startTime;

        isTicking = false;
    }
}

