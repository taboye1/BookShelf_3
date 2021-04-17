package temple.edu.bookshelf_3;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class Book implements Parcelable, Serializable {
    private int id;
    private String title;
    private String author;
    private String coverUrl;
    private int duration;

    public Book(int id,String title, String author,String coverUrl,int duration) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.duration = duration;
    }
    public Book (JSONObject b) throws JSONException {
        this(b.getInt("book_id")
                ,b.getString("title")
                ,b.getString("author")
                ,b.getString("cover_url")
                ,b.getInt("duration"));
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        coverUrl = in.readString();
        duration = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }
    public String getAuthor() {

        return author;
    }
    public void setAuthor(String author) {

        this.author = author;
    }

    public String getCoverUrl()
    {

        return coverUrl;
    }
    public void setCoverUrl(String coverUrl)

    {
        this.coverUrl = coverUrl;
    }
    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }
    @Override
    public int describeContents() {

        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverUrl);
        dest.writeInt(duration);
    }
}
