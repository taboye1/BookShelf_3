package temple.edu.bookshelf_3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class BookListFragment extends Fragment implements Displayable {

    private static final String BOOKS_KEY = "bookList";

    private Library bookList;
    ListView listView;
    BookSelectedInterface parentActivity;

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance(Library bookList) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOKS_KEY, bookList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookSelectedInterface) {
            parentActivity = (BookSelectedInterface) context;
        } else {
            throw new RuntimeException("Please implement the required interfaces");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookList = getArguments().getParcelable(BOOKS_KEY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_book_list, container, false);
        listView = layout.findViewById(R.id.listView);
        listView.setAdapter(new BooksAdapter((Context) parentActivity, bookList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                parentActivity.bookSelected(bookList.getBookAt(i));
            }
        });
        return layout;
    }
    @Override
    public Library getBooks() {
        return bookList;
    }

    @Override
    public void setBooks(Library books) {
        bookList = books;
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    interface BookSelectedInterface {
        void bookSelected(Book book);
    }

}