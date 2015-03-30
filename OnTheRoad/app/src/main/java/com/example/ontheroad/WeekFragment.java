package com.example.ontheroad;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends Fragment {


    private View rootView;

    private static WeekFragment mInstance;

    private WeekFragment() {
        // Required empty public constructor
    }

    public static WeekFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (WeekFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new WeekFragment();
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
            rootView = inflater.inflate(R.layout.week_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }


}
