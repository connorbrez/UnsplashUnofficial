package com.connorbrezinsky.unsplashunofficial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.github.rye761.unsplash.Photo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;



    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return Photo.all().length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }



            try {
                imageView.setImageBitmap(getImageFromURL(Photo.all()[position].urls.thumb));
            }catch (IOException e){
                e.printStackTrace();
            }





        return imageView;
    }

    public static String[] photos;



    public static Bitmap getImageFromURL(String url) throws MalformedURLException, IOException {

        URL newurl = new URL(url);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;


        return BitmapFactory.decodeStream(newurl.openConnection().getInputStream(), new Rect(8,8,8,8), options);
    }


}