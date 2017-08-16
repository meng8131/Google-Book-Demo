package com.example.android.googlebookdemo;

/**
 * Created by lyman on 2017/8/15.
 */

public class BookInfo {

    //定义新字段
    //书籍名称
    private String mTitle;
    //书记作者
    private String mAuthor;

    //定义constructor
    public BookInfo(String title, String author){
        mTitle = title;
        mAuthor = author;
    }

    //定义get method
    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){
        return mAuthor;
    }
}
