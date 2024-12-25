package com.example.bookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<BookModel> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this layout exists

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns in grid

        // Create the book list and add books
        bookList = new ArrayList<>();
        bookList.add(new BookModel("Da Vinci Code", "Dan Brown", "A gripping modern classic.", R.drawable.cover1, "$19.99", true));
        bookList.add(new BookModel("1984", "George Orwell", "A dystopian social science fiction novel.", R.drawable.cover2, "$15.99", true));
        bookList.add(new BookModel("To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice.", R.drawable.cover3, "$12.99", true));
        bookList.add(new BookModel("Pride and Prejudice", "Jane Austen", "A romantic novel of manners.", R.drawable.cover4, "$10.99", true));
        bookList.add(new BookModel("Moby Dick", "Herman Melville", "The narrative of a whaling ship.", R.drawable.cover5, "$22.99", true));

        // Initialize the adapter and set it to the RecyclerView
        bookAdapter = new BookAdapter(bookList, this);
        recyclerView.setAdapter(bookAdapter);
    }

    // Inner class for the adapter
    private class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
        private List<BookModel> bookList;
        private MainActivity mainActivity;

        public BookAdapter(List<BookModel> bookList, MainActivity mainActivity) {
            this.bookList = bookList;
            this.mainActivity = mainActivity;
        }

        @NonNull
        @Override
        public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false); // Ensure item_book layout exists
            return new BookViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
            BookModel book = bookList.get(position);
            holder.coverImageView.setImageResource(book.getCoverImage()); // Correct method call
            holder.titleTextView.setText(book.getTitle());
            holder.authorTextView.setText(book.getAuthor());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mainActivity, BookDetailActivity.class);
                intent.putExtra("title", book.getTitle());
                intent.putExtra("author", book.getAuthor());
                intent.putExtra("description", book.getDescription());
                intent.putExtra("coverImageResource", book.getCoverImage());
                intent.putExtra("price", book.getPrice());
                intent.putExtra("availability", book.isAvailable());
                mainActivity.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return bookList.size();
        }

        class BookViewHolder extends RecyclerView.ViewHolder {
            ImageView coverImageView;
            TextView titleTextView;
            TextView authorTextView;

            public BookViewHolder(@NonNull View itemView) {
                super(itemView);
                coverImageView = itemView.findViewById(R.id.coverImageView); // Ensure this ID exists in item_book layout
                titleTextView = itemView.findViewById(R.id.titleTextView); // Ensure this ID exists in item_book layout
                authorTextView = itemView.findViewById(R.id.authorTextView); // Ensure this ID exists in item_book layout
            }
        }
    }
}
