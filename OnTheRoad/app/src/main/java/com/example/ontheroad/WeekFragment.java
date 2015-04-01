package com.example.ontheroad;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.example.tools.DisplayUtil;
import com.example.util.HistogramView;
import com.example.util.Steps;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class WeekFragment extends Fragment implements View.OnTouchListener {

    private HistogramView hv;

    private String[] weeks;// 设置星期数目
    private int[] steps;// 设置7天的步数
    private int[] text;// 设置是否显示对应柱状图的数值

    private Calendar calendar;
    public String day;


    public int records;
    private int average = 0;
    private int sum = 0;
    private int average1 = 0;
    private int sum1 = 0;

    private AllAnimation ani;// 设置的动画

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        setWeek();
        setProgress();
        rootView.startAnimation(ani);

    }

    /**
     * 初始化一些对象
     */
    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        weeks = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
        steps = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        text = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        ani = new AllAnimation();
        ani.setDuration(2000);

        calendar = Calendar.getInstance();

        hv = (HistogramView) rootView.findViewById(R.id.histograms);

        hv.setOnTouchListener(this);

    }

    @SuppressLint("SimpleDateFormat")
    private void setProgress() {

        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+5000;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+1750;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+520;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+1000;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+3000;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+1000;
        steps[i++] = records;

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = sdf.format(calendar.getTime());
        records = (Steps.STEPS)+500;
        steps[i++] = records;

        hv.setWeekd(weeks);
        hv.setProgress(steps);

    }

    /**
     * 设置星期
     */
    private void setWeek() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // Toast.makeText(getActivity(), day + "", Toast.LENGTH_LONG).show();
        day -= 1;
        for (int i = 0; i < weeks.length; i++) {
            weeks[i] = week(day - i);
        }
    }

    /**
     * 将星期由阿拉伯数字变为汉字
     * @param day
     * @return
     */
    private String week(int day) {
        if (day < 1) {
            day += 7;
        }
        switch (day) {
            case 1:
                return "Mon";
            case 2:
                return "Tue";
            case 3:
                return "Wed";
            case 4:
                return "Thu";
            case 5:
                return "Fri";
            case 6:
                return "Sat";
            case 7:
                return "Sun";
            default:
                return "";
        }
    }


    private class AllAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                sum1 = (int) (sum * interpolatedTime);
                average1 = (int) (average * interpolatedTime);
            } else {
                sum1 = sum;
                average1 = average;
            }
            rootView.postInvalidate();
            average = sum / 7;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int step = (v.getWidth() - dp2px(30)) / 8;
        int x = (int) event.getX();
        for (int i = 0; i < 7; i++) {
            if (x > (dp2px(15) + step * (i + 1) - dp2px(15))
                    && x < (dp2px(15) + step * (i + 1) + dp2px(15))) {
                text[i] = 1;
                for (int j = 0; j < 7; j++) {
                    if (i != j) {
                        text[j] = 0;
                    }
                }
                hv.setText(text);
            }
        }

        return false;
    }

    private int dp2px(int value) {
        return DisplayUtil.dip2px(getActivity(), value);
    }


}