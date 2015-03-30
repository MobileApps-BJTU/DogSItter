package com.example.ontheroad;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interfaces.OnFragmentInteractionListener;
import com.example.service.StepService;
import com.example.util.StepDetector;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private View rootView;
    private TextView steps, drawing, gain, stepNum;
    private Thread thread;

    private static MainFragment mInstance;

    private FragmentManager fm;
    private LinearLayout container;

    private AnimationDrawable anim;
    private int timesToStop = 10;
    private int currentSteps = 0;

//    private boolean isStepsClicked = true;
//    private boolean isDrawingClicked = true;
//    private boolean isGainClicked = true;

    private OnFragmentInteractionListener mListener;

    private MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (MainFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new MainFragment();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
            rootView = inflater.inflate(R.layout.main_fragment,container,false);

            steps = (TextView)rootView.findViewById(R.id.id_steps);
            drawing = (TextView)rootView.findViewById(R.id.id_drawing);
            gain = (TextView)rootView.findViewById(R.id.id_gain);

            stepNum = (TextView)rootView.findViewById(R.id.id_step_num);

            Typeface typeFace =Typeface.createFromAsset(getActivity().getAssets(),"fonts/Bradley Hand ITC.TTF");

            steps.setTypeface(typeFace, Typeface.BOLD);
            drawing.setTypeface(typeFace, Typeface.BOLD);
            gain.setTypeface(typeFace, Typeface.BOLD);

            steps.setOnClickListener(stepsListener);
            drawing.setOnClickListener(drawingListener);
            gain.setOnClickListener(gainListener);

            container = (LinearLayout)rootView.findViewById(R.id.id_main_container);
            anim = (AnimationDrawable)container.getBackground();
            //anim.start();

            Intent intent = new Intent(getActivity(), StepService.class);
            getActivity().startService(intent);
            mThread();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void mThread() {
        if (thread == null) {

            thread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (StepService.flag) {
                            Message msg = new Message();
                            handler.sendMessage(msg);
                        }
                    }
                }
            });
            thread.start();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(StepDetector.CURRENT_SETP != currentSteps){
                timesToStop = 0;
                currentSteps = StepDetector.CURRENT_SETP;
                anim.start();
            }else{
                if(timesToStop == 10){
                    if(anim.isRunning())
                        anim.stop();
                }else{
                    timesToStop++;
                }
            }
            stepNum.setText(StepDetector.CURRENT_SETP + " steps");
        }
    };

    private View.OnClickListener stepsListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            mListener.onStepsClicked();
//            if(!isStepsClicked){
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.id_main_container, StepsFragment.getInstance());
//                ft.commit();
//
//                isStepsClicked = true;
//                isDrawingClicked = false;
//                isGainClicked = false;
//
//                steps.setBackgroundResource(R.drawable.button_clik_style);
//                drawing.setBackgroundResource(R.drawable.button_unclik_style);
//                gain.setBackgroundResource(R.drawable.button_unclik_style);
//            }
        }
    };

    private View.OnClickListener drawingListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            mListener.onDrawingClicked();
//            if(!isDrawingClicked){
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.id_main_container, DrawingFragment.getInstance());
//                ft.commit();
//
//                isStepsClicked = false;
//                isDrawingClicked = true;
//                isGainClicked = false;
//
//                steps.setBackgroundResource(R.drawable.button_unclik_style);
//                drawing.setBackgroundResource(R.drawable.button_clik_style);
//                gain.setBackgroundResource(R.drawable.button_unclik_style);
//            }
        }
    };

    private View.OnClickListener gainListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            mListener.onGainClicked();
//            if(!isGainClicked){
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.id_main_container, GainFragment.getInstance());
//                ft.commit();
//
//                isStepsClicked = false;
//                isDrawingClicked = false;
//                isGainClicked = true;
//
//                steps.setBackgroundResource(R.drawable.button_unclik_style);
//                drawing.setBackgroundResource(R.drawable.button_unclik_style);
//                gain.setBackgroundResource(R.drawable.button_clik_style);
//            }
        }
    };
}
