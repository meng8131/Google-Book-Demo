package com.example.android.googlebookdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lyman on 2017/8/16.
 */

public class QueryAdapter extends ArrayAdapter<BookInfo> {

    public QueryAdapter(Context context, List<BookInfo> books){
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.query_results, parent, false);
        }

        //find the book at the given position in the list of query results
        BookInfo currentBook = getItem(position);

        //get the tittle and author of this book and assign to according TextView
        TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title);
        String title = currentBook.getTitle();
        bookTitle.setText(title);

        TextView bookAuthor =(TextView) listItemView.findViewById(R.id.book_author);
        String author = currentBook.getAuthor();
        bookAuthor.setText(author);
        
        return listItemView;
    }
}
