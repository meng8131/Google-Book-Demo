package com.example.android.googlebookdemo;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.googlebookdemo.MainActivity.queryUrl;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    private static final String LOG_TAG = ResultActivity.class.getName();

    private QueryAdapter mQueryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG,"ResultActivity onCreate() method is called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView listView = (ListView) findViewById(R.id.result_list_view);

        mQueryAdapter = new QueryAdapter(this, new ArrayList<BookInfo>());

        listView.setAdapter(mQueryAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"Method onCreateLoader is called");
        return new QueryLoader(this, queryUrl);
    }


    @Override
    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> data) {
        Log.i(LOG_TAG,"Method onLoadFinished is called");
        View progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        mQueryAdapter.clear();
        Log.i(LOG_TAG,"Adapter is cleared before assigning parsed data");

        //如果JSON解析数据非null，则向adapter添加解析得到的Array
        if (data != null && !data.isEmpty()){
            mQueryAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookInfo>> loader) {
        Log.i(LOG_TAG,"Method onLoaderReset is called");
        mQueryAdapter.clear();
        Log.i(LOG_TAG,"Adapter is cleared");

    }
}
