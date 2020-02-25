package ar.com.jdodevelopment.moviesapp.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;


public class DataBindingUtil {


    @BindingAdapter({"imageUrl"})
    public static void imageLoader(ImageView imageView, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .into(imageView);
    }


    @BindingAdapter({"imageUrl", "placeholder"})
    public static void imageLoader(ImageView imageView, String imageUrl, Drawable placeHolder) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(placeHolder)
                .into(imageView);

    }

}