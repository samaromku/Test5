package com.example.alino4ka.test5.test2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.alino4ka.test5.R;


public class Test2Activity extends AppCompatActivity{
    private Fragment createFragment(){
        return new MapFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2_activity);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.map_container);
        if(fragment==null){
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.map_container, fragment)
                    .commit();
        }
    }
}
