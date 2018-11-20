package com.example.neerajal.mytest;

import android.content.Context;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public static List<Bitmap> imagesfromCatAPI= new ArrayList<>();

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            /*R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,*/

    };

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {

            return imagesfromCatAPI.size();

       // return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        if(imagesfromCatAPI.size()>0) {
            System.out.println("from ADAPTER"+imagesfromCatAPI.size());
            imageView.setImageBitmap(imagesfromCatAPI.get(position));
        }else {
            //imageView.setImageResource(mThumbIds[position]);
        }
        return imageView;
    }



}
