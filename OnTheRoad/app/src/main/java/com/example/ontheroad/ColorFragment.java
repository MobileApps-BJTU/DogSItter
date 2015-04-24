package com.example.ontheroad;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    private View rootView;

    private static ColorFragment mInstance;

    private ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (ColorFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new ColorFragment();
                }
            }
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.color_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }


}
