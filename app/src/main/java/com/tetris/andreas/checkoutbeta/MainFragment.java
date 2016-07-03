package com.tetris.andreas.checkoutbeta;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

/**
 * Created by ANDREAS on 03.07.2016.
 */
public class MainFragment extends Fragment {
    ViewPager viewPager = ViewPagerActivity.viewPager;
    Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ExpandableListView choices = (ExpandableListView) rootView.findViewById(R.id.listView);

        ReadProducts r = new ReadProducts(mActivity, "products.txt");



        final ExpandableListAdapter adapter = new ExpandableAdapter(mActivity, r.getGroupList(), r.getFoodCollection());

        choices.setAdapter(adapter);

        GlobalParameters.b = new Basket("My temporary basket!");

        choices.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Product temp = (Product) adapter.getChild(groupPosition, childPosition);
                GlobalParameters.b.addToBasket(temp);
                Toast.makeText(getContext(), "Added to basket!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        Button basketButton = (Button) rootView.findViewById(R.id.basketButton);

        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1, true);

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
}
