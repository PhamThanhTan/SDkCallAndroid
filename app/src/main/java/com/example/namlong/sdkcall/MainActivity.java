package com.example.namlong.sdkcall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sdkandroid.Token.BCTHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BCTHelper.initialize(this);
        startActivity(new Intent(this,MyService.class));
        setContentView(R.layout.activity_main);
    }
}
