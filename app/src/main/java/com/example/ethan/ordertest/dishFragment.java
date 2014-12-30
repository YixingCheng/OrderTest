package com.example.ethan.ordertest;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.util.Log;
import android.support.v4.app.Fragment;
import java.util.ArrayList;

/**
 * Created by ethan on 12/19/14.
 */
public class dishFragment extends ListFragment {
    private static final String    TAG                 = "OrderTest";
    private static final String ARG_SECTION_NUMBER = "section_number";

    DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        String category = MainActivity.categories[sectionNumber -1];
        Log.i(TAG, "current fragment is: " + category);
        db = new DatabaseHandler(getActivity());
        ArrayList<Dish> dishes = db.getDishbyCat(category);

        // We need to use a different list item layout for devices older than Honeycomb
        //int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
        //        android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        //setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.dishlayout, R.id.label, values));
        setListAdapter(new dishAdapter(getActivity(), dishes));
    }

    public static dishFragment newInstance(int sectionNumber) {
        dishFragment fragment = new dishFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public dishFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dish, container, false);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("Truiton FragmentList", "Item clicked: " + id);
    }

}
