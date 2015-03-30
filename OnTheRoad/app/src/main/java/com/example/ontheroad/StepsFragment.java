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
public class StepsFragment extends Fragment {


    private View rootView;

    private static StepsFragment mInstance;

    private TextView week, month, total;

    private FragmentManager fm;

    private boolean isWeekClicked = true;
    private boolean isMonthClicked = false;
    private boolean isTotalClicked = false;

    private StepsFragment() {
        // Required empty public constructor
    }

    public static StepsFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (StepsFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new StepsFragment();
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
            rootView = inflater.inflate(R.layout.steps_fragment,container,false);

            week = (TextView)rootView.findViewById(R.id.id_week);
            month = (TextView)rootView.findViewById(R.id.id_month);
            total = (TextView)rootView.findViewById(R.id.id_total);

            Typeface typeFace =Typeface.createFromAsset(getActivity().getAssets(),"fonts/Bradley Hand ITC.TTF");

            week.setTypeface(typeFace, Typeface.BOLD);
            month.setTypeface(typeFace, Typeface.BOLD);
            total.setTypeface(typeFace, Typeface.BOLD);

            week.setOnClickListener(weekListener);
            month.setOnClickListener(monthListener);
            total.setOnClickListener(totalListener);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_steps_container, WeekFragment.getInstance());
        ft.commit();

        week.setBackgroundResource(R.drawable.button_clik_style);
        month.setBackgroundResource(R.drawable.button_unclik_style);
        total.setBackgroundResource(R.drawable.button_unclik_style);

        isWeekClicked = true;
        isMonthClicked = false;
        isTotalClicked = false;

        return rootView;
    }

    @Override
    public void onDestroy() {
        if(isWeekClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(WeekFragment.getInstance());
            ft.commit();
        }
        if(isMonthClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(MonthFragment.getInstance());
            ft.commit();
        }
        if(isTotalClicked){
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(TotalFragment.getInstance());
            ft.commit();
        }
        super.onDestroy();
    }

    private View.OnClickListener weekListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isWeekClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_steps_container, WeekFragment.getInstance());
                ft.commit();

                isWeekClicked = true;
                isMonthClicked = false;
                isTotalClicked = false;

                week.setBackgroundResource(R.drawable.button_clik_style);
                month.setBackgroundResource(R.drawable.button_unclik_style);
                total.setBackgroundResource(R.drawable.button_unclik_style);
            }
        }
    };

    private View.OnClickListener monthListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isMonthClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_steps_container, MonthFragment.getInstance());
                ft.commit();

                isWeekClicked = false;
                isMonthClicked = true;
                isTotalClicked = false;

                week.setBackgroundResource(R.drawable.button_unclik_style);
                month.setBackgroundResource(R.drawable.button_clik_style);
                total.setBackgroundResource(R.drawable.button_unclik_style);
            }
        }
    };

    private View.OnClickListener totalListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(!isTotalClicked){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.id_steps_container, TotalFragment.getInstance());
                ft.commit();

                isWeekClicked = false;
                isMonthClicked = false;
                isTotalClicked = true;

                week.setBackgroundResource(R.drawable.button_unclik_style);
                month.setBackgroundResource(R.drawable.button_unclik_style);
                total.setBackgroundResource(R.drawable.button_clik_style);
            }
        }
    };

}
