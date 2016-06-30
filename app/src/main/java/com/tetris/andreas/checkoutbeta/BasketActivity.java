package com.tetris.andreas.checkoutbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity implements SwipeInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        View contentView = (View) findViewById(R.id.main);

        ListView basketView = (ListView) findViewById(R.id.basketView);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < GlobalParameters.b.getAllProductsArray().length; ++i) {
            list.add(GlobalParameters.b.getAllProductsArray()[i].toString());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        basketView.setAdapter(adapter);

        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);

        contentView.setOnTouchListener(swipe);

        TextView price = (TextView) findViewById(R.id.priceView);

        price.setText(String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
    }

    @Override
    public void bottom2top(View v) {
        return;
    }

    @Override
    public void left2right(View v) {
        return;
    }

    @Override
    public void right2left(View v) {
        Intent myIntent = new Intent(BasketActivity.this, MainActivity.class);
        BasketActivity.this.startActivity(myIntent);
    }

    @Override
    public void top2bottom(View v) {
        return;
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(BasketActivity.this, MainActivity.class);
        BasketActivity.this.startActivity(myIntent);
    }
}
