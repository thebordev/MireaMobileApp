package com.example.mireaapp.Frgaments.Explorer.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mireaapp.R;

import java.util.ArrayList;
import java.util.List;

public class BookLibrary extends AppCompatActivity implements BookCallback{

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mdata;
    private ImageView btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_library);

        initViews();
        initmdataBooks();
        setupBookAdapter();
    }

    private void setupBookAdapter() {

        bookAdapter = new BookAdapter(mdata, this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initmdataBooks() {

        mdata = new ArrayList<>();
        mdata.add(new Book(R.drawable.lagom));
        mdata.add(new Book(R.drawable.book2));
        mdata.add(new Book(R.drawable.book3));
    }

    private void initViews() {

        btnAddBook = findViewById(R.id.favorite_bottom_book);
        rvBooks = findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);
        rvBooks.setItemAnimator(new CustomItemAnimator());

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }

    private void removeBook() {

        mdata.remove(1);
        bookAdapter.notifyItemRemoved(1);
    }

    private void addBook() {
        Book book = new Book(R.drawable.book2);
        mdata.add(1, book);
        bookAdapter.notifyItemInserted(1);
    }

    @Override
    public void onBookItemClick(int pos,
                                ImageView imgContainer,
                                ImageView imgBook,
                                TextView title,
                                TextView authorName,
                                TextView nbpages,
                                TextView score,
                                RatingBar ratingBar) {

        Intent intent = new Intent(this, BookDetails.class);
        intent.putExtra("bookObject", mdata.get(pos));

        Pair<View,String> p1 = Pair.create((View)imgContainer,"containerTN"); // second arg is the tansition string Name
        Pair<View,String> p2 = Pair.create((View)imgBook,"bookTN"); // second arg is the tansition string Name
        Pair<View,String> p3 = Pair.create((View)title,"booktitleTN"); // second arg is the tansition string Name
        Pair<View,String> p4 = Pair.create((View)authorName,"authorTN"); // second arg is the tansition string Name
        Pair<View,String> p5 = Pair.create((View)nbpages,"bookpagesTN"); // second arg is the tansition string Name
        Pair<View,String> p6 = Pair.create((View)score,"scoreTN"); // second arg is the tansition string Name
        Pair<View,String> p7 = Pair.create((View)ratingBar,"rateTN"); // second arg is the tansition string Name


//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3, p4, p5, p6, p7);


        // start the activity with scene transition

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
        }
        else
            startActivity(intent);

    }
}