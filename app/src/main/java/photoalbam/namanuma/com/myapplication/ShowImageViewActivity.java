package photoalbam.namanuma.com.myapplication;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;

import io.realm.Realm;
import photoalbam.namanuma.com.myapplication.model.PhotoCard;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class ShowImageViewActivity extends AppCompatActivity {

    public static final String INTENT_PHOTO_ID = "PHOTO_ID";

    ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(INTENT_PHOTO_ID, 0);

        Realm realm = Realm.getDefaultInstance();
        PhotoCard photo = realm.where(PhotoCard.class).equalTo("id", id).findFirst();
        realm.close();

        Uri uri = Uri.parse(photo.getUriString());
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getSupportActionBar().setTitle("選択したタイトルまたは日付");

        mImageView = (ImageView) findViewById(R.id.imageView);

        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
