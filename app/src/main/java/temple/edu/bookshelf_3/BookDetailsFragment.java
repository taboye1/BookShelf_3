package temple.edu.bookshelf_3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class BookDetailsFragment extends Fragment {

    private static final String BOOK_TITLE_KEY = "bookkey";
    private Book book;

    TextView titleTextView;
    TextView authorTextView;
    ImageView bookCoverImageView;
    Button playBtn;
    BookPlayInterface parentActivity;

    public BookDetailsFragment() {
        // Required empty public constructor
    }


    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOK_TITLE_KEY,book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = getArguments().getParcelable(BOOK_TITLE_KEY);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookDetailsFragment.BookPlayInterface)
            parentActivity = (BookDetailsFragment.BookPlayInterface) context;
        else
            throw new RuntimeException("Please implement BookSelectedInterface");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_details, container, false);
        titleTextView = v.findViewById(R.id.titleTextView);
        authorTextView = v.findViewById(R.id.authorTextView);
        bookCoverImageView = v.findViewById(R.id.coverImageView);
        playBtn = v.findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parentActivity.bookPlay(book.getId(), book.getDuration());
            }
        });

        if (book != null) {
            changeBook(book);
        }
        return v;
    }

    public void changeBook(Book book) {
        this.book = book;
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        Picasso.get().load(book.getCoverUrl()).into(bookCoverImageView);

    }
    interface BookPlayInterface {
        void bookPlay(int id, int duration);

    }
}