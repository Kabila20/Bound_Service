package com.example.bound_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    MyService myService;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void bindMethod(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(getBaseContext(), "service is binded!", Toast.LENGTH_SHORT).show();
    }


    public void unBindMethod(View view) {
        if (status) {
            unbindService(sc);
            Toast.makeText(getBaseContext(), "service is Unbinded!", Toast.LENGTH_LONG).show();
            status = true;
        }
        else
        {
            Toast.makeText(getBaseContext(), "first you have to bind!", Toast.LENGTH_LONG).show();
        }
    }


    public void factMethod(View view) {
        if (status)
        {
            int num = Integer.parseInt(editText.getText().toString());
            int result = MyService.findFactorial(num);
            Toast.makeText(getBaseContext(), "Factorial = " + result, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getBaseContext(), "enter no correct", Toast.LENGTH_LONG).show();
        }
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder  service) {
            MyService.LocalBinder binder=(MyService.LocalBinder)service ;
            myService=binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
