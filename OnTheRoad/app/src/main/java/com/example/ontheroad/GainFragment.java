package com.example.ontheroad;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GainFragment extends Fragment {

    private View rootView;

    private static GainFragment mInstance;

    private TextView show, charity;

    private FragmentManager fm;

    private boolean isShowClicked = true;
    private boolean isCharityClicked = false;

    private GainFragment() {
        // Required empty public constructor
    }

    public static GainFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (GainFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new GainFragment();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.gain_fragment,container,false);

            show = (TextView)rootView.findViewById(R.id.id_show);
            charity = (TextView)rootView.findViewById(R.id.id_charity);

            Typeface typeFace =Typeface.createFromAsset(getActivity().getAssets(),"fonts/Bradley Hand ITC.TTF");

            show.setTypeface(typeFace, Typeface.BOLD);
            charity.setTypeface(typeFace, Typeface.BOLD);

            show.setOnClickListener(showListener);
            charity.setOnClickListener(charityListener);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        isShowClicked = true;
        isCharityClicked = false;

        show.setBackgroundResource(R.drawable.button_clik_style);
        charity.setBackgroundResource(R.drawable.button_unclik_style);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_gain_container, ShowFragment.getInstance());
        ft.commit();

        return rootView;
    }

    @Override
    public void onDestroy() {
        if(isShowClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(ShowFragment.getInstance());
            ft.commit();
        }
        if(isCharityClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(CharityFragment.getInstance());
            ft.commit();
        }
        super.onDestroy();
    }

    private View.OnClickListener showListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isShowClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_gain_container, ShowFragment.getInstance());
                ft.commit();

                isShowClicked = true;
                isCharityClicked = false;

                show.setBackgroundResource(R.drawable.button_clik_style);
                charity.setBackgroundResource(R.drawable.button_unclik_style);
            }
        }
    };

    private View.OnClickListener charityListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isCharityClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_gain_container, CharityFragment.getInstance());
                ft.commit();

                isShowClicked = false;
                isCharityClicked = true;

                show.setBackgroundResource(R.drawable.button_unclik_style);
                charity.setBackgroundResource(R.drawable.button_clik_style);
            }
        }
    };
}
