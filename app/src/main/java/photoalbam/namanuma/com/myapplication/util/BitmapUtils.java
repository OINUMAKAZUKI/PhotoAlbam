package photoalbam.namanuma.com.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class BitmapUtils {

    public static String toString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String bitmapStr = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        return bitmapStr;
    }

    public static Bitmap toBitmap(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        byte[] b = Base64.decode(str, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length).copy(Bitmap.Config.ARGB_8888, true);
        return bitmap;
    }
}
