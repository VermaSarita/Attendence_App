package com.example.appointmentsystem;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    boolean isSelected=false;
    ArrayList<ModelClass> List;
    Context context;

    public Adapter(ArrayList<ModelClass> list, Context context) {
        List = list;
        this.context = context;

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.doctor_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.Drimage.setImageResource(List.get(position).Drimage);
        holder.textName.setText(List.get(position).textName);
        holder.textAbout.setText(List.get(position).textAbout);
        holder.feeView.setText(List.get(position).feeView);
        holder.phoneno.setText(List.get(position).phoneno);
        holder.getAppointment.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent( context,appointment_Activity.class ));
            }

        });



//
//        holder.itemView.setOnClickListener(new View.OnClickListener( ) {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent( context, Dahboard_Screen.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Drimage,imageSelected;
        TextView textName,textAbout,feeView ,phoneno;
        RatingBar ratingBar;
        Button getAppointment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Drimage=itemView.findViewById(R.id.Drimage);
            imageSelected=itemView.findViewById(R.id.imageSelected);
            textName=itemView.findViewById(R.id.textName);
            textAbout=itemView.findViewById(R.id.textAbout);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            getAppointment=itemView.findViewById(R.id.getAppointment);
            feeView=itemView.findViewById(R.id.feeView);
            phoneno=itemView.findViewById(R.id.phoneno);

        }
    }
}
