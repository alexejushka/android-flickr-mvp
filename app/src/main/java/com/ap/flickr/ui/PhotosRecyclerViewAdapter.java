package com.ap.flickr.ui;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ap.flickr.PhotosContract;
import com.ap.flickr.R;
import com.ap.flickr.databinding.RItemBinding;
import com.ap.flickr.databinding.RItemBindingImpl;
import com.ap.flickr.pojo.Photo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.ViewHolder> {

    private List<Photo> photos;
    private PhotosContract.Presenter presenter;

    PhotosRecyclerViewAdapter(List<Photo> data, PhotosContract.Presenter presenter) {
        this.photos = data;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RItemBindingImpl binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.r_item, parent, false);
        binding.setPresenter(presenter);
        return new ViewHolder(binding);
    }

    @BindingAdapter("android:src")
    public static void bindImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        holder.bind(photo);
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        private final RItemBinding binding;

        ViewHolder(RItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Photo item) {
            binding.setPhoto(item);
            binding.executePendingBindings();
        }

    }

}