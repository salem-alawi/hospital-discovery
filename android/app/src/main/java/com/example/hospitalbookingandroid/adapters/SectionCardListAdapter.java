package com.example.hospitalbookingandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingandroid.R;
import com.example.hospitalbookingandroid.dto.HospitalSection;
import com.example.hospitalbookingandroid.pages.SectionDetailActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SectionCardListAdapter extends RecyclerView.Adapter<SectionCardListAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "sectionDetail";
    private final List<HospitalSection> messagesList;
    private Context context;


    //        private MessagesService.MessageInterface messageInterface;
    public SectionCardListAdapter(List<HospitalSection> messages, Context context) {
        messagesList = messages;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = messagesList.get(position);
        holder.txtSectionName.setText(messagesList.get(position).getSection().getName());
        Picasso.get().load(holder.mItem.getSection().getCoverImage().replace("127.0.0.1","10.0.2.2")).into(holder.img_section);
        holder.txtDoctorNo.setText(  holder.mItem.getDoctors().size()+ " طبيب");


        holder.mView.setOnClickListener((view)->{
            Intent intent = new Intent(context, SectionDetailActivity.class);

            Gson gson = new Gson();
            String myJson = gson.toJson(holder.mItem);
            intent.putExtra(EXTRA_MESSAGE, myJson);
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView img_section;
        public final TextView txtSectionName;
        public final TextView txtDoctorNo;
        public HospitalSection mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            img_section =  view.findViewById(R.id.img_section);
            txtSectionName = view.findViewById(R.id.txtSectionName);
            txtDoctorNo= view.findViewById(R.id.txtDoctorNo);
        }
        @Override
        public String toString() {
            return "not to string";
        }
    }
}