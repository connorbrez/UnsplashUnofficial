package com.connorbrezinsky.unsplashunofficial;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.github.rye761.unsplash.Photo;
import com.github.rye761.unsplash.Unsplash;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HashMap<String, String> config = new HashMap<>();
        config.put("applicationId", "your_appid");
        config.put("secret", "your_secret");
        config.put("callbackUrl", "urn:ietf:wg:oauth:2.0:oob");
        Unsplash.getInstance().init(config);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        final ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewflipper);
        final ImageView photo = (ImageView) findViewById(R.id.photo);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                vf.showNext();
                try {
                    photo.setImageBitmap(ImageAdapter.getImageFromURL(Photo.all()[position].urls.regular));
                }catch(IOException e){
                    e.printStackTrace();
                }


            }
        });

    }
}
