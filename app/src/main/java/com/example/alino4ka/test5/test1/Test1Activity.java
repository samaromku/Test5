package com.example.alino4ka.test5.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.alino4ka.test5.R;

import java.util.ArrayList;
import java.util.List;

public class Test1Activity extends AppCompatActivity{
    Button addEditText;
    LinearLayout whereToAdd;
    private static final String TAG = "Test1Activity";
    private ArrayList<CharSequence>texts = new ArrayList<>();
    private List<EditText> edittexts = new ArrayList<>();
    private static final String KEY = "text";
    ArrayList<CharSequence>savedItems;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.test1_activity);
        init();
        if(savedInstanceState!=null){
            for (int i = 0; i < savedItems.size(); i++) {
                addEditTextMethod(savedItems.get(i));
            }
        }
        addEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditTextMethod("");
            }
        });
    }

    private void init(){
        addEditText = (Button) findViewById(R.id.add_edit_text);
        whereToAdd = (LinearLayout) findViewById(R.id.where_to_add);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequenceArrayList(KEY, texts);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedItems = savedInstanceState.getCharSequenceArrayList(KEY);
    }

    private void addEditTextMethod(CharSequence text){
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final EditText et = new EditText(this);
        et.setText(text);
        et.setLayoutParams(lp);
        et.setHint("hello");
        whereToAdd.addView(et);
        edittexts.add(et);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < edittexts.size(); i++) {
            texts.add(edittexts.get(i).getText());
        }
    }
}
