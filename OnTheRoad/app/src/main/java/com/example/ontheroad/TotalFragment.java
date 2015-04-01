package com.example.ontheroad;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {

    private View rootView;

    private static TotalFragment mInstance;

    private TotalFragment() {
        // Required empty public constructor
    }

    public static TotalFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (MainFragment.class){
                if (mInstance == null)
                {
                    mInstance = new TotalFragment();
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
            rootView = inflater.inflate(R.layout.total_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }


}
