package photoalbam.namanuma.com.myapplication.model;

import android.graphics.Bitmap;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class PhotoCard {
    private String title;
    private Bitmap bitmap;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
