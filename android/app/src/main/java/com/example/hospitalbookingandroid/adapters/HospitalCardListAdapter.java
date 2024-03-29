package com.example.hospitalbookingandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingandroid.pages.HospitalDetailActivity;
import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.dto.Hospital;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class HospitalCardListAdapter extends RecyclerView.Adapter<HospitalCardListAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "HospitalDetail";
    private final List<Hospital> messagesList;
    private final Location location;
    private Context context;


    //        private MessagesService.MessageInterface messageInterface;
    public HospitalCardListAdapter(List<Hospital> messages, Location currentLocation, Context context) {

        location=currentLocation;
        this.context=context;


        messagesList = messages;

        Collections.sort(messagesList, (o1, o2) -> {


            Location oo1=new Location(LocationManager.GPS_PROVIDER);
            oo1.setLatitude(Double.valueOf(o1.getLatitude()));
            oo1.setLongitude(Double.valueOf(o1.getLongitude()));

            int dis1=   Math.round(  location.distanceTo(oo1));



            Location oo2=new Location(LocationManager.GPS_PROVIDER);
            oo2.setLatitude(Double.valueOf(o2.getLatitude()));
            oo2.setLongitude(Double.valueOf(o2.getLongitude()));

            int dis2=    Math.round( location.distanceTo(oo2));
            int reslut= dis1-dis2;


            return reslut;



        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = messagesList.get(position);
        holder.hospitalName.setText(messagesList.get(position).getName());


        Picasso.get().load(holder.mItem.getHospitalStaticConfig().getCoverImage()).into(holder.hospitalImage);

        Location hospitalLocation=new Location(LocationManager.GPS_PROVIDER);
        hospitalLocation.setLatitude(Double.valueOf(holder.mItem.getLatitude()));
        hospitalLocation.setLongitude(Double.valueOf(holder.mItem.getLongitude()));



        double disanceInMeter= location.distanceTo(hospitalLocation);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if(disanceInMeter> 100){

            disanceInMeter=disanceInMeter/1000;

            String distance=df.format(disanceInMeter) + "  كم ";
            holder.hospitalLocation.setText(distance);

        }else {
            String distance=df.format(disanceInMeter) + " متر ";
            holder.hospitalLocation.setText(distance);
        }


        holder.mView.setOnClickListener((view)->{
            Intent intent = new Intent(context, HospitalDetailActivity.class);

            Gson gson = new Gson();
            String myJson = gson.toJson(holder.mItem);
            intent.putExtra(EXTRA_MESSAGE, myJson);
            context.startActivity(intent);

            Toast.makeText(context, "click hospital id="+messagesList.get(position).getId().toString(),Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView hospitalImage;
        public final TextView hospitalName;
        public final TextView hospitalLocation;
        public Hospital mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            hospitalName =  view.findViewById(R.id.txtSectionName);
            hospitalLocation = view.findViewById(R.id.txtDoctorNo);
            hospitalImage= view.findViewById(R.id.img_section);
        }
        @Override
        public String toString() {
            return "not to string";
        }
    }
}