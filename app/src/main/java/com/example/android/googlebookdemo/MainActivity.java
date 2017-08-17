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

//    private QueryAdapter mQueryAdapter;

    private Editable queryInput;

    private static String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    protected static String queryUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG,"Earthquake Activity onCreate() method is called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ListView listView = (ListView) findViewById(R.id.result_list_view);
//
//        mQueryAdapter = new QueryAdapter(this, new ArrayList<BookInfo>());
//
//        listView.setAdapter(mQueryAdapter);

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

                    //Intent启动ResultActivity
                    Intent submitIntent = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(submitIntent);
                }
            }
        });

//        LoaderManager loaderManager = getLoaderManager();
//        loaderManager.initLoader(0, null, this);
    }

//    @Override
//    public Loader<List<BookInfo>> onCreateLoader(int id, Bundle args) {
//        return new QueryLoader(this, queryUrl);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> data) {
//        View progressBar = findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.GONE);
//
//        mQueryAdapter.clear();
//
//        //如果JSON解析数据非null，则向adapter添加解析得到的Array
//        if (data != null && !data.isEmpty()){
//            mQueryAdapter.add((BookInfo) data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<BookInfo>> loader) {
//        mQueryAdapter.clear();
//
//    }
}
