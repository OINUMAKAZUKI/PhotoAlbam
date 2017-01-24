package photoalbam.namanuma.com.myapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class PhotoCard extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private String bitmap;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBitmap() {
        return bitmap;
    }
}
