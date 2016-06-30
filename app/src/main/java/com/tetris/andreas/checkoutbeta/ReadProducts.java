package com.tetris.andreas.checkoutbeta;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ANDREAS on 30.06.2016.
 */
public class ReadProducts {

    private Context context;

    private String fileName;

    private List<String> groupList = new ArrayList<>();

    private Map<String, List<String>> foodCollection = new LinkedHashMap<>();

    private List<String> childList;


    public ReadProducts(Context context, String fileName) {
        this.context = context;

        this.fileName = fileName;

        System.out.println(readToString());
    }

    public Map<String, List<String>> getFoodCollection() {
        return this.foodCollection;
    }

    public List<String> getGroupList() {
        return this.groupList;
    }

    private boolean readToString() {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream stream = assetManager.open(fileName);

            BufferedReader in=
                    new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                lineToLists(str);
            }

            in.close();
            stream.close();
            return true;
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
            return false;
        }
    }

    private void lineToLists(String s) {
        System.out.println(s);
        String[] spl = s.split("-");

        if (spl.length != 2) {
            Log.d("WRONG SIZE", "Line length is wrong, needs checking!");
            return;
        }

        groupList.add(spl[0]);

        loadChild(spl[1].split(","));

        foodCollection.put(spl[0], childList);
    }

    private void loadChild(String[] products) {
        childList = new ArrayList<>();
        for (String model : products) {
            childList.add(model.split("\\.")[0] + " - väikseim hind: " +
                    Double.parseDouble(model.split("\\.")[1]) / 100 + "€");
        }
    }

}
