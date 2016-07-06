package com.tetris.andreas.checkoutbeta;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANDREAS on 03.07.2016.
 */
public class BasketFragment extends Fragment {
    Activity mActivity;

    ListView basketView;
    TextView price;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket_layout, container, false);

        basketView = (ListView) rootView.findViewById(R.id.basketView);

//        final ArrayList<String> list = new ArrayList<String>();
//        if (GlobalParameters.b != null && GlobalParameters.b.getAllProducts() != null) {
//            for (int i = 0; i < GlobalParameters.b.getAllProductsArray().length; ++i) {
//                list.add(GlobalParameters.b.getAllProductsArray()[i].toString());
//            }
//        }
//
//        basketView.setAdapter(new CustomListAdapter(mActivity, R.layout.basket_item, list));

        price = (TextView) rootView.findViewById(R.id.priceView);

        if (GlobalParameters.b != null && GlobalParameters.b.getAllProducts() != null) {
            price.setText("Total: " + String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
        } else {
            price.setText("Total: " + 0 + "€");
        }


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        basketView.setAdapter(new CustomListAdapter(mActivity, R.layout.basket_item, GlobalParameters.b.getAllProducts()));

        if (GlobalParameters.b != null && GlobalParameters.b.getAllProducts() != null) {
            price.setText("Total: " + String.format("%.2f", GlobalParameters.b.getTotalPrice()) + "€");
        } else {
            price.setText("Total: " + 0 + "€");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

//    @Override
//    public void onBackPressed() {
//        Intent myIntent = new Intent(BasketActivity.this, MainActivity.class);
//        BasketActivity.this.startActivity(myIntent);
//        BasketActivity.this.finish(); Todo: fix this
//    }

    private class CustomListAdapter extends ArrayAdapter<Product> {

        private int layout;

        private List<Product> mObjects;

        private Context context;

        public CustomListAdapter(Context context, int resource, List<Product> products) {
            super(context, resource, products);
            this.context = context;
            mObjects = products;
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
                viewHolder.thumbnail.setAnimation(null);
                UrlImageViewHelper.setUrlDrawable(viewHolder.thumbnail, getItem(position).getImgURL());
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
            mainViewholder.title.setText(getItem(position).toString());

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
