package com.example.bound_services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class MyService extends Service {
    public final IBinder iBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent)
    {
        return iBinder;
    }

    public class LocalBinder extends Binder
    {
        public MyService getService()
        {

            return  MyService.this;
        }
    }
    public static int findFactorial(int x)
    {
        int fact = 1;
        for (int i=1; i<=x; i++)
        {
            fact = fact*i;
        }
        return  fact;
    }
}
