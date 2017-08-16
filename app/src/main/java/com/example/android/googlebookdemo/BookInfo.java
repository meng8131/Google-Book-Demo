package com.example.android.googlebookdemo;

/**
 * Created by lyman on 2017/8/15.
 */

public class BookInfo {

    //定义新字段
    //书籍名称
    private String mName;
    //书记作者
    private String mAuthor;

    //定义constructor
    public BookInfo(String name, String author){
        mName = name;
        mAuthor = author;
    }

    //定义get method
    public String getName(){
        return mName;
    }

    public String getAuthor(){
        return mAuthor;
    }
}
