package com.tetris.andreas.checkoutbeta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
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

        ReadProducts r = new ReadProducts(this, "products.txt");

        final ExpandableListAdapter adapter = new ExpandableAdapter(this, r.getGroupList(), r.getFoodCollection());

        choices.setAdapter(adapter);

        choices.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Product temp = (Product) adapter.getChild(groupPosition, childPosition);
                System.out.println(temp.getName());
                return false;
            }
        });
    }}
