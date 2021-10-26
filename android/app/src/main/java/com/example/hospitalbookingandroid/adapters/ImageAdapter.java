package com.example.hospitalbookingandroid.adapters;


import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hospitalbookingandroid.ServerDomain;
import com.example.hospitalbookingandroid.dto.Hospital;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
    Context mContext;
    List<String> listOfImage=new ArrayList<>();
    public ImageAdapter(Context context, Hospital hospital) {
        this.mContext = context;
        listOfImage.add(hospital.getHospitalStaticConfig().getCoverImage());
       if(hospital.getHospitalStaticConfig().getCoverImage()!=null){
           listOfImage.addAll(hospital.getHospitalStaticConfig().getImages());
       }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(this.listOfImage.get(position).replace("127.0.0.1",
                ServerDomain.IP)).into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return this.listOfImage.size();

    }
}