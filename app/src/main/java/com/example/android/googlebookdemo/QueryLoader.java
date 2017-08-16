package com.example.android.googlebookdemo;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by lyman on 2017/8/16.
 */

public class QueryLoader extends AsyncTaskLoader<List<BookInfo>> {
    private static final String LOG_TAG = QueryLoader.class.getName();

    private String mUrl;

    public QueryLoader(Context context, String mUrl){
        super(context);
        mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BookInfo> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        //execute query process
        List<BookInfo> results = QueryProcess.fetchBookData(mUrl);

        return results;
    }
}
