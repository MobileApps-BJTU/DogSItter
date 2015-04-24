package com.example.ontheroad;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class    VarietyFragment extends Fragment {

    private View rootView;

    private static VarietyFragment mInstance;

    private VarietyFragment() {
        // Required empty public constructor
    }

    public static VarietyFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (VarietyFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new VarietyFragment();
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
            rootView = inflater.inflate(R.layout.variety_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }


}