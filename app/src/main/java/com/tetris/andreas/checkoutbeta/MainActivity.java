package com.tetris.andreas.checkoutbeta;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView choices = (ExpandableListView) findViewById(R.id.listView);

        List<String> groupList = new ArrayList<>();

        groupList.add("Piim");
        groupList.add("Vesi");
        groupList.add("Leib");
        groupList.add("Juust");

        Map<String, List<String>> foodCollection = new LinkedHashMap<>();

        String[] companies = {"firma1", "firma2", "firma3"};

        for (String collections : groupList) {
            loadChild(companies);

            foodCollection.put(collections, childList);
        }


        ExpandableListAdapter adapter = new ExpandableAdapter(this, groupList, foodCollection);

        choices.setAdapter(adapter);
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

}
