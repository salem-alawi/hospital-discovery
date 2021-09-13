package com.example.hospitalbookingandroid;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingandroid.dto.Hospital;

import java.text.DecimalFormat;
import java.util.List;

public class HospitalCardListAdapter extends RecyclerView.Adapter<HospitalCardListAdapter.ViewHolder> {

    private final List<Hospital> messagesList;
    private final Location location;
    private Context context;
    //        private MessagesService.MessageInterface messageInterface;
    public HospitalCardListAdapter(List<Hospital> messages, Location currentLocation, Context context) {
        messagesList = messages;
        location=currentLocation;
        this.context=context;
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
            Toast.makeText(context, "hellow="+messagesList.get(position).getId().toString(),Toast.LENGTH_SHORT).show();
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
            hospitalName =  view.findViewById(R.id.txt_hospitalName);
            hospitalLocation = view.findViewById(R.id.txt_distance);
            hospitalImage= view.findViewById(R.id.img_hospital);
        }
        @Override
        public String toString() {
            return "not to string";
        }
    }
}