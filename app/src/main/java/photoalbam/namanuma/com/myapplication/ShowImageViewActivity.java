package photoalbam.namanuma.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class ShowImageViewActivity extends AppCompatActivity {

    public static final String INTENT_TITLE = "TITLE";
    public static final String INTENT_BITMAP = "BITMAP";

    ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_show_image_view);

        String title = getIntent().getStringExtra(INTENT_TITLE);
        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = (Bitmap) bundle.get(INTENT_BITMAP);


        getSupportActionBar().setTitle("選択したタイトルまたは日付");

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageBitmap(bitmap);
    }
}
