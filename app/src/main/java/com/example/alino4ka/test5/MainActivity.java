package com.example.alino4ka.test5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alino4ka.test5.test1.Test1Activity;
import com.example.alino4ka.test5.test2.Test2Activity;
import com.example.alino4ka.test5.test4.Test4Activity;

public class MainActivity extends AppCompatActivity {
    Button test1;
    Button test2;
    Button test3;
    Button test4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test1Activity.class));
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test2Activity.class));
            }
        });

        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Метод onDestroy будет вызван после метода onCreate", Toast.LENGTH_SHORT).show();
            }
        });

        test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test4Activity.class));
            }
        });

    }

    private void init(){
        test1 = (Button) findViewById(R.id.test1);
        test2 = (Button) findViewById(R.id.test2);
        test3 = (Button) findViewById(R.id.test3);
        test4 = (Button) findViewById(R.id.test4);
    }
}
