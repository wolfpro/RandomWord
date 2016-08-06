package com.test.wp.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText myText;
    private Random random;
    private Button butRand, butClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (EditText) findViewById(R.id.editText);

        myText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (getCurrentFocus() != null) {

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        //((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });

        butRand = (Button) findViewById(R.id.button);
        butClear = (Button) findViewById(R.id.button2);

        random = new Random();

    }

    private String valid(String a) {
        String s = "";
        for (int i = 0; i < a.length(); ++i) {
            char c = a.charAt(i);
            s += c == '\n' ? ' ' : c;
        }
        a = s;
        s = "";

        for (int i = 0; i < a.length(); ++i) {
            char c = a.charAt(i);
            if (i > 0) {
                char b = a.charAt(i - 1);
                if (c == b && c == ' ') {
                    continue;
                }
            }
            s += a.charAt(i);
        }

        if(s.equals(" "))
            s= "";
        return s;
    }


    public void onClickRandom(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(butRand.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);


        ArrayList<String> arrayList = new ArrayList();
        String s = myText.getText().toString();
        s = valid(s);

        if(s.length()==0)
            return;

        for (String spt : s.split(" ")) {
            arrayList.add(spt);
        }

        String ans = arrayList.get(random.nextInt(arrayList.size()));
        Toast.makeText(getApplicationContext(), ans, Toast.LENGTH_SHORT).show();
        //toast.show();
    }

    public void onClickClear(View view) {
        myText.setText("");
    }
}
