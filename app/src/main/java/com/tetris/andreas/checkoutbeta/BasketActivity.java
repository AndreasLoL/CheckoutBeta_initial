package com.tetris.andreas.checkoutbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ListView basketView = (ListView) findViewById(R.id.basketView);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < GlobalParameters.b.getAllProductsArray().length; ++i) {
            list.add(GlobalParameters.b.getAllProductsArray()[i].toString());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        basketView.setAdapter(adapter);

        TextView price = (TextView) findViewById(R.id.priceView);

        price.setText(String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
    }
}
