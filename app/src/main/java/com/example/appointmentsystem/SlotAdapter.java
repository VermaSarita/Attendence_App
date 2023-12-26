package com.example.appointmentsystem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appointmentsystem.Model.DoctorSlot.Slot;
import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {
    private List<Slot> slotModels;
    private Context context;

    public SlotAdapter(List<Slot> slotModels, Context context) {
        this.slotModels = slotModels;
        this.context = context;

    }

    @NonNull
    @Override
    public SlotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textview.setText(slotModels.get(position).getTiming());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Slot selectedSlot = slotModels.get(position);
                Intent intent = new Intent(context, appointment_Activity.class);
                intent.putExtra("selectedSlot", selectedSlot.getSlot());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return slotModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
        }
    }
}
