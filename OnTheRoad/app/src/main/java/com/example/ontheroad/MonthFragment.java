package com.example.ontheroad;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment {


    private View rootView;

    private static MonthFragment mInstance;

    private MonthFragment() {
        // Required empty public constructor
    }

    public static MonthFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (MonthFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new MonthFragment();
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
            rootView = inflater.inflate(R.layout.month_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

}
