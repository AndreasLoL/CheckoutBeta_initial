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

        ReadProducts r = new ReadProducts(this, "products.txt");

        ExpandableListAdapter adapter = new ExpandableAdapter(this, r.getGroupList(), r.getFoodCollection());

        choices.setAdapter(adapter);

    }

}
