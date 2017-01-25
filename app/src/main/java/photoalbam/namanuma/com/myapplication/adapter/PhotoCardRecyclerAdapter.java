package photoalbam.namanuma.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import photoalbam.namanuma.com.myapplication.R;
import photoalbam.namanuma.com.myapplication.model.PhotoCard;
import photoalbam.namanuma.com.myapplication.view.SquareImageView;

/**
 * Created by k.oinuma on 2017/01/24.
 */

public class PhotoCardRecyclerAdapter extends RecyclerView.Adapter<PhotoCardRecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<PhotoCard> mList;
    private Context mContext;
    public interface OnRecyclerListener {
        void onClicked(PhotoCard object);
    }
    private OnRecyclerListener mListener;

    public PhotoCardRecyclerAdapter(Context context, List<PhotoCard> list, OnRecyclerListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mList = list;
        mListener = listener;
    }

    @Override
    public PhotoCardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_image_view, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoCardRecyclerAdapter.ViewHolder holder, int position) {
        final PhotoCard item = mList.get(position);

        Uri uri = Uri.parse(item.getUriString());
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            holder.imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // クリック処理
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // ViewHolder(固有ならインナークラスでOK)
    class ViewHolder extends RecyclerView.ViewHolder {

        SquareImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (SquareImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
