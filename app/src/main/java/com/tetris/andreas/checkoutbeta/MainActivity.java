package com.tetris.andreas.checkoutbeta;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View contentView = (View) findViewById(R.id.main);

        ExpandableListView choices = (ExpandableListView) findViewById(R.id.listView);

        ReadProducts r = new ReadProducts(this, "products.txt");

        final ExpandableListAdapter adapter = new ExpandableAdapter(this, r.getGroupList(), r.getFoodCollection());

        choices.setAdapter(adapter);

        GlobalParameters.b = new Basket("My temporary basket!");

        choices.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Product temp = (Product) adapter.getChild(groupPosition, childPosition);
                GlobalParameters.b.addToBasket(temp);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Added to basket!");
                return false;
            }
        });

        Button basketButton = (Button) findViewById(R.id.basketButton);

        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, BasketActivity.class);

                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}


