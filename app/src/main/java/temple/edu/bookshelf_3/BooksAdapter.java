package temple.edu.bookshelf_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {
    Context con;
    Library bookLib;

    public BooksAdapter (Context con, Library bookLib){
        this.con = con;
        this.bookLib = bookLib;
    }

    @Override
    public int getCount() {

        return bookLib.size();
    }
    @Override
    public Object getItem(int i){

        return bookLib.getBookAt(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView textView = view == null ? new TextView(con): (TextView) view;

        textView.setText(bookLib.getBookAt(i).getTitle());
        textView.setTextSize(25);
        textView.setPadding(4,4,4,4);

        return textView;
    }
}

