package com.example.hospitalbookingandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.dto.Doctor;
import com.example.hospitalbookingandroid.dto.HospitalSection;
import com.example.hospitalbookingandroid.pages.SectionDetailActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Optional;

public class DoctorCardListAdapter extends RecyclerView.Adapter<DoctorCardListAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "DoctorDetail";
    private final List<Doctor> messagesList;
    private Context context;


    //        private MessagesService.MessageInterface messageInterface;
    public DoctorCardListAdapter(List<Doctor> messages, Context context) {
        messagesList = messages;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_list_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = messagesList.get(position);
        holder.text_doctor_name.setText(Optional.ofNullable(messagesList.get(position).getName()).orElse(" لا يوجد"));
        holder.txt_doctor_about.setText(Optional.ofNullable(messagesList.get(position).getAbout()).orElse(" لا يوجد"));
        Picasso.get().load(holder.mItem.getImage().replace("127.0.0.1","10.0.2.2")).into(holder.img_doctor_image);
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView img_doctor_image;
        public final TextView text_doctor_name;
        public final TextView txt_doctor_about;
        public Doctor mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            img_doctor_image = view.findViewById(R.id.img_doctor);
            text_doctor_name = view.findViewById(R.id.txt_doctor_name);
            txt_doctor_about = view.findViewById(R.id.txt_doctor_about);
        }

        @Override
        public String toString() {
            return "not to string";
        }
    }
}