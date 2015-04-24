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
public class DrawingFragment extends Fragment {

    private View rootView;

    private static DrawingFragment mInstance;

    private TextView variety, color;

    private FragmentManager fm;

    private boolean isVarietyClicked = true;
    private boolean isColorClicked = false;

    private DrawingFragment() {
        // Required empty public constructor
    }

    public static DrawingFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (DrawingFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new DrawingFragment();
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
            rootView = inflater.inflate(R.layout.drawing_fragment,container,false);
            variety = (TextView)rootView.findViewById(R.id.id_variety);
            color = (TextView)rootView.findViewById(R.id.id_color);

            Typeface typeFace =Typeface.createFromAsset(getActivity().getAssets(),"fonts/Bradley Hand ITC.TTF");

            variety.setTypeface(typeFace, Typeface.BOLD);
            color.setTypeface(typeFace, Typeface.BOLD);

            variety.setOnClickListener(varietyListener);
            color.setOnClickListener(colorListener);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        variety.setBackgroundResource(R.drawable.button_clik_style);
        color.setBackgroundResource(R.drawable.button_unclik_style);

        isVarietyClicked = true;
        isColorClicked = false;

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_drawing_container, VarietyFragment.getInstance());
        ft.commit();

        return rootView;
    }

    @Override
    public void onDestroy() {
        if(isVarietyClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(VarietyFragment.getInstance());
            ft.commit();
        }
        if(isColorClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(ColorFragment.getInstance());
            ft.commit();
        }
        super.onDestroy();
    }

    private View.OnClickListener varietyListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isVarietyClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_drawing_container, VarietyFragment.getInstance());
                ft.commit();

                isVarietyClicked = true;
                isColorClicked = false;

                variety.setBackgroundResource(R.drawable.button_clik_style);
                color.setBackgroundResource(R.drawable.button_unclik_style);
            }
        }
    };

    private View.OnClickListener colorListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isColorClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_drawing_container, ColorFragment.getInstance());
                ft.commit();

                isVarietyClicked = false;
                isColorClicked = true;

                variety.setBackgroundResource(R.drawable.button_unclik_style);
                color.setBackgroundResource(R.drawable.button_clik_style);
            }
        }
    };
}
