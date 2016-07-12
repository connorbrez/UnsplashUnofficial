package com.connorbrezinsky.unsplashunofficial;

import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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

        // YOU NEED YOUR OWN API INFO IF YOU WANT TO COMPILE
        HashMap<String, String> config = new HashMap<>();
        config.put("applicationId", ApiInfo.API_APPID);
        config.put("secret", ApiInfo.API_SECRET);
        config.put("callbackUrl", ApiInfo.API_CALLBACK);
        Unsplash.getInstance().init(config);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        final ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewflipper);
        final ImageView photo = (ImageView) findViewById(R.id.photo);

        final String[] navigationItems = getResources().getStringArray(R.array.navigation_items);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView drawerList = (ListView) findViewById(R.id.left_drawer);

        //drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.))

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
