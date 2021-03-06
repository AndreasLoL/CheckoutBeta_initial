package com.tetris.andreas.checkoutbeta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

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


        basketView.setAdapter(new CustomListAdapter(this, R.layout.basket_item, list));

        TextView price = (TextView) findViewById(R.id.priceView);

        price.setText("Total: " + String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(BasketActivity.this, MainActivity.class);
        BasketActivity.this.startActivity(myIntent);
        BasketActivity.this.finish();
    }

    private class CustomListAdapter extends ArrayAdapter<String> {

        private int layout;

        private List<String> mObjects;

        private Context context;

        public CustomListAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            this.context = context;
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.listImage);
                viewHolder.title = (TextView) convertView.findViewById(R.id.listText);
                viewHolder.button = (Button) convertView.findViewById(R.id.listButton);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item removed!", Toast.LENGTH_SHORT).show();
                    remove(getItem(position));
                    notifyDataSetChanged();
                    GlobalParameters.b.removeProduct(position);
                    UpdatePrice();
                }
            });
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }

        public void UpdatePrice(){
            TextView txtView = (TextView) ((Activity)context).findViewById(R.id.priceView);
            txtView.setText("Total: " + String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
        }

    }

    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        Button button;
    }

}
