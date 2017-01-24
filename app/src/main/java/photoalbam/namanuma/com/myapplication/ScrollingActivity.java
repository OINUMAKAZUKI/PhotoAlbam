package photoalbam.namanuma.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import photoalbam.namanuma.com.myapplication.adapter.PhotoCardRecyclerAdapter;
import photoalbam.namanuma.com.myapplication.model.PhotoCard;

public class ScrollingActivity extends AppCompatActivity {

    private static final int RESULT_PICK_IMAGEFILE = 100;

    RecyclerView mRecyclerView = null;
    PhotoCardRecyclerAdapter mPhotoCardRecyclerAdapter = null;
    List<PhotoCard> mPhotoCardList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPhotoCardList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(android.R.id.content).setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        getSupportActionBar().setTitle("My Photo");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
                Snackbar.make(view, "アルバムの詳細を編集します", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mPhotoCardRecyclerAdapter = new PhotoCardRecyclerAdapter(this, mPhotoCardList, mOnRecyclerListener(this));
        mRecyclerView.setAdapter(mPhotoCardRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_PICK_IMAGEFILE) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    PhotoCard photoCard = new PhotoCard();
                    photoCard.setBitmap(bitmap);
                    mPhotoCardList.add(photoCard);
                    mPhotoCardRecyclerAdapter.notifyDataSetChanged();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PhotoCardRecyclerAdapter.OnRecyclerListener mOnRecyclerListener(final Context context) {
        return new PhotoCardRecyclerAdapter.OnRecyclerListener() {
            @Override
            public void onClicked(PhotoCard object) {
                Intent intent = new Intent(context, ShowImageViewActivity.class);
                intent.putExtra(ShowImageViewActivity.INTENT_TITLE, object.getTitle());
                intent.putExtra(ShowImageViewActivity.INTENT_BITMAP, object.getBitmap());
                startActivity(intent);
            }
        };
    }
}
