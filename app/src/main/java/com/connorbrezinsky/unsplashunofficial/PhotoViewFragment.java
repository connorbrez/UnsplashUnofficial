package com.connorbrezinsky.unsplashunofficial;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class PhotoViewFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        if(savedInstanceState!=null){
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }



        return inflater.inflate(R.layout.photo_view, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();


        Bundle args = getArguments();
        if (args != null) {
            updatePhotoContainer(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updatePhotoContainer(mCurrentPosition);
        }


    }


    public void updatePhotoContainer(int position) {
        ImageView photo = (ImageView) getActivity().findViewById(R.id.photo_container);
        try {
            photo.setImageBitmap(getImageFromURL(PhotoHandler.photos[position]));
        }catch (IOException e){
            e.printStackTrace();
        }
        mCurrentPosition = position;

    }

    public static Bitmap getImageFromURL(String url) throws MalformedURLException, IOException {

            URL newurl = new URL(url);
            return BitmapFactory.decodeStream(newurl.openConnection().getInputStream());



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putInt(ARG_POSITION, mCurrentPosition);
    }


}
