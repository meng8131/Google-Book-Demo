package com.example.android.googlebookdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    private Editable queryInput;

    private static String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    protected static String queryUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG,"MainActivity onCreate() method is called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.query_input);
        queryInput = editText.getText();

        TextView querySubmit = (TextView) findViewById(R.id.query_submit);
        querySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (queryInput == null){
                    //Intent提示“请输入查询关键词”

                    queryUrl = null;
                } else {
                    //根据输入组合请求URL
                    queryUrl = baseUrl + queryInput.toString();
                    Log.i(LOG_TAG, "The input is " + queryInput +" ,and query URL is " + queryUrl);

                    //Intent启动ResultActivity
                    Intent submitIntent = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(submitIntent);
                }
            }
        });
    }
}
