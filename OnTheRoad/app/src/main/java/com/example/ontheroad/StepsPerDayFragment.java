package com.example.ontheroad;

import android.app.Activity;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.StepsPerDayAdapter;
import com.example.entity.StepsPerDay;
import com.example.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link com.example.interfaces.OnFragmentInteractionListener}
 * interface.
 */
public class StepsPerDayFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private SharedPreferences savedSearches;
    private List<StepsPerDay> list;
    private StepsPerDayAdapter stepsPerDayAdapter;

    private static StepsPerDayFragment mInstance;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    private StepsPerDayFragment() {
    }

    public static StepsPerDayFragment getInstance()
    {

        if (mInstance == null)
        {
            synchronized (StepsPerDayFragment.class)
            {
                if (mInstance == null)
                {
                    mInstance = new StepsPerDayFragment();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<StepsPerDay>();
        List<String> tags = new ArrayList<String>(savedSearches.getAll().keySet());
        for(int i = 0;i < tags.size();i++){
            list.add(new StepsPerDay(tags.get(i),savedSearches.getString(tags.get(i),"0")));
        }
        stepsPerDayAdapter = new StepsPerDayAdapter(getActivity(), list);
        setListAdapter(stepsPerDayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_per_day_fragment, container, false);
        return view;
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
        }
    }

    public void setSavedSearches(SharedPreferences savedSearches) {
        this.savedSearches = savedSearches;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
