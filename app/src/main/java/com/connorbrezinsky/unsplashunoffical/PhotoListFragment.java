package com.connorbrezinsky.unsplashunoffical;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class PhotoListFragment extends Fragment {

    OnPhotoItemSelectedListener mCallback;

    public interface OnPhotoItemSelectedListener{
        public void OnPhotoItemSelected(int position);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_list,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.fragment_container);
        gridView.setAdapter(new PhotoImageAdapter(view.getContext())); // uses the view to get the context instead of getActivity().
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.list_fragment) !=null ){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnPhotoItemSelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnPhotoItemSelectedListener");
        }

    }





}
